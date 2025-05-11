package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class DownloadInvoiceTest  extends TestBase {

    HomePage homePage;
    AddToCartPage addToCartPage;
    RegisterPage registerPage;
    RegisterBeforeCheckoutPage registerBeforeCheckoutPage;
    LoginPage loginPage;
    
    @DataProvider(name = "registerData")
    public Object[][] testData() {
        return new Object[][] {
            {"kholoud khaled", "kholoud63@gmail.com", "kholoud155312", 15, "5", "2005", 
             "kholoud", "khaled", "DEPI", "El-Fayoum", "Itsa", "Australia", "Jedcet", "Brde delt", "9900000", "01001386305","I want the order within 10 days","Visa","345 689 692 240","112","5","2027"}
        };
    }
    @Test(dataProvider = "registerData")
    public void downloadInvoiceAfterPurchase(String name, String email, String password, int day, String month, 
            String year, String firstName, String lastName, String company, 
            String address1, String address2, String country, String state, 
            String city, String zipcode, String mobileNumber , String message ,String nameCard, 
            String cardNumber , String cvc ,String expiryMonth , String expiryYear) throws InterruptedException {
        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'
        //    (Handled in TestBase)
         
        homePage = new HomePage(driver);
        addToCartPage = new AddToCartPage(driver);
        registerPage = new RegisterPage(driver);
        registerBeforeCheckoutPage = new RegisterBeforeCheckoutPage(driver);
        loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
       
       
        // 3. Verify that home page is visible successfully
        Assert.assertEquals("rgba(255, 165, 0, 1)", homePage.homeBtn.getCssValue("color"));

        // 4. Add products to cart
        homePage.openProductsPage();
        addToCartPage.addFirstTwoProductsToCart();

        // 5. Click 'Cart' button
        addToCartPage.openCartPage();

        // 6. Verify that cart page is displayed
        List<WebElement> cartRows = addToCartPage.getAllCartRows();
        Assert.assertFalse(cartRows.isEmpty(), "Cart page is not displayed!");

        // 7. Click Proceed To Checkout
        registerBeforeCheckoutPage.clickProceedToCheckout();

        // 8. Click 'Register / Login' button
        registerBeforeCheckoutPage.Register_Login();

        // 9. Fill all details in Signup and create account
       

        registerPage.userCanRegister(name, email);
        Assert.assertEquals("ENTER ACCOUNT INFORMATION", registerPage.accountInformationMessage.getText());
        
        registerPage.enterAccountInformation(password, day, month, year, firstName, lastName, company, address1,
                                                address2, country, state, city, zipcode, mobileNumber);

        // 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        String success = "Account Created!";
        Assert.assertEquals(success.toUpperCase(), registerPage.accountCreatedMessage.getText());

        // 11. Verify ' Logged in as username' at top
        registerPage.continueAccount();
        Assert.assertEquals("Logged in as " + name, registerPage.loggedLink.getText());
//
//        // 12. Click 'Cart' button
        homePage.openCartPage();
//
//        // 13. Click 'Proceed To Checkout' button
        registerBeforeCheckoutPage.clickProceedToCheckout();
//
//        // 14. Verify Address Details and Review Your Order
        String expectedAddress ="Mrs. "+ firstName + " " + lastName + "\n" + address1 + "\n" + city + " " + state + " " + zipcode + "\n" + mobileNumber;
        String actualDeliveryAddress = registerBeforeCheckoutPage.addressName.getText() + "\n" +
                registerBeforeCheckoutPage.address1.getText() + "\n" +
                registerBeforeCheckoutPage.cityStatePostCode.getText() + "\n" +
                registerBeforeCheckoutPage.phoneNumber.getText();

        Assert.assertEquals(actualDeliveryAddress.trim(), expectedAddress.trim(),
                "Delivery address doesn't match registration details");

        String actualBillingAddress = registerBeforeCheckoutPage.addressName.getText() + "\n" +
                registerBeforeCheckoutPage.address1.getText() + "\n" +
                registerBeforeCheckoutPage.cityStatePostCode.getText() + "\n" +
                registerBeforeCheckoutPage.phoneNumber.getText();

        Assert.assertEquals(actualBillingAddress.trim(), expectedAddress.trim(),
                "Billing address doesn't match delivery address");
//
        // 15. Enter description in comment text area and click 'Place Order'
        String comment = "I want my order delivered ASAP.";
        registerBeforeCheckoutPage.enterComment(comment);
        registerBeforeCheckoutPage.clickPlaceOrder();

        // 16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        
        registerBeforeCheckoutPage.enterPaymentDetails(nameCard, cardNumber, cvc, expiryMonth, expiryYear);

        // 17. Click 'Pay and Confirm Order' button
        registerBeforeCheckoutPage.clickPayAndConfirm();

        // 18. Verify success message 'Your order has been placed successfully!'
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Congratulations! Your order has been confirmed!')]")));
        Assert.assertTrue(successMessage.getText().contains("Congratulations! Your order has been confirmed!"), "Order confirmation message not displayed!");

        // 19. Click 'Download Invoice' button and verify invoice is downloaded successfully.
        registerBeforeCheckoutPage.clickDownloadInvoice();
        Thread.sleep(2000); // give time for download to complete.  Add more robust download verification if possible

        // 20. Click 'Continue' button
        registerPage.continueAccount();

        // 21. Click 'Delete Account' button
        loginPage.deleteAccount();

        // 22. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Assert.assertTrue(loginPage.deleteMessage.getText().equalsIgnoreCase("Account Deleted!"), "Account deletion failed");
        loginPage.continueAccount();
    }
}
