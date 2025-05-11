package tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.HomePage;

public class ContactUs_HappyScenario extends TestBase {
	HomePage homeObject = new HomePage(driver);
	ContactUsPage contactObject = new ContactUsPage(driver);
	
	
	@DataProvider(name = "contactUsData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"Shady","Shadyelsawy536@gmail.com","Complain","My order doesn't deliver yet"}
			
		};
		
		return data;
	}
	
  @Test(priority = 1 , dataProvider = "contactUsData")
  public void testContactUs_vaildData(String name , String email , String subject ,  String message) throws InterruptedException, AWTException {
	  
	  Thread.sleep(3000);
	  
	  homeObject.openContactUsPage();
	  Thread.sleep(3000);
	  
	  Assert.assertEquals("GET IN TOUCH", contactObject.getInMessage.getText());
	  
	  contactObject.userCanContactUs(name,email , subject, message);
	  Thread.sleep(3000);
	  
	  Assert.assertEquals("Success! Your details have been submitted successfully.", contactObject.successMessage.getText());
	 
	  contactObject.BackToHomePage();
	  Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color")); 
	  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
//WebDriver driver;
//
//  @BeforeTest
//  public void setUp() {
//      WebDriverManager.chromedriver().setup();
//      driver = new ChromeDriver();
//      driver.manage().window().maximize();
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//      driver.get("http://automationexercise.com");
//  }
//
//
//  @Test
//  public void test1() throws InterruptedException, AWTException {
//      WebElement homePage = driver.findElement(By.xpath("//body"));
//      assertTrue(homePage.isDisplayed());
//
//      driver.findElement(By.xpath("//a[normalize-space()='Contact us']")).click();
//      WebElement getInTouch = driver.findElement(By.xpath("//h2[normalize-space()='Get In Touch']"));
//      assertTrue(getInTouch.isDisplayed());
//
//      driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Shady Elsawy");
//      driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("shadyelsawy536@gmail.com");
//      driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("test");
//      driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("TestNG");
//
//      WebElement uploadFile = driver.findElement(By.xpath("//input[@name='upload_file']"));
//      uploadFile.sendKeys("D:\\DEPI\\AutomationExerciseTests\\AutomationExerciseTests\\src\\test\\java\\AutomationExerciseTests\\AutomationExerciseTests\\resources\\images.png");
//
//      driver.findElement(By.xpath("//input[@name='submit']")).click();
//      Thread.sleep(2000);
//      driver.switchTo().alert().accept();
//
//      Thread.sleep(2000);
//      WebElement successMessage = driver.findElement(By.xpath("(//*[text()='Success! Your details have been submitted successfully.'])[1]"));
//      assertTrue(successMessage.isDisplayed());
//
//      driver.findElement(By.xpath("//span[normalize-space()='Home']")).click();
//      WebElement homePages = driver.findElement(By.xpath("//body"));
//      assertTrue(homePages.isDisplayed());
//  }
//  
//  @AfterTest
//  public void tearDown() throws InterruptedException {
//      Thread.sleep(2000);
//      driver.close();
//  }
}


