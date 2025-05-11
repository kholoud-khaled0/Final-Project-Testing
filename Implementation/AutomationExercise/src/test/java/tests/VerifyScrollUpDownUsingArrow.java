package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

public class VerifyScrollUpDownUsingArrow extends TestBase {
    HomePage homePage;

    @Test
    public void testVerifyScrollUpDown() { // هنشيل الـ throws InterruptedException

        homePage = new HomePage(driver);

        // Step 2: Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");

        // Step 3: Verify that home page is visible successfully
        Assert.assertTrue(driver.getCurrentUrl().contains("automationexercise"));

        // Step 4: Scroll down page to bottom
        homePage.scrollToBottom();

        // Step 5: Verify 'SUBSCRIPTION' is visible
        Assert.assertTrue(homePage.isSubscriptionVisible());

        // Step 6: Click on arrow at bottom right side to move upward
        homePage.clickArrowButton();

        // Step 7: Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        homePage.scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // نستخدم WebDriverWait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/div/div/div/div/div/div[1]/div[1]/h2"))); 
        Assert.assertTrue(homePage.isFullFledgedTextVisible());
    }
}