package onliner.framework.browser;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static onliner.framework.browser.DriverFactory.getWebDriver;

public class Browser {
  public static WebDriver driver;
  public static SoftAssert softAssert;

  public Browser(String driverName){
    Browser.driver = getWebDriver(driverName);
    softAssert = new SoftAssert();
  }

  public void maximizeWindow() {
    driver.manage().window().maximize();
  }

  public void navigatePage(String url) {
    driver.get(url);
  }

  public void quitBrowser() {
    driver.quit();
  }
}