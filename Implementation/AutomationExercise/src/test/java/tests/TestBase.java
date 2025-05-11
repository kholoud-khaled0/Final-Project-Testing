package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;

public class TestBase {

	WebDriver driver = new ChromeDriver();
	String baseUrl = "https://www.automationexercise.com/";
  @BeforeTest
  public void OpenBrowser() {
	  driver.manage().window().maximize();
	  driver.navigate().to(baseUrl);
  }

  @AfterTest
  public void CloseBrowser() {
	  driver.quit();
  }

}
