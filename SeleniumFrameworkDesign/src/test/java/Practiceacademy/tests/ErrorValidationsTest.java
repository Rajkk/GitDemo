package Practiceacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Practiceacademy.TestComponents.BaseTest;
import Practiceacademy.TestComponents.Retry;
import Practiceacademy.pageobejcts.CartPage;
import Practiceacademy.pageobejcts.LandingPage;
import Practiceacademy.pageobejcts.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest
{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("rajkawana4@gmail.com", "Raj@11021996");
	Assert.assertEquals(" Incorrect email or password.  ", landingpage.getErrorMessage());	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	   
		String productName = "ZARA COAT 3";
		LandingPage landingpage= launchApplication();
		ProductCatalogue productCatalogue =landingpage.loginApplication("rajkawana5@gmail.com", "Raj@11021996");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	
}	
}