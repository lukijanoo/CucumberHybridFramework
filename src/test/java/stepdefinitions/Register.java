package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.CommonUtils;

import java.util.Map;

public class Register {
    WebDriver driver;
    private RegisterPage registerPage;
    private AccountSuccessPage accountSuccessPage;
    private CommonUtils commonUtils;

    @Given("User navigates to Register Account page")
    public void user_navigates_to_register_account_page() {
        driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        registerPage = homePage.selectRegisterOption();
    }

    @When("User enters the details into below fields")
    public void user_enters_the_details_into_below_fields(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        commonUtils = new CommonUtils();

        registerPage.enterFirstName(dataMap.get("firstName"));
        registerPage.enterLastName(dataMap.get("lastName"));
        registerPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
        registerPage.enterTelephoneNumber(dataMap.get("telephone"));
        registerPage.enterPassword(dataMap.get("password"));
        registerPage.enterConfirmPassword(dataMap.get("password"));
    }

    @When("User enters the details into below fields with duplicate email")
    public void user_enters_the_details_into_below_fields_with_duplicate_email(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        registerPage.enterFirstName(dataMap.get("firstName"));
        registerPage.enterLastName(dataMap.get("lastName"));
        registerPage.enterEmailAddress(dataMap.get("email"));
        registerPage.enterTelephoneNumber(dataMap.get("telephone"));
        registerPage.enterPassword(dataMap.get("password"));
        registerPage.enterConfirmPassword(dataMap.get("password"));

    }

    @And("User selects Privacy Policy")
    public void user_selects_privacy_policy() {
        registerPage.selectPrivacyPolicy();
    }

    @And("User clicks on Continue button")
    public void user_clicks_on_continue_button() {
        accountSuccessPage = registerPage.clickOnContinueButton();
    }

    @Then("User account should get created successfully")
    public void user_account_should_get_created_successfully() {
        Assertions.assertEquals("Your Account Has Been Created!", accountSuccessPage.getPageHeading());
    }

    @And("User selects Yes for Newsletter")
    public void userSelectsYesForNewsletter() {
        registerPage.selectYesNewsletterOption();
    }

    @Then("User should get a proper warning message about duplicate email")
    public void userShouldGetAProperWarningMessageAboutDuplicateEmail() {
        String expected_duplicateEmailWarningMessage = "Warning: E-Mail Address is already registered!";
        Assertions.assertTrue(registerPage.getWarningMessageText().contains(expected_duplicateEmailWarningMessage));
    }

    @Then("User should get proper warning messages for every mandatory field")
    public void userShouldGetProperWarningMessagesForEveryMandatoryField() {

        Assertions.assertTrue(registerPage.getWarningMessageText().contains("Warning: You must agree to the Privacy Policy!"));
        Assertions.assertEquals("First Name must be between 1 and 32 characters!", registerPage.getFirstNameWarning());
        Assertions.assertEquals("Last Name must be between 1 and 32 characters!", registerPage.getLastNameWarning());
        Assertions.assertEquals("E-Mail Address does not appear to be valid!", registerPage.getEmailWarning());
        Assertions.assertEquals("Telephone must be between 3 and 32 characters!", registerPage.getTelephoneWarning());
        Assertions.assertEquals("Password must be between 4 and 20 characters!", registerPage.getPasswordWarning());

    }

    @When("User doesn't enter any details into fields")
    public void userDoesnTEnterAnyDetailsIntoFields() {
        registerPage.enterFirstName("");
        registerPage.enterLastName("");
        registerPage.enterEmailAddress("");
        registerPage.enterTelephoneNumber("");
        registerPage.enterPassword("");
        registerPage.enterConfirmPassword("");
    }
}
