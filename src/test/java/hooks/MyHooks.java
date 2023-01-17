package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.util.Properties;

public class MyHooks {
    WebDriver driver;

    @Before
    public void setup() {
        //Calling values from config for initializing web driver
        Properties prop = new ConfigReader().initializeProperties();
        driver = DriverFactory.initializeBrowser(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scenario) {
        //Taking a screenshot when scenario fails
        String scenarioName = scenario.getName().replace(" ", "_");
        if (scenario.isFailed()) {
            byte[] srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(srcScreenshot, "image/png", scenarioName);
        }

        driver.quit();
    }
}
