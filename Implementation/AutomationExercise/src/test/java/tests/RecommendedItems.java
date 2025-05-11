package tests;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AddToCartPage;
import pages.HomePage;
import pages.RecommendedItemsAddToCartPage;

public class RecommendedItems extends TestBase {
    HomePage homeObject = new HomePage(driver);
    AddToCartPage addToCartObject = new AddToCartPage(driver);
    RecommendedItemsAddToCartPage recommendedObject = new RecommendedItemsAddToCartPage(driver);

    @Test(priority = 2)
    public void recommendedItemsAddToCart() {
        Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color"));

        recommendedObject.scrollToRecommendedSection();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement recommendedItemsSection = wait.until(
            ExpectedConditions.visibilityOf(recommendedObject.recommendedItemsSection)
        );

        Assert.assertTrue(recommendedItemsSection.isDisplayed(), "'RECOMMENDED ITEMS' section is not visible!");

        
        recommendedObject.addRecommendedProductAndViewCart();

        
        WebElement cartProduct = wait.until(
            ExpectedConditions.visibilityOf(addToCartObject.cartProduct)
        );
        Assert.assertTrue(cartProduct.isDisplayed(), "Recommended product 'Blue Top' is not displayed in cart!");
    }
}
