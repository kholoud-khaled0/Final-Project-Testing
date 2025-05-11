package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class RegisterPage extends PageBase {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "name")
    WebElement usernameTxt;

    @FindBy(name = "email")
    List<WebElement> emails;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/button")
    WebElement signupBtn;

    @FindBy(id = "id_gender2")
    WebElement rediusBtn;

    @FindBy(id = "password")
    WebElement passwordTxt;

    @FindBy(id = "days")
    WebElement daysList;

    @FindBy(id = "months")
    WebElement monthsList;

    @FindBy(id = "years")
    WebElement yearsList;

    @FindBy(id = "newsletter")
    WebElement newsletterCheckBox;

    @FindBy(id = "optin")
    WebElement optinCheckBox;

    @FindBy(id = "first_name")
    WebElement first_nameTxt;

    @FindBy(id = "last_name")
    WebElement last_nameTxt;

    @FindBy(id = "company")
    WebElement companyTxt;

    @FindBy(id = "address1")
    WebElement address1Txt;

    @FindBy(id = "address2")
    WebElement address2Txt;

    @FindBy(id = "country")
    WebElement countryList;

    @FindBy(id = "state")
    WebElement stateTxt;

    @FindBy(id = "city")
    WebElement cityTxt;

    @FindBy(id = "zipcode")
    WebElement zipcodeTxt;

    @FindBy(id = "mobile_number")
    WebElement mobile_numberTxt;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/div[1]/form/button")
    WebElement createAccountBtn;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/h2")
    public WebElement newUserMessage;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/div[1]/h2/b")
    public WebElement accountInformationMessage;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/div/a")
    WebElement continueBtn;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
    public WebElement accountCreatedMessage;

    @FindBy(partialLinkText = "Logged in")
    public WebElement loggedLink;

    @FindBy(tagName = "li")
    List<WebElement> navBarList;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/h2/b")
    public WebElement deleteMessage;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/p")
    public WebElement emailExistMessage;

    public void userCanRegister(String name, String email) {
        scrollToElement(usernameTxt);
        usernameTxt.sendKeys(name);

        scrollToElement(emails.get(1));
        emails.get(1).sendKeys(email);

        scrollToElement(signupBtn);
        signupBtn.click();
    }

    public void enterAccountInformation(String password, int day, String month, String year,
                                         String firstName, String lastName, String company,
                                         String address1, String address2, String country,
                                         String state, String city, String zipcode,
                                         String mobileNumber) {

        scrollToElement(rediusBtn);
        rediusBtn.click();

        scrollToElement(passwordTxt);
        passwordTxt.sendKeys(password);

        scrollToElement(daysList);
        new Select(daysList).selectByIndex(day);

        scrollToElement(monthsList);
        new Select(monthsList).selectByValue(month);

        scrollToElement(yearsList);
        new Select(yearsList).selectByValue(year);

        scrollToElement(newsletterCheckBox);
        newsletterCheckBox.click();

        scrollToElement(optinCheckBox);
        optinCheckBox.click();

        scrollToElement(first_nameTxt);
        first_nameTxt.sendKeys(firstName);

        scrollToElement(last_nameTxt);
        last_nameTxt.sendKeys(lastName);

        scrollToElement(companyTxt);
        companyTxt.sendKeys(company);

        scrollToElement(address1Txt);
        address1Txt.sendKeys(address1);

        scrollToElement(address2Txt);
        address2Txt.sendKeys(address2);

        scrollToElement(countryList);
        new Select(countryList).selectByVisibleText(country);

        scrollToElement(stateTxt);
        stateTxt.sendKeys(state);

        scrollToElement(cityTxt);
        cityTxt.sendKeys(city);

        scrollToElement(zipcodeTxt);
        zipcodeTxt.sendKeys(zipcode);

        scrollToElement(mobile_numberTxt);
        mobile_numberTxt.sendKeys(mobileNumber);

        scrollToElement(createAccountBtn);
        createAccountBtn.click();
    }

    public void continueAccount() {
        scrollToElement(continueBtn);
        continueBtn.click();
    }

    public void deleteAcount() {
        scrollToElement(navBarList.get(4));
        navBarList.get(4).click();
    }


}
