package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CardPage {
	   WebDriver driver;

	    public CardPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(xpath = "//a[@href='/view_cart']")
	    WebElement cartButton;

	    @FindBy(xpath = "//h2[text()='Subscription']")
	    WebElement subscriptionTitle;

	    @FindBy(id = "susbscribe_email")
	    WebElement subscriptionEmailInput;

	    @FindBy(id = "subscribe")
	    WebElement subscriptionButton;

	    @FindBy(xpath = "//*[contains(text(),'You have been successfully subscribed!')]")
	    WebElement subscriptionSuccessMessage;

	    public void openCartPage() {
	        cartButton.click();
	    }

	    public void scrollToFooter() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    }

	    public boolean isSubscriptionTitleVisible() {
	        return subscriptionTitle.isDisplayed();
	    }

	    public void enterSubscriptionEmail(String email) {
	        subscriptionEmailInput.clear();
	        subscriptionEmailInput.sendKeys(email);
	    }

	    public void clickSubscriptionButton() {
	        subscriptionButton.click();
	    }

	    public boolean isSubscriptionSuccessMessageVisible() {
	        return subscriptionSuccessMessage.isDisplayed();
	    }

	    public String getSubscriptionSuccessMessageText() {
	        return subscriptionSuccessMessage.getText();
	    }
	}

