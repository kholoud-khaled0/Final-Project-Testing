package tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.LoginBeforeCheckoutPage;
import pages.RegisterBeforeCheckoutPage;
import pages.RegisterPage;

public class LoginBeforeCheckout extends TestBase {

    HomePage homeObject = new HomePage(driver);
    LoginPage loginObject = new LoginPage(driver);
    AddToCartPage addToCartObject  = new AddToCartPage(driver);
    RegisterBeforeCheckoutPage checkoutObject  = new RegisterBeforeCheckoutPage(driver);
    LoginBeforeCheckoutPage loginCheckoutObject  = new LoginBeforeCheckoutPage(driver);
    RegisterPage registerObject  = new RegisterPage(driver); 

    @DataProvider(name = "loginData")
    public Object[][] testData() {
        return new Object[][] {
            {"kholoud111@gmail.com", "kholoud155312", "kholoud khaled", "El-Fayoum", "Itsa", "01001386305",
             "I want the order within 10 days", "Visa", "345689692240", "112", "5", "2027"}
        };
    }

    @Test(priority = 3, dataProvider = "loginData")
    public void loginBeforeCheckout_HappyScenario(String email, String password, String fullName,
                                                  String address1, String city, String mobileNumber,
                                                  String message, String nameCard, String cardNumber,
                                                  String cvc, String expiryMonth, String expiryYear) throws InterruptedException {


        

        Assert.assertTrue(homeObject.homeBtn.isDisplayed(), "Home page is not visible!");

        homeObject.openLoginPage();

        Assert.assertEquals(loginObject.loginAccountMessage.getText(), "Login to your account", "Login page is not displayed!");

        loginObject.userCanLogin(email, password);

        Assert.assertEquals(loginObject.loggedLink.getText(), "Logged in as " + fullName, "User is not logged in!");

        homeObject.openProductsPage();
        addToCartObject.addFirstTwoProductsToCart();

        addToCartObject.openCartPage();

        Assert.assertTrue(addToCartObject.cartBtn.isDisplayed(), "Cart page is not displayed!");

        checkoutObject.clickProceedToCheckoutUsingJS();

        Assert.assertEquals("YOUR DELIVERY ADDRESS",loginCheckoutObject.deliveryAddress.getText());
        Assert.assertEquals("YOUR BILLING ADDRESS",loginCheckoutObject.billingAddress.getText());

        List<WebElement> allProducts = checkoutObject.getAllProductRows();
        Assert.assertTrue(allProducts.size() > 0, "Cart is empty! No products found!");

        WebElement firstProductRow = allProducts.get(0);
        WebElement secondProductRow = allProducts.get(1);

        Assert.assertEquals(checkoutObject.getProductName(firstProductRow), "Blue Top", "First product name is incorrect!");
        Assert.assertEquals(checkoutObject.getProductPrice(firstProductRow), "Rs. 500", "First product price is incorrect!");
        Assert.assertEquals(checkoutObject.getProductQuantity(firstProductRow), "1", "First product quantity is incorrect!");
        Assert.assertEquals(checkoutObject.getProductTotal(firstProductRow), "Rs. 500", "First product total is incorrect!");

        Assert.assertEquals(checkoutObject.getProductName(secondProductRow), "Men Tshirt", "Second product name is incorrect!");
        Assert.assertEquals(checkoutObject.getProductPrice(secondProductRow), "Rs. 400", "Second product price is incorrect!");
        Assert.assertEquals(checkoutObject.getProductQuantity(secondProductRow), "1", "Second product quantity is incorrect!");
        Assert.assertEquals(checkoutObject.getProductTotal(secondProductRow), "Rs. 400", "Second product total is incorrect!");
        checkoutObject.Messagedescription(message);
        checkoutObject.PlaceOrder();

        checkoutObject.paymentDetails(nameCard, cardNumber, cvc, expiryMonth, expiryYear);
        checkoutObject.PayAndConfirmOrder();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Congratulations! Your order has been confirmed!')]")));
        Assert.assertTrue(successMessage.getText().contains("Congratulations! Your order has been confirmed!"), "Order confirmation message not displayed!");

//        registerObject.deleteAcount();
//
//        Assert.assertEquals(registerObject.deleteMessage.getText(), "ACCOUNT DELETED!", "Account deletion message not displayed!");
//        registerObject.continueAccount();
    }
}