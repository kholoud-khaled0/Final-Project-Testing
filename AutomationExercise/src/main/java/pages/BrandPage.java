package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BrandPage extends PageBase {

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getBrandLinks() {
        return driver.findElements(By.xpath("//div[@class='brands_products']//ul[@class='nav nav-pills nav-stacked']/li/a"));
    }

    public List<WebElement> getDisplayedProducts() {
        return driver.findElements(By.xpath("//div[@class='features_items']/div"));
    }
}
