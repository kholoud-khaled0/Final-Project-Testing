package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.DeleteFromCartPage;
import pages.HomePage;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DeleteFromCart extends TestBase {

    HomePage homePage;
    AddToCartPage addToCartPage;
    DeleteFromCartPage deleteFromCartPage;

    @Test(priority = 1)
    public void userCanDeleteProductFromCart() throws InterruptedException {

        homePage = new HomePage(driver);
        addToCartPage = new AddToCartPage(driver);
        deleteFromCartPage = new DeleteFromCartPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 3. Verify that home page is visible successfully
        Assert.assertEquals(homePage.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)", "Home page is not displayed properly!");

        // 4. Add products to cart
        homePage.openProductsPage();
        addToCartPage.addFirstTwoProductsToCart();

        // 5. Click 'Cart' button
        addToCartPage.openCartPage();

        // 6. Verify that cart page is displayed
        wait.until(ExpectedConditions.visibilityOf(deleteFromCartPage.cartTable));
        Assert.assertTrue(deleteFromCartPage.isCartPageDisplayed(), "Cart page is not displayed!");

        // 7. Click 'X' button corresponding to particular product
        List<WebElement> cartRowsBefore = deleteFromCartPage.getAllCartRows();
        Assert.assertEquals(cartRowsBefore.size(), 2, "Expected 2 products before deletion!");

        deleteFromCartPage.removeProductFromCart(cartRowsBefore.get(0));

        // 8. Wait and verify product removed
        wait.until(driver -> deleteFromCartPage.getAllCartRows().size() == 1);

        List<WebElement> cartRowsAfter = deleteFromCartPage.getAllCartRows();
        Assert.assertEquals(cartRowsAfter.size(), 1, "Product was not removed successfully from cart!");
    }
}
