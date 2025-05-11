package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecommendedItemsAddToCartPage extends PageBase {

    public RecommendedItemsAddToCartPage(WebDriver driver) {
        super(driver);
    }

    
    @FindBy(xpath = "//h2[text()='recommended items']")
    public WebElement recommendedItemsSection;

    
    @FindBy(xpath = "//div[contains(@class, 'recommended_items')]//a[@data-product-id='1']")
    WebElement addToCartRecommendedProduct;

    
    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    WebElement viewCartBtn;

    
    public void scrollToRecommendedSection() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", recommendedItemsSection);
    }

    
    public void addRecommendedProductAndViewCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addToCartRecommendedProduct);

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(viewCartBtn));
        viewCartBtn.click();
    }
}
