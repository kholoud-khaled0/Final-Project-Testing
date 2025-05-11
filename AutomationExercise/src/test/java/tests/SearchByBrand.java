package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.BrandPage;
import java.util.List;

public class SearchByBrand extends TestBase {

    HomePage homeObject;
    BrandPage brandPageObject;

    @Test(priority = 1)
    public void searchByBrand() throws InterruptedException {
        homeObject = new HomePage(driver);
        brandPageObject = new BrandPage(driver);

        homeObject.openProductsPage();
        Thread.sleep(2000);

        List<WebElement> brandLinks = brandPageObject.getBrandLinks();
        Assert.assertTrue(brandLinks.size() > 0, "No brand links found in sidebar!");

        String firstBrand = brandLinks.get(0).getText();

        // Click by JavaScript Executor
        WebElement firstBrandLink = brandLinks.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstBrandLink);

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("brand"), "Not navigated to brand page!");
        Assert.assertTrue(brandPageObject.getDisplayedProducts().size() > 0, "No products displayed for brand: " + firstBrand);

        // Navigate back
        driver.navigate().back(); 
        Thread.sleep(2000);

        brandLinks = brandPageObject.getBrandLinks();
        String secondBrand = brandLinks.get(1).getText();

        WebElement secondBrandLink = brandLinks.get(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondBrandLink);

        Thread.sleep(2000);

        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("brand"), "Not navigated to second brand page!");
        Assert.assertTrue(brandPageObject.getDisplayedProducts().size() > 0, "No products displayed for brand: " + secondBrand);
    }
}
