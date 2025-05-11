package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class VerifyCategoryProducts extends TestBase {

    HomePage homeObject;

    @Test(priority = 1)
    public void viewCategoryProducts() throws InterruptedException {
        homeObject = new HomePage(driver);

        // Click 'Women' category
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", homeObject.getCategoryElement("Women"));
        js.executeScript("arguments[0].click();", homeObject.getCategoryElement("Women"));

        Assert.assertTrue(homeObject.getCategoryElement("Women").isDisplayed(),
                "Women category is not visible!");

        homeObject.clickSubCategory("Tops"); // 

        Assert.assertTrue(homeObject.getPageTitle("WOMEN - TOPS PRODUCTS").isDisplayed(),
                "Category page for 'Women - Tops Products' is not displayed!");

        // Click 'Men' category
        js.executeScript("arguments[0].scrollIntoView(true);", homeObject.getCategoryElement("Men"));
        js.executeScript("arguments[0].click();", homeObject.getCategoryElement("Men"));

        homeObject.clickSubCategory("Tshirts"); //
        Assert.assertTrue(homeObject.getPageTitle("MEN - SHIRTS PRODUCTS").isDisplayed(),
                "Category page for 'Men - Shirts Products' is not displayed!");
    }

}
