package tests;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import org.testng.annotations.Test;

import pages.HomePage;

import java.time.Duration;


public class VerifyTestCasesPage extends TestBase  {
    

	 HomePage homeObject ;
   
    @Test
    public void test1(){
    	homeObject = new HomePage(driver);
    	//Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(homePage.isDisplayed());
       
        //Click on 'Test Cases' button
        homeObject.openTestCasesPage();
        
        //Verify user is navigated to test cases page successfully
        Assert.assertEquals(homeObject.TestCasesTitle.getText(), "Test Cases");
    }

 
}