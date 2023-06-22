package onliner.framework.baseElement;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static onliner.framework.browser.Browser.driver;
import static onliner.framework.PropertyReader.getProperty;

public abstract class BaseElement {
    protected By locator;
    protected WebElement element;
    public JavascriptExecutor js;
    protected static long WAIT_TIMEOUT_SECONDS = Long.parseLong(getProperty("config", "waitingDuration"));
    protected List<WebElement> webElementsList = new ArrayList<>();

    public BaseElement(final By locName) {
        locator = locName;
    }

    public static void waitUntilIsDisplayed(WebElement button) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(condition -> button.isDisplayed());
    }

    public static void waitUntilIsInvisibilityOfElement(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    public static void waitUntilVisibilityOfAllElementsLocated(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
    }

    private void waitUntilPresenceOfElement(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    private void waitUntilElementToBeClickable(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(xpath));
    }

    private void waitUntilElementToBeVisible(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    private WebElement getPresentElement() {
        waitUntilPresenceOfElement(locator);
        return element = findElement(locator);
    }

    private WebElement getClickableElement() {
        getPresentElement();
        waitUntilElementToBeClickable(locator);
        return element;
    }

    public void click() {
        getClickableElement().click();
    }

    public void jsClick() {
        getPresentElement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void sendKeys(String keys) {
        checkIsDisplayedWithWait();
        element.sendKeys(keys);
    }

    private WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void waitUntilPresentAll() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void getPresentElements() {
        waitUntilPresentAll();
        webElementsList = findElements(locator);
    }

    public Boolean checkIsDisplayedWithWait() {
        getPresentElement();
        waitUntilElementToBeVisible(locator);
        return element.isDisplayed();
    }

    protected abstract List<? extends BaseElement> getElementsList();

    public String getText() {
        return getPresentElement().getText();
    }
}
