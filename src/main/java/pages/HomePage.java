package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;
import utils.ElementUtils;

public class HomePage {

    WebDriver driver;
    private ElementUtils elementUtils;
    //To remove hardcoded locators, we're implementing POM instead. It's Page Factory
    //given by Selenium to support POM
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    @FindBy(linkText = "Login")
    private WebElement loginOption;
    @FindBy(linkText = "Register")
    private WebElement registerOption;
    @FindBy(name = "search")
    private WebElement searchBoxField;
    @FindBy(className = "input-group-btn")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }

    public void clickOnMyAccount() {
        elementUtils.clickOnElement(myAccountDropMenu, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
    }

    public LoginPage selectLoginOption() {
        elementUtils.clickOnElement(loginOption, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
        return new LoginPage(driver);
    }

    public RegisterPage selectRegisterOption() {
        elementUtils.clickOnElement(registerOption, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
        return new RegisterPage(driver);
    }

    public void enterProductIntoSearchBox(String productText) {
        elementUtils.typeTextIntoElement(searchBoxField, productText, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
    }

    public SearchResultsPage clickOnSearchButton() {
        elementUtils.clickOnElement(searchButton, CommonUtils.EXPLICIT_WAIT_BASIC_TIME);
        return new SearchResultsPage(driver);
    }
}
