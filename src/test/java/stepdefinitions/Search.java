package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.SearchResultsPage;

public class Search {
    WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @When("User opens the Application")
    public void user_opens_the_application() {
        driver = DriverFactory.getDriver();
    }

    @When("User enters valid product {string} into Search box field")
    public void userEntersValidProductIntoSearchBoxField(String validProductText) {
        homePage = new HomePage(driver);
        homePage.enterProductIntoSearchBox(validProductText);
    }

    @And("User clicks on Search button")
    public void userClicksOnSearchButton() {
        searchResultsPage = homePage.clickOnSearchButton();
    }

    @Then("User should get valid product displayed in search results")
    public void userShouldGetValidProductDisplayedInSearchResults() {
        Assertions.assertTrue(searchResultsPage.displayStatusOfValidProduct());
    }

    @When("User enters invalid product {string} into Search box field")
    public void userEntersInvalidProductIntoSearchBoxField(String invalidProductText) {
        homePage = new HomePage(driver);
        homePage.enterProductIntoSearchBox(invalidProductText);
    }

    @Then("User should get a message about no product matching")
    public void userShouldGetAMessageAboutNoProductMatching() {
        Assertions.assertEquals("There is no product that matches the search criteria.", searchResultsPage.getMessageText());
    }

    @When("User doesn't enter any product name into Search box field")
    public void userDoesnTEnterAnyProductNameIntoSearchBoxField() {
        homePage = new HomePage(driver);
    }
}
