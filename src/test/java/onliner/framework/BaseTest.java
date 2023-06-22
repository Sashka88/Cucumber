package onliner.framework;

import static onliner.framework.PropertyReader.getProperty;

import onliner.framework.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  public static Browser browser;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    browser = new Browser(getProperty("config", "browserName"));
    browser.maximizeWindow();
    browser.navigatePage(getProperty("config", "baseUrl"));
  }

  @AfterMethod(alwaysRun = true)
  public void teardown() {
    browser.quitBrowser();
  }
}