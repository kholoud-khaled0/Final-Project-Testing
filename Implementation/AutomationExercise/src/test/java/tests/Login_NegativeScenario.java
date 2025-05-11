package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class Login_NegativeScenario  extends TestBase{
	
	HomePage homeObject = new HomePage(driver);
	LoginPage loginObject = new LoginPage(driver);
	
	@DataProvider(name = "loginData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"kholoud15442@gmail.com","kholoud155312"}
			
		};
		
		return data;
	}
	
  @Test(priority = 2 , dataProvider = "loginData")
  public void emailAndPasswordNotCorrect(String email , String password) throws InterruptedException {
	  
	  Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color")); 
	  homeObject.openLoginPage();
	  
	  Assert.assertEquals("Login to your account", loginObject.loginAccountMessage.getText());
	  loginObject.userCanLogin(email , password);  
	  
	  
      Assert.assertEquals("Your email or password is incorrect!", loginObject.errorMessage.getText());
      
  }
} 
