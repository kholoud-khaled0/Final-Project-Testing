package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class Login_HappyScenario extends TestBase {
	HomePage homeObject = new HomePage(driver);
	LoginPage loginObject = new LoginPage(driver);
	
	@DataProvider(name = "loginData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"kholoud111@gmail.com","kholoud155312"}
			
		};
		 
		return data;
	}
	
  @Test(priority = 1 , dataProvider = "loginData")
  public void testLogin_CorrectEmailAndPassword(String email , String password) throws InterruptedException {
	  Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color")); 
	  homeObject.openLoginPage();
	  
	  Assert.assertEquals("Login to your account", loginObject.loginAccountMessage.getText());
	  loginObject.userCanLogin(email , password);  
	  
	  String name = "kholoud khaled";
      Assert.assertEquals("Logged in as "+ name  , loginObject.loggedLink.getText());
	  
//      loginObject.deleteAccount();
//      Assert.assertTrue( loginObject.deleteMessage.getText().equalsIgnoreCase("Account Deleted!"));
//	  
//      loginObject.continueAccount();
	  
	 
	  
	  
  }
  
  
  
}
