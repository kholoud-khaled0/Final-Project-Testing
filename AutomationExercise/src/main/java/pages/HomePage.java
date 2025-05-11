// Page Object Class
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class HomePage extends PageBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Signup / Login")
    WebElement signUpLoginBtn;

    @FindBy(linkText = "Home")
    public WebElement homeBtn;

    @FindBy(linkText = "Contact us")
    WebElement contactUsBtn;

    @FindBy(linkText = " Products")
    WebElement productsBtn;
    @FindBy(xpath = "//a[@href='/view_cart']")
    WebElement cartBtn;
    
    @FindBy(linkText = "Test Cases")
    WebElement testCasesBtn;


    @FindBy(xpath = "//*[@id=\"accordian\"]/div[1]/div[1]/h4/a")
    WebElement womenCategory;

    @FindBy(xpath = "//*[@id=\"accordian\"]/div[2]/div[1]/h4/a")
    WebElement menCategory;

    @FindBy(linkText = "TOPS")
    WebElement topsCategoryLink;

    @FindBy(linkText = "TSHIRTS")
    WebElement menSubCategoryLink;

    @FindBy(xpath = "/html/body/section/div/div[2]/div[2]/div/h2")
    WebElement womenPageTitle;

    @FindBy(xpath = "/html/body/section/div/div[2]/div[2]/div/h2")
    WebElement menPageTitle;
    

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")
     public WebElement TestCasesTitle;
     
    @FindBy(id = "scrollUp")
    WebElement arrowButton;

    @FindBy(tagName = "html")
    WebElement htmlElement;
    
    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionTitle;

    @FindBy(id = "susbscribe_email") 
    private WebElement subscriptionEmailField;

    @FindBy(id = "subscribe") 
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[contains(@class,'alert-success') and contains(text(),'You have been successfully subscribed!')]")
    private WebElement subscriptionSuccessMessage;

    public void openRegisterationPage() {
        signUpLoginBtn.click();
    }

    public void openLoginPage() {
        signUpLoginBtn.click();
    }

    public void openContactUsPage() {
        contactUsBtn.click();
    }

    public void openProductsPage() {
        productsBtn.click();
    }
    public void openTestCasesPage() {
        testCasesBtn.click();
    }
    

    public WebElement getCategoryElement(String categoryName) {
        if (categoryName.equalsIgnoreCase("Women")) {
            return womenCategory;
        } else if (categoryName.equalsIgnoreCase("Men")) {
            return menCategory;
        }
        return null;
    }

    public void clickSubCategory(String subCategoryName) {
        WebElement subCategory = driver.findElement(By.xpath("//div[@class='panel-group category-products']//a[contains(text(),'" + subCategoryName + "')]"));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", subCategory);
        js.executeScript("arguments[0].click();", subCategory);
    }

    public WebElement getPageTitle(String title) {
        if (title.equalsIgnoreCase("WOMEN - TOPS PRODUCTS")) {
            return womenPageTitle;
        } else if (title.equalsIgnoreCase("MEN - SHIRTS PRODUCTS")) {
            return menPageTitle;
        }
        return null;
    }
    public void openCartPage() {
        cartBtn.click();
    }
    JavascriptExecutor jse = (JavascriptExecutor) driver;

    public void scrollToBottom() {
        jse.executeScript("document.documentElement.style.scrollBehavior = 'smooth';");
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // Optional: Wait for a short time to see the smooth scroll
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("document.documentElement.style.scrollBehavior = 'auto';"); // Reset
    }

    public void clickArrowButton() {
        jse.executeScript("arguments[0].click();", arrowButton);
    }

    public void scrollToTop() {
        jse.executeScript("document.documentElement.style.scrollBehavior = 'smooth';");
        jse.executeScript("window.scrollTo(0, 0)");
        // Optional: Wait for a short time to see the smooth scroll
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jse.executeScript("document.documentElement.style.scrollBehavior = 'auto';"); // Reset
    }

    public boolean isSubscriptionVisible() {
        WebElement subscriptionElement = driver.findElement(By.tagName("h2"));
        return subscriptionElement.isDisplayed();
    }

    public boolean isFullFledgedTextVisible() {
        WebElement fullFledgedTextElement = driver.findElement(By.xpath("//section[1]/div/div/div/div/div/div[1]/div[1]/h2"));
        return fullFledgedTextElement.isDisplayed();
    }
    
    public boolean isHomeButtonDisplayed() {
        return homeBtn.isDisplayed();
    } 

    public boolean isSubscriptionTitleVisible() {
        return subscriptionTitle.isDisplayed();
    }

    // Enter email into Subscription field
    public void enterSubscriptionEmail(String email) {
        subscriptionEmailField.clear();
        subscriptionEmailField.sendKeys(email);
    }

    // Click the Subscription button
    public void clickSubscriptionButton() {
        subscriptionButton.click();
    }

    // Check if Subscription success message is visible
    public boolean isSubscriptionSuccessMessageVisible() {
        try {
            return subscriptionSuccessMessage.isDisplayed();
        } catch (Exception e) {
            return false; // لو مش موجودة مش يرمي Exception
        }
    }

    // Get the text of Subscription success message
    public String getSubscriptionSuccessMessageText() {
        return subscriptionSuccessMessage.getText();
    }
    
    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
}

