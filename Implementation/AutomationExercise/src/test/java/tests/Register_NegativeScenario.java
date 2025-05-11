package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.RegisterPage;

public class Register_NegativeScenario extends TestBase  {
	HomePage homeObject = new HomePage(driver);
	RegisterPage registerObject = new RegisterPage(driver);
	
	@DataProvider(name = "registerData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"kholoud khaled","kholoud111@gmail.com"}
		};
		
		return data;
	}
	
	
  @Test(priority = 2 , dataProvider = "registerData")
  public void Register_ExistEmail(String name , String email)throws InterruptedException {
  {
		  Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color")); 
		  
		  
		  homeObject.openRegisterationPage();
		  Assert.assertEquals("New User Signup!",registerObject.newUserMessage.getText());		
		  
		  
		  registerObject.userCanRegister(name, email);
		  
		 
		  
		  Assert.assertEquals("Email Address already exist!", registerObject.emailExistMessage.getText());
		  
		  
	  }
  }
}
