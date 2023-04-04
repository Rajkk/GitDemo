 package Practiceacademy.pageobejcts;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practiceacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends  AbstractComponent {


	public WebDriver driver;

		
	public CheckoutPage(WebDriver driver)
	{
		
		super(driver);
		// initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");


	private WebElement checkoutEle;
	public void selectCountry(String countryName)
	{
		
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	}

	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		submit.click();
		return	new ConfirmationPage(driver);
	}


	

	
	public Boolean VerifyProductDisplay(String productName )
	{
		Collection<WebElement> cartProducts = null;
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	
}
