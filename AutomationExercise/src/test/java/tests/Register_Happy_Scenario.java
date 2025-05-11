package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.RegisterPage;


public class Register_Happy_Scenario extends TestBase {
	HomePage homeObject = new HomePage(driver);
	RegisterPage registerObject = new RegisterPage(driver);
	
	@DataProvider(name = "registerData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"kholoud khaled","kholoud15511@gmail.com","kholoud155312",15,"5","2005","kholoud","khaled","DEPI","El-Fayoum","Itsa","Australia","Jedcet","Brde delt","9900000","01001386305"}
			
		};
		
		return data;
	}
	
  @Test(priority = 1 , dataProvider = "registerData")
  public void Register_NewEmail_MandatoryAndOptional(String name , String email ,String password, int day , String month , String year , String firstName , String lastName , String company
			, String address1 , String address2 , String country , String state , String city , String zipcode , String mobileNumber) throws InterruptedException {
	  Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color")); 
	  
	  
	  homeObject.openRegisterationPage();
	  Assert.assertEquals("New User Signup!",registerObject.newUserMessage.getText());
	  Thread.sleep(3000);
	  
	  registerObject.userCanRegister(name, email); 
	  
	  Assert.assertEquals("ENTER ACCOUNT INFORMATION", registerObject.accountInformationMessage.getText());//1 way
	  
	  registerObject.enterAccountInformation(password,day,month,year,firstName,lastName,company,address1,address2,country,state,city,zipcode,mobileNumber);
	  Thread.sleep(3000);
	  
	  String success = "Account Created!";
      Assert.assertEquals(success.toUpperCase(), registerObject.accountCreatedMessage.getText());// 2 way
      
      registerObject.continueAccount();     
      
      Assert.assertEquals("Logged in as "+ name  , registerObject.loggedLink.getText());
       
      registerObject.deleteAcount();
     
      Assert.assertTrue( registerObject.deleteMessage.getText().equalsIgnoreCase("Account Deleted!"));//3 way
      
      registerObject.continueAccount();
      
  }


}
