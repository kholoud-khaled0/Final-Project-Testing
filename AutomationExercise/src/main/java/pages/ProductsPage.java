package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends PageBase {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='All Products']")
    public WebElement allProductsHeader;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    public WebElement searchedProductsHeader;

    @FindBy(id = "search_product")
    WebElement searchInput;

    @FindBy(id = "submit_search")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='product-image-wrapper']")
    List<WebElement> searchResultProducts;
    
    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement productsTitle;

    @FindBy(xpath = "//div[@class='features_items']")
    public WebElement searchResultsContainer;
    
    @FindBy(xpath = "//*[@class='productinfo text-center']/p")
    public List<WebElement> allProducts;



    public void searchForProduct(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
        searchButton.click();
    }

    public List<WebElement> getSearchResultProducts() {
        return searchResultProducts;
    }

    public void addSearchResultsToCart() throws InterruptedException {
        for (WebElement product : searchResultProducts) {
            WebElement addBtn = product.findElement(org.openqa.selenium.By.xpath(".//a[contains(@class,'add-to-cart')]"));
            addBtn.click();
            Thread.sleep(1000);
            WebElement continueBtn = driver.findElement(org.openqa.selenium.By.xpath("//button[text()='Continue Shopping']"));
            continueBtn.click();
        }
    }

    public void openCartPage() {
        driver.findElement(org.openqa.selenium.By.xpath("//a[@href='/view_cart']")).click();
    }
   

}
