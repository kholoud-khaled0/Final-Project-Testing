package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterBeforeCheckoutPage extends PageBase {

    public RegisterBeforeCheckoutPage(WebDriver driver) {
        super(driver);
    }

   
    @FindBy(xpath = "//*[@id='do_action']/div[1]/div/div/a")
    WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//*[@id='address_delivery']/li[2]")
    public WebElement addressName;

    @FindBy(xpath = "//*[@id='address_delivery']/li[4]")
    public WebElement address1;

    @FindBy(xpath = "//*[@id='address_delivery']/li[6]")
    public WebElement cityStatePostCode;

    @FindBy(xpath = "//*[@id='address_delivery']/li[8]")
    public WebElement phoneNumber;

    @FindBy(id = "cart_info")
    WebElement cartInfoTable;

    @FindBy(name = "message")
    WebElement messageTxt;
    
    @FindBy(xpath = "//*[@id=\"cart_items\"]/div/div[7]/a")
    WebElement placeOrderBtn;

    @FindBy(name = "name_on_card")
    WebElement nameOnCardTxt;

    @FindBy(name = "card_number")
    WebElement card_numberTxt;

    @FindBy(name = "cvc")
    WebElement cvcTxt;

    @FindBy(name = "expiry_month")
    WebElement expiry_monthTxt;

    @FindBy(name = "expiry_year")
    WebElement expiry_yearTxt;

    @FindBy(id = "submit")
    WebElement submitBtn;

    @FindBy(id = "success_message")
    public WebElement successMessage;

    // تحسين: استخدام WebDriverWait بدلاً من JavascriptExecutor
    public void clickProceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
        element.click();
    }
    //sara
    @FindBy(name = "message")
    public WebElement commentTextArea;

//    @FindBy(xpath = "//a[contains(text(),'Place Order')]")
//    public WebElement placeOrderBtn;

    @FindBy(name = "name_on_card")
    public WebElement nameOnCard;

    @FindBy(name = "card_number")
    public WebElement cardNumber;

    @FindBy(name = "cvc")
    public WebElement cvc;

    @FindBy(name = "expiry_month")
    public WebElement expiryMonth;

    @FindBy(name = "expiry_year")
    public WebElement expiryYear;

    @FindBy(id = "submit")
    public WebElement payBtn;

    @FindBy(css = "a[href*='/download_invoice']")
    public WebElement downloadInvoiceBtn;
        


    // Address Verification
    public String getFullNameText() {
        return addressName.getText();
    }

    public String getAddress1Text() {
        return address1.getText();
    }

    public String getCityStatePostCodeText() {
        return cityStatePostCode.getText();
    }

    public String getPhoneNumberText() {
        return phoneNumber.getText();
    }

    // Order Review - Dynamic for Multiple Products
    public List<WebElement> getAllProductRows() {
        return cartInfoTable.findElements(By.xpath(".//tbody/tr"));
    }

    public String getProductName(WebElement productRow) {
        return productRow.findElement(By.xpath("./td[2]/h4/a")).getText();
    }

    public String getProductPrice(WebElement productRow) {
        return productRow.findElement(By.xpath("./td[3]/p")).getText();
    }

    public String getProductQuantity(WebElement productRow) {
        return productRow.findElement(By.xpath("./td[4]/button")).getText();
    }

    public String getProductTotal(WebElement productRow) {
        return productRow.findElement(By.xpath("./td[5]/p")).getText();
    }

    public boolean isCartDisplayed() {
        return cartInfoTable.isDisplayed();
    }

    
    public void clickProceedToCheckoutUsingJS() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    
    public void Messagedescription(String message) {
        messageTxt.sendKeys(message);
    }

    
    public void PlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement placeOrderBtnVisible = wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
        placeOrderBtnVisible.click();
    }

    
    public void paymentDetails(String nameCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        nameOnCardTxt.sendKeys(nameCard);
        card_numberTxt.sendKeys(cardNumber);
        cvcTxt.sendKeys(cvc);
        expiry_monthTxt.sendKeys(expiryMonth);
        expiry_yearTxt.sendKeys(expiryYear);
    }

    
    public void PayAndConfirmOrder() {
        submitBtn.click();
    }
    public void enterComment(String comment) {
        commentTextArea.sendKeys(comment);
    }

    public void clickPlaceOrder() {
        placeOrderBtn.click();
    }

    public void enterPaymentDetails(String name, String number, String cvcNum, String month, String year) {
        nameOnCard.sendKeys(name);
        cardNumber.sendKeys(number);
        cvc.sendKeys(cvcNum);
        expiryMonth.sendKeys(month);
        expiryYear.sendKeys(year);
    }

    public void clickPayAndConfirm() {
        payBtn.click();
    }

    public void clickDownloadInvoice() {
        downloadInvoiceBtn.click();
    }
    
    @FindBy(xpath = "//a[contains(@href,'/login') or contains(text(),'Register')]")
    public WebElement Register_LoginBtn;

    public void Register_Login() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(Register_LoginBtn)).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", Register_LoginBtn);
        }
    }

}
