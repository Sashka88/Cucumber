package onliner.framework;

import static onliner.framework.browser.Browser.driver;

import org.openqa.selenium.*;
import org.testng.Assert;

public abstract class BasePage {

  public BasePage(String expectedTitle, By xpathTitle) {
    Assert.assertTrue(driver.findElement(xpathTitle).getText().contains(expectedTitle),
        "Incorrect page is opened, expected page is - " + expectedTitle);
  }

  public BasePage(By xpathElement) {
    Assert.assertTrue(driver.findElement(xpathElement).isDisplayed(), "Incorrect page is opened");
  }
}