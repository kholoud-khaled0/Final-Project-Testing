package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.HomePage;
import java.util.List;
import org.openqa.selenium.WebElement;

public class AddToCart extends TestBase {

    HomePage homeObject;
    AddToCartPage addToCartObject;

    @Test(priority = 1)
    public void addToCart() throws InterruptedException {
        homeObject = new HomePage(driver);
        addToCartObject = new AddToCartPage(driver);

        Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color"));

        homeObject.openProductsPage();

        addToCartObject.addFirstTwoProductsToCart();  

        addToCartObject.openCartPage(); 
        Thread.sleep(3000);

        List<WebElement> cartRows = addToCartObject.getAllCartRows();

        Assert.assertEquals(cartRows.size(), 2, "Expected 2 products in the cart!");

        for (WebElement cartRow : cartRows) {
            String name = addToCartObject.getCartProductName(cartRow);
            String price = addToCartObject.getCartProductPrice(cartRow);
            String quantity = addToCartObject.getCartProductQuantity(cartRow);
            String total = addToCartObject.getCartProductTotal(cartRow);

            System.out.println("Product: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Total: " + total);

            Assert.assertFalse(name.isEmpty(), "Product name is empty!");
            Assert.assertTrue(price.startsWith("Rs."), "Product price format wrong!");
            Assert.assertEquals(quantity, "1", "Product quantity is not 1!");
            Assert.assertTrue(total.startsWith("Rs."), "Product total format wrong!");
        }
    }
}
