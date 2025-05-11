package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CardPage;
import pages.HomePage;  

public class SubscriptionCartTest extends TestBase {

    CardPage cartPage;
    HomePage homeObject;

    @DataProvider(name = "cartSubscriptionData")
    public Object[][] cartData() {
        return new Object[][] {
            {"samar@example.com", true},
            {"", false}
        };
    }

    @Test(dataProvider = "cartSubscriptionData")
    public void verifySubscriptionInCartPage(String email, boolean expectSuccess) throws InterruptedException {
        homeObject = new HomePage(driver);  
        cartPage = new CardPage(driver);    

        // Step 3: Verify home
        Assert.assertTrue(homeObject.homeBtn.isDisplayed(), "Home page not visible!");

        // Step 4: Click cart
        cartPage.openCartPage();   
        Thread.sleep(2000);

        // Step 5: Scroll to footer
        cartPage.scrollToFooter();  
        Thread.sleep(2000);

        // Step 6: Verify subscription text
        Assert.assertTrue(cartPage.isSubscriptionTitleVisible(), "Subscription title not visible!");  // تعديل هنا لاستخدام cartPage

        // Step 7: Enter email & click
        cartPage.enterSubscriptionEmail(email);  
        cartPage.clickSubscriptionButton();     

        // Step 8: Validate result
        if (expectSuccess) {
            Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible(), "Success message not shown!");  // تعديل هنا لاستخدام cartPage
            Assert.assertEquals(cartPage.getSubscriptionSuccessMessageText(), "You have been successfully subscribed!");  // تعديل هنا لاستخدام cartPage
        } else {
            Assert.assertFalse(cartPage.isSubscriptionSuccessMessageVisible(), "Unexpected success message shown!");  // تعديل هنا لاستخدام cartPage
        }
    }
}

