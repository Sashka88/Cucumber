package onliner.projectOnlinerCucumber.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import onliner.framework.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static onliner.framework.PropertyReader.getProperty;

@CucumberOptions(
        plugin = {"html:target/cucumber-report/", "json:target/cucumber.json"},
        features = "src/test/java/onliner/projectOnlinerCucumber/features",
        glue = "onliner.projectOnlinerCucumber.steps")
public class RunnerCucumber extends AbstractTestNGCucumberTests {

    protected static Browser browser;

    @BeforeMethod
    public void before() {
        browser = new Browser(getProperty("config", "browserName"));
        browser.maximizeWindow();
        browser.navigatePage(getProperty("config", "baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        browser.quitBrowser();
    }
}
