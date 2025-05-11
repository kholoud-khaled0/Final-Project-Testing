// DeleteFromCartPage.java

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class DeleteFromCartPage extends PageBase {

    public DeleteFromCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    public WebElement cartBtn;

    @FindBy(xpath = "//*[@id='cart_info']")
    public WebElement cartTable;

    // Get all cart rows
    public List<WebElement> getAllCartRows() {
        return cartTable.findElements(By.xpath(".//tbody/tr"));
    }

    // Remove a product from the cart dynamically
    public void removeProductFromCart(WebElement cartRow) {
        WebElement removeButton = cartRow.findElement(By.xpath(".//a[@class='cart_quantity_delete']"));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", removeButton);
        js.executeScript("arguments[0].click();", removeButton);
    }

    // Navigate to the cart page
    public void openCartPage() {
        cartBtn.click();
    }

    // Retrieve the name of the product in the cart row
    public String getCartProductName(WebElement cartRow) {
        return cartRow.findElement(By.xpath("./td[2]/h4/a")).getText();
    }

    // Verify if Cart page is displayed
    public boolean isCartPageDisplayed() {
        try {
            return cartTable.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
