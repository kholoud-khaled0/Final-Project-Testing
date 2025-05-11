package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "body > section > div > div > div.col-sm-9.padding-right > div.category-tab.shop-details-tab > div.col-sm-12 > ul > li > a")
    public WebElement review;

    @FindBy(css = "#cartModal > div > div > div.modal-footer > button")
    WebElement continueBtn;

    @FindBy(css = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a > u")
    WebElement viewCartBtn;

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    public WebElement cartBtn;

    @FindBy(css = "body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.choose > ul > li > a")
    WebElement viewProductBtn;

    @FindBy(id = "quantity")
    public WebElement quantityInput;

    @FindBy(css = "body section div div div.col-sm-9.padding-right div.product-details div.col-sm-7 div span button")
    WebElement addToCartBtn;

    @FindBy(css = "#cartModal a[href='/view_cart']")
    WebElement viewCartInModalBtn;

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    public WebElement cartBtnTop;

    @FindBy(xpath = "//a[text()='Write Your Review']")
    public WebElement reviewSectionTitle;

    @FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "review")
    WebElement reviewTextArea;

    @FindBy(id = "button-review")
    WebElement submitReviewButton;

    @FindBy(xpath = "//div[@class='alert-success' and contains(text(), 'Thank you for your review.')]")
    public WebElement reviewSuccessMessage;

    public void clickFirstViewProduct() {
        try {
            WebElement firstViewProduct = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstViewProduct);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstViewProduct);
        } catch (Exception e) {
            System.out.println("Failed to click on first 'View Product' button: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void writeReview(String name, String email, String reviewText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(reviewTextArea));
        reviewTextArea.clear();
        reviewTextArea.sendKeys(reviewText);
    }

    public void submitReview() {
        submitReviewButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOf(reviewSuccessMessage),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert-success']"))
        ));
    }

    public String verifyIncreaseQuantity() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", viewProductBtn);
        js.executeScript("arguments[0].click();", viewProductBtn);
        Thread.sleep(2000);
        quantityInput.clear();
        quantityInput.sendKeys("4");
        js.executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
        js.executeScript("arguments[0].click();", addToCartBtn);
        Thread.sleep(2000);
        return quantityInput.getAttribute("value");
    }

    public void openCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.visibilityOf(viewCartInModalBtn));
            js.executeScript("arguments[0].click();", viewCartInModalBtn);
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", cartBtnTop);
        }
    }
}
