package onliner.framework;

import static onliner.framework.browser.Browser.driver;

import onliner.framework.baseElement.BaseElement;
import org.openqa.selenium.*;
import org.testng.Assert;

public abstract class BasePage {

  public BasePage() {}

  protected <T extends BaseElement> void checkPageElementIsDisplayed(T element) {
    Assert.assertTrue(element.checkIsDisplayedWithWait(), "Incorrect page is opened");
  }

  protected <T extends BaseElement> void checkPageTitle(String expectedTitle, T element) {
    Assert.assertTrue(element.getText().contains(expectedTitle), "Incorrect page title");
  }
}
