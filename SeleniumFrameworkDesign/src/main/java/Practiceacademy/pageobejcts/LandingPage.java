package Practiceacademy.pageobejcts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practiceacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//Initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
//WebElement userEmail= driver.findElement(By.id("userEmail"));

	//PageFactory 
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

			
	@FindBy(xpath = "[class*='flyInOut']")
	WebElement errorMessage;


	public ProductCatalogue loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
