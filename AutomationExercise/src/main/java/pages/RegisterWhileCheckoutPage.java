package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterWhileCheckoutPage extends PageBase{

	public RegisterWhileCheckoutPage(WebDriver driver) {
		super(driver);
		
	}

@FindBy(xpath = "//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a/u")
WebElement Register_LoginBtn;

public void Register_Login() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", Register_LoginBtn);
	
}
}
