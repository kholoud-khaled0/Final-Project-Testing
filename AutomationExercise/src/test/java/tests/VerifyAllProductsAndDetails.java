package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductPage;
import pages.ProductsPage;

public class VerifyAllProductsAndDetails extends TestBase {

    HomePage homeObject;
    ProductsPage productsObject;
    ProductPage productObject;

    @Test
    public void test1() throws InterruptedException {

        homeObject = new HomePage(driver);
        productsObject = new ProductsPage(driver);
        productObject = new ProductPage(driver);

        // Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(homePage.isDisplayed());

        // Click on 'Products' button
        homeObject.openProductsPage();

        // Verify user is navigated to ALL PRODUCTS page successfully
        WebElement productPage = driver.findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(productPage.isDisplayed());

        // The products list is visible
        Assert.assertTrue(productsObject.productsTitle.isDisplayed());

        // Click on 'View Product' of first product using JavaScript
        WebElement viewProductButton = driver.findElement(By.xpath("//*[@href='/product_details/1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductButton);

        // Wait a bit to ensure the page loads
        Thread.sleep(2000);

        // User is landed to product detail page
        Assert.assertTrue(productObject.review.isDisplayed());

        // Verify that details are visible: product name, category, price, availability, condition, brand
        WebElement productName = driver.findElement(By.xpath("//h2[normalize-space()='Blue Top']"));
        Assert.assertTrue(productName.isDisplayed());

        WebElement category = driver.findElement(By.xpath("//p[normalize-space()='Category: Women > Tops']"));
        Assert.assertTrue(category.isDisplayed());

        WebElement price = driver.findElement(By.xpath("//span[normalize-space()='Rs. 500']"));
        Assert.assertTrue(price.isDisplayed());

        WebElement availability = driver.findElement(By.xpath("//b[normalize-space()='Availability:']"));
        Assert.assertTrue(availability.isDisplayed());

        WebElement condition = driver.findElement(By.xpath("//b[normalize-space()='Condition:']"));
        Assert.assertTrue(condition.isDisplayed());

        WebElement brand = driver.findElement(By.xpath("//b[normalize-space()='Brand:']"));
        Assert.assertTrue(brand.isDisplayed());
    }
}
