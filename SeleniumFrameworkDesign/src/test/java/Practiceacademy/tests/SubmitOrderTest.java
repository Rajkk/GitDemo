package Practiceacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practiceacademy.TestComponents.BaseTest;
import Practiceacademy.pageobejcts.CartPage;
import Practiceacademy.pageobejcts.CheckoutPage;
import Practiceacademy.pageobejcts.ConfirmationPage;
import Practiceacademy.pageobejcts.OrderPage;
import Practiceacademy.pageobejcts.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String,String> input)
			throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage();

		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {
		ProductCatalogue productCatalogue = landingpage.loginApplication("rajkawana4@gmail.com", "Raj@11021996");
		OrderPage orderspage = productCatalogue.goToOrdersfPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
	//	HashMap<String,String> map= new HashMap<String,String>();
		//map.put("email", "rajkawana4@gmail.com");
		//map.put("password", "Raj@11021996");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String,String> map1= new HashMap<String,String>();
		//map1.put("email", "rajkawana5@gmail.com");
		//map1.put("password", "Raj@11021996");
		//map1.put("product", "ADIDAS ORIGINAL");
	List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Practiceacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) },{ data.get(1) } };
	}

	
	}

