package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(name = "email")
	WebElement emailTxt;
	
	@FindBy(name = "password")
	WebElement passwordTxt;
	
	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/button")
	WebElement loginBtn;
	
	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/h2")
	public WebElement loginAccountMessage;
	
	@FindBy(linkText  = "Delete Account")
	WebElement deleteAccount;
	
	@FindBy(partialLinkText  = "Logged in ")
	public WebElement loggedLink;
	
	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
	public WebElement deleteMessage;
	
	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div/div/a")
    WebElement continueBtn ;
	
	@FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/p")
	public WebElement errorMessage;
	
    @FindBy(linkText = "Logout")
    WebElement logoutLink;
	
	public void userCanLogin(String email , String password) {
		
		emailTxt.sendKeys(email);
		passwordTxt.sendKeys(password);
		
		loginBtn.click();
		
	}
	
	public void deleteAccount() {
		deleteAccount.click();
		
	}
	
	public void continueAccount() {
		continueBtn.click();
	}

	public void userCanLogout() {
	    try {
	        logoutLink.click();
	      
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to logout properly: " + e.getMessage());
	    }
	}

	
}
