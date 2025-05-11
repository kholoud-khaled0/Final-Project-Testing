package tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.HomePage;
import pages.ProductPage;

public class VerifyProductquantityinCart extends TestBase {

    HomePage homeObject;
    ProductPage productObject;
    AddToCartPage addToCartObject;

    @Test(priority = 1)
    public void viewProduct() throws InterruptedException {
        homeObject = new HomePage(driver);
        productObject = new ProductPage(driver);
        addToCartObject = new AddToCartPage(driver);

        Assert.assertEquals(homeObject.homeBtn.getCssValue("color"), "rgba(255, 165, 0, 1)", "Home page color is incorrect!");

        String expectedQuantity = productObject.verifyIncreaseQuantity(); 

        productObject.openCartPage();

        List<WebElement> cartRows = addToCartObject.getAllCartRows();
        WebElement firstCartRow = cartRows.get(0);

        String actualQuantity = addToCartObject.getCartProductQuantity(firstCartRow);
        Assert.assertEquals(actualQuantity, expectedQuantity, "Product quantity does not match the expected value!");
    }
}
