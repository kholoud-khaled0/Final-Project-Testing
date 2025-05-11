package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

public class SubscriptionHomeTest extends TestBase {

    HomePage homeObject;

    @DataProvider(name = "subscriptionData")
    public Object[][] subscriptionTestData() {
        return new Object[][] {
            {"samar@example.com", true},    // Positive case
            {"", false}                     // Negative case: Empty email
        };
    }

    @Test(dataProvider = "subscriptionData")
    public void verifySubscriptionInHomePage(String email, boolean expectSuccess) {
        homeObject = new HomePage(driver);

        // Step 1: Verify Home Page is visible
        Assert.assertTrue(homeObject.isHomeButtonDisplayed(), "Home page is not visible!");

        // Step 2: Scroll down to footer
        homeObject.scrollToFooter();

        // Step 3: Verify 'SUBSCRIPTION' section is visible
        Assert.assertTrue(homeObject.isSubscriptionTitleVisible(), "Subscription title is not visible!");

        // Step 4: Enter email and click Subscribe
        homeObject.enterSubscriptionEmail(email);
        homeObject.clickSubscriptionButton();

        // Step 5: Verify Subscription result
        if (expectSuccess) {
            Assert.assertTrue(homeObject.isSubscriptionSuccessMessageVisible(), "Success message not shown!");
            Assert.assertEquals(homeObject.getSubscriptionSuccessMessageText(), "You have been successfully subscribed!");
        } else {
            Assert.assertFalse(homeObject.isSubscriptionSuccessMessageVisible(), "Unexpected success message shown!");
        }
    }
}
