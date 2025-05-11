package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutAddressTest extends TestBase {
	@DataProvider(name = "registerData")
    public Object[][] testData() {
        return new Object[][] {
            {"kholoud khaled", "kholoud63@gmail.com", "kholoud155312", 15, "5", "2005", 
             "kholoud", "khaled", "DEPI", "El-Fayoum", "Itsa", "Australia", "Jedcet", "Brde delt", "9900000", "01001386305","I want the order within 10 days","Visa","345 689 692 240","112","5","2027"}
        };
    }
    @Test(dataProvider = "registerData")
    public void verifyAddressDetailsInCheckout(String name, String email, String password, int day, String month, 
            String year, String firstName, String lastName, String company, 
            String address1, String address2, String country, String state, 
            String city, String zipcode, String mobileNumber , String message ,String nameCard, 
            String cardNumber , String cvc ,String expiryMonth , String expiryYear) throws InterruptedException {
        // Initialize page objects
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        RegisterBeforeCheckoutPage checkoutPage = new RegisterBeforeCheckoutPage(driver);
        
        // Test data
       
        
        // 1-3. Launch browser and verify home page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(homePage.homeBtn));
        Assert.assertTrue(homePage.homeBtn.isDisplayed(), "Home page not visible");
        
        // 4. Click Signup/Login
        homePage.openRegisterationPage();
        
        // 5. Register new user
        registerPage.userCanRegister(name, email);
        Assert.assertTrue(registerPage.accountInformationMessage.isDisplayed(), 
                        "Account information page not displayed");
        
        // Fill account information with address details
        registerPage.enterAccountInformation(password, day, month, year, firstName, lastName, company, address1,
                address2, country, state, city, zipcode, mobileNumber);

        
        // 6. Verify account created and continue
        Assert.assertTrue(registerPage.accountCreatedMessage.getText()
                        .contains("ACCOUNT CREATED!"), "Account not created");
        registerPage.continueAccount();
        
        // 7. Verify logged in status
        wait.until(ExpectedConditions.visibilityOf(registerPage.loggedLink));
        Assert.assertTrue(registerPage.loggedLink.getText().contains(name), 
                        "Not logged in successfully");
        
        // 8. Add products to cart
        homePage.openProductsPage();
        addToCartPage.addFirstTwoProductsToCart();
        
        // 9-10. Go to cart and verify
        homePage.openCartPage();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait2.until(ExpectedConditions.visibilityOf(addToCartPage.cartTable));

        Assert.assertTrue(addToCartPage.cartTable.isDisplayed(), "Cart page not displayed");
        // 11. Proceed to checkout
        checkoutPage.clickProceedToCheckoutUsingJS();
        
        // 12. Verify delivery address
        String expectedName = "Mrs. " + firstName + " " + lastName;
        String expectedAddress ="Mrs. "+ firstName + " " + lastName + "\n" + address1 + "\n" + city + " " + state + " " + zipcode + "\n" + mobileNumber;
        
        String actualDeliveryAddress = checkoutPage.getFullNameText() + "\n" +
                                     checkoutPage.getAddress1Text() + "\n" +
                                     checkoutPage.getCityStatePostCodeText() + "\n" +
                                     checkoutPage.getPhoneNumberText();
        
        Assert.assertEquals(actualDeliveryAddress.trim(), expectedAddress.trim(), 
                          "Delivery address doesn't match registration details");
        
        // 13. Verify billing address (should be same as delivery)
        String actualBillingAddress = checkoutPage.getFullNameText() + "\n" +
                                    checkoutPage.getAddress1Text() + "\n" +
                                    checkoutPage.getCityStatePostCodeText() + "\n" +
                                    checkoutPage.getPhoneNumberText();
        
        Assert.assertEquals(actualBillingAddress.trim(), expectedAddress.trim(), 
                          "Billing address doesn't match delivery address");
        
        // 14. Delete account
        registerPage.deleteAcount();
        
        // 15. Verify account deleted and continue
        wait.until(ExpectedConditions.visibilityOf(registerPage.deleteMessage));
        Assert.assertTrue(registerPage.deleteMessage.getText()
                        .equalsIgnoreCase("ACCOUNT DELETED!"), 
                        "Account deletion failed");
        registerPage.continueAccount();
    }
}