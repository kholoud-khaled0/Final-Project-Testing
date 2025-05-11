package tests;


import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.List;
import org.testng.Assert;


public class SearchProduct extends TestBase {
	
	HomePage homeObject = new HomePage(driver);
    ProductsPage productsObject = new ProductsPage(driver);

    @Test
    public void userCanSearchForProductSuccessfully() throws InterruptedException {

        Assert.assertTrue(homeObject.homeBtn.isDisplayed());

        homeObject.openProductsPage();

        Assert.assertTrue(productsObject.productsTitle.isDisplayed());

        productsObject.searchForProduct("Winter Top");

        Assert.assertTrue(productsObject.searchResultsContainer.isDisplayed());

        driver.navigate().back();
        
        for (WebElement product : productsObject.allProducts) {
            Assert.assertTrue(product.isDisplayed(), "Product not visible: " + product.getText());
        }
       
    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//    WebDriver driver;
//
//    @BeforeTest
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.get("http://automationexercise.com");
//    }
//
//    @Test
//    public void test1() {
//        WebElement homePage = driver.findElement(By.xpath("//body"));
//        Assert.assertTrue(homePage.isDisplayed());
//
//        driver.findElement(By.xpath("//a[@href='/products']")).click();
//
//        WebElement productPage = driver.findElement(By.xpath("//h2[@class='title text-center']"));
//        Assert.assertTrue(productPage.isDisplayed());
//
//        driver.findElement(By.xpath("//input[@id='search_product']")).sendKeys("Winter Top");
//        driver.findElement(By.xpath("//button[@id='submit_search']")).click();
//
//        WebElement search_product = driver.findElement(By.xpath("//div[@class='features_items']"));
//        Assert.assertTrue(search_product.isDisplayed());
//
//        driver.navigate().back();
//        List<WebElement> allProducts = driver.findElements(By.xpath("//*[@class='productinfo text-center']/p"));
//        for (WebElement w : allProducts) {
//            Assert.assertTrue(w.isDisplayed());
//        }
//    }
//    
//    @AfterTest
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(2000);
//        driver.close();
//    }
}
