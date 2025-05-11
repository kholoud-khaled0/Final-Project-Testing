package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginBeforeCheckoutPage extends PageBase {

    public LoginBeforeCheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='address_delivery']/li[2]")
    public WebElement addressName;

    @FindBy(xpath = "//*[@id='address_delivery']/li[4]")
    public WebElement address1;

    @FindBy(xpath = "//*[@id='address_delivery']/li[6]")
    public WebElement cityStatePostCode;

    @FindBy(xpath = "//*[@id='address_delivery']/li[8]")
    public WebElement phoneNumber;
    
    @FindBy(xpath = "//div[@id='address_invoice']/div[contains(@class, 'address_firstname')]")
	
    public WebElement billingAddressName;

    @FindBy(xpath = "//div[@id='address_invoice']/div[contains(@class, 'address_address1')]")
    public WebElement billingAddress1;

    @FindBy(xpath = "//div[@id='address_invoice']/div[contains(@class, 'address_city')]")
    public WebElement billingCityStatePostCode;

    @FindBy(xpath = "//div[@id='address_invoice']/div[contains(@class, 'address_phone')]")
    public WebElement billingPhoneNumber;
    
    @FindBy(xpath = "//*[@id=\"address_delivery\"]/li[1]/h3")
    public WebElement deliveryAddress;
    
    @FindBy(xpath = "//*[@id=\"address_invoice\"]/li[1]/h3")
    public WebElement billingAddress;

    public String getAddressNameText() {
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
    
    public String getBillingAddressNameText() {
        return billingAddressName.getText();
    }

    public String getBillingAddress1Text() {
        return billingAddress1.getText();
    }

    public String getBillingCityStatePostCodeText() {
        return billingCityStatePostCode.getText();
    }

    public String getBillingPhoneNumberText() {
        return billingPhoneNumber.getText();
    }
}
