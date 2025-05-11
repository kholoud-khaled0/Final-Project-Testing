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
import pages.RegisterBeforeCheckoutPage;
import pages.RegisterPage;

public class RegisterBeforeCheckout extends TestBase {
    HomePage homeObject = new HomePage(driver);
    RegisterBeforeCheckoutPage checkOutB_Object = new RegisterBeforeCheckoutPage(driver);
    RegisterPage registerObject = new RegisterPage(driver);
    AddToCartPage addToCartObject = new AddToCartPage(driver);

    @DataProvider(name = "registerData")
    public Object[][] testData() {
        return new Object[][] {
            {"kholoud khaled", "kholoud69@gmail.com", "kholoud155312", 15, "5", "2005", 
             "kholoud", "khaled", "DEPI", "El-Fayoum", "Itsa", "Australia", "Jedcet", "Brde delt", "9900000", "01001386305","I want the order within 10 days","Visa","345 689 692 240","112","5","2027"}
        };
    }

    @Test(priority = 1, dataProvider = "registerData")
    public void Register_before_Checkout(String name, String email, String password, int day, String month, 
                                          String year, String firstName, String lastName, String company, 
                                          String address1, String address2, String country, String state, 
                                          String city, String zipcode, String mobileNumber , String message ,String nameCard, 
                                          String cardNumber , String cvc ,String expiryMonth , String expiryYear) throws InterruptedException {
      
        Assert.assertEquals("rgba(255, 165, 0, 1)", homeObject.homeBtn.getCssValue("color"));

        homeObject.openRegisterationPage();
        Assert.assertEquals("New User Signup!", registerObject.newUserMessage.getText());
        
        registerObject.userCanRegister(name, email); 
        Assert.assertEquals("ENTER ACCOUNT INFORMATION", registerObject.accountInformationMessage.getText());
        
        registerObject.enterAccountInformation(password, day, month, year, firstName, lastName, company, address1,
                                                address2, country, state, city, zipcode, mobileNumber);
        
        String success = "Account Created!";
        Assert.assertEquals(success.toUpperCase(), registerObject.accountCreatedMessage.getText());
        
        registerObject.continueAccount();
        Assert.assertEquals("Logged in as " + name, registerObject.loggedLink.getText());
        
        homeObject.openProductsPage();
        addToCartObject.addFirstTwoProductsToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 

        addToCartObject.openCartPage();
        checkOutB_Object.clickProceedToCheckoutUsingJS();
        Assert.assertEquals("rgba(255, 165, 0, 1)", addToCartObject.cartBtn.getCssValue("color"));

        Assert.assertEquals(checkOutB_Object.getFullNameText(), "Mrs. " + firstName + " " + lastName);
        Assert.assertTrue(checkOutB_Object.getAddress1Text().contains(address1));
        Assert.assertTrue(checkOutB_Object.getCityStatePostCodeText().contains(city));
        Assert.assertEquals(checkOutB_Object.getPhoneNumberText(), mobileNumber);
        Assert.assertTrue(checkOutB_Object.isCartDisplayed(), "Cart table is not displayed");

        List<WebElement> allProducts = checkOutB_Object.getAllProductRows();
        Assert.assertTrue(allProducts.size() > 0, "No products found in cart!");

        WebElement firstProductRow = allProducts.get(0);
        WebElement secondProductRow = allProducts.get(1);

        Assert.assertEquals(checkOutB_Object.getProductName(firstProductRow), "Blue Top");
        Assert.assertEquals(checkOutB_Object.getProductPrice(firstProductRow), "Rs. 500");
        Assert.assertEquals(checkOutB_Object.getProductQuantity(firstProductRow), "1");
        Assert.assertEquals(checkOutB_Object.getProductTotal(firstProductRow), "Rs. 500");
        
        Assert.assertEquals(checkOutB_Object.getProductName(secondProductRow), "Men Tshirt");
        Assert.assertEquals(checkOutB_Object.getProductPrice(secondProductRow), "Rs. 400");
        Assert.assertEquals(checkOutB_Object.getProductQuantity(secondProductRow), "1");
        Assert.assertEquals(checkOutB_Object.getProductTotal(secondProductRow), "Rs. 400");

        checkOutB_Object.Messagedescription(message);
        checkOutB_Object.PlaceOrder();

        checkOutB_Object.paymentDetails(nameCard, cardNumber, cvc, expiryMonth, expiryYear);
        checkOutB_Object.PayAndConfirmOrder();

        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successMessage = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Congratulations! Your order has been confirmed!')]")));
        Assert.assertTrue(successMessage.getText().contains("Congratulations! Your order has been confirmed!"), "Order confirmation message not displayed!");

        registerObject.deleteAcount();
        Assert.assertTrue(registerObject.deleteMessage.getText().equalsIgnoreCase("Account Deleted!"));

        registerObject.continueAccount();
    }
}
