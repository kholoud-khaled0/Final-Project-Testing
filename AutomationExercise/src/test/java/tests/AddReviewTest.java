package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.ProductsPage;

import java.time.Duration;

public class AddReviewTest extends TestBase {

    ProductPage productPage;
    HomePage homeObject;
    ProductsPage productsObject;

    @DataProvider(name = "reviewData")
    public Object[][] reviewTestData() {
        return new Object[][] {
            {"Samar", "samar@example.com", "Great product!", true},
            {"", "", "", false}
        };
    }

    @Test(dataProvider = "reviewData")
    public void addReviewToProduct(String name, String email, String reviewText, boolean expectSuccess) {
        productPage = new ProductPage(driver);
        homeObject = new HomePage(driver);
        productsObject = new ProductsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open products page
        homeObject.openProductsPage();
        wait.until(ExpectedConditions.visibilityOf(productsObject.allProductsHeader));
        Assert.assertTrue(productsObject.allProductsHeader.isDisplayed(), "All Products page not visible!");

        // View product
        productPage.clickFirstViewProduct();
        wait.until(ExpectedConditions.visibilityOf(productPage.reviewSectionTitle));
        Assert.assertTrue(productPage.reviewSectionTitle.isDisplayed(), "Review section not visible!");

        // Fill review form
        productPage.writeReview(name, email, reviewText);

        // Submit review
        productPage.submitReview();

        // Verify the result
        if (expectSuccess) {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOf(productPage.reviewSuccessMessage));
            Assert.assertTrue(successMessage.isDisplayed(), "Success message not shown!");
        } else {
            boolean isSuccessVisible = false;
            try {
                isSuccessVisible = productPage.reviewSuccessMessage.isDisplayed();
            } catch (Exception e) {
                // No message appeared, as expected
            }
            Assert.assertFalse(isSuccessVisible, "Unexpected success message displayed for invalid input!");
        }
    }
}
