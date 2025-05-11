package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage extends PageBase {

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    public WebElement cartBtn;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='product-image-wrapper']")
    List<WebElement> allProducts;

    @FindBy(xpath = "//*[@id='cart_info']")
    public WebElement cartTable;

    @FindBy(xpath = "//*[@id=\"cartModal\"]/div/div/div[3]/button")
    WebElement continueShoppingBtn;

    @FindBy(xpath = "//a[@href='/view_cart']")
    WebElement viewCartButton;

    @FindBy(xpath = "//a[contains(text(), 'Blue Top')]")
    public WebElement cartProduct;

    public void addFirstTwoProductsToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int addedProducts = 0;

        for (int i = 0; i < 2; i++) {
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='features_items']//div[@class='product-image-wrapper']"));
            WebElement product = products.get(i);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

            Actions actions = new Actions(driver);
            actions.moveToElement(product).perform();

            WebElement addButton = product.findElement(By.xpath(".//a[contains(@class,'add-to-cart')]"));

            wait.until(ExpectedConditions.elementToBeClickable(addButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);

            wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
            Thread.sleep(1000);

            addedProducts++;
        }
    }

    public void openCartPage() {
        cartBtn.click();
    }

    public List<WebElement> getAllCartRows() {
        return cartTable.findElements(By.xpath(".//tbody/tr"));
    }

    public String getCartProductName(WebElement cartRow) {
        return cartRow.findElement(By.xpath("./td[2]/h4/a")).getText();
    }

    public String getCartProductPrice(WebElement cartRow) {
        return cartRow.findElement(By.xpath("./td[3]/p")).getText();
    }

    public String getCartProductQuantity(WebElement cartRow) {
        return cartRow.findElement(By.xpath("./td[4]/button")).getText();
    }

    public String getCartProductTotal(WebElement cartRow) {
        return cartRow.findElement(By.xpath("./td[5]/p")).getText();
    }

    public void viewCart() {
        viewCartButton.click();
    }

    public boolean isCartTableDisplayed() {
        try {
            return cartTable.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
