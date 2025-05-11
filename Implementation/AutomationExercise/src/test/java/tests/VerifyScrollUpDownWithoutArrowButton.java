package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class VerifyScrollUpDownWithoutArrowButton extends TestBase{
	HomePage homePage;
    JavascriptExecutor jse; // هنضيف الـ JavascriptExecutor

    @Test
    public void testVerifyScrollUpDownWithoutArrow() {

        homePage = new HomePage(driver);
        jse = (JavascriptExecutor) driver; // هنعرف الـ JavascriptExecutor

        // Step 1 & 2: Launch browser & Navigate to url
        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.getCurrentUrl().contains("automationexercise"));

        // Step 4 & 5: Scroll down & Verify SUBSCRIPTION
        homePage.scrollToBottom();
        Assert.assertTrue(homePage.isSubscriptionVisible());

        // Step 6: Scroll up page to top (WITHOUT ARROW)
        jse.executeScript("window.scrollTo(0, 0)"); // هنستخدم JavascriptExecutor عشان نعمل Scroll لفوق
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/div/div/div/div/div/div[1]/div[1]/h2")));

        // Step 7: Verify that page is scrolled up and text is visible
        Assert.assertTrue(homePage.isFullFledgedTextVisible());
    }
}
