package onliner.framework.baseElement;

import static onliner.framework.services.PropertyReader.getProperty;
import static onliner.framework.browser.Browser.driver;
import static onliner.framework.browser.Browser.executor;

import java.util.ArrayList;
import java.util.List;
import onliner.framework.JavaScript;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseElement {
    protected static long WAIT_TIMEOUT_SECONDS =
            Long.parseLong(getProperty("config", "waitingDuration"));
    public By locator;
    protected WebElement element;
    protected List<WebElement> webElementsList = new ArrayList<>();

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public static void waitUntilElementToBeInvisible(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    public static void waitUntilVisibilityOfAllElementsLocated(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
    }

    protected void waitUntilPresenceOfElement(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    protected void waitUntilElementToBeClickable(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(xpath));
    }

    protected void waitUntilElementToBeVisible(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    protected WebElement getPresentElement() {
        waitUntilPresenceOfElement(locator);
        return element = findElement(locator);
    }

    protected WebElement getVisibleElement() {
        getPresentElement();
        waitUntilElementToBeVisible(locator);
        return element;
    }

    protected WebElement getClickableElement() {
        getPresentElement();
        waitUntilElementToBeClickable(locator);
        return element;
    }

    public void click() {
        getClickableElement().click();
    }

    public void jsClick() {
        getPresentElement();
        executor.executeScript(JavaScript.CLICK_ON_ELEMENT.getScript(), element);
    }

    private WebElement findElement(By xpath) {
        return driver.findElement(xpath);
    }

    private List<WebElement> findElements(By xpath) {
        return driver.findElements(xpath);
    }

    public void hoverElementAndClick() {
        new Actions(driver).moveToElement(getVisibleElement()).click().perform();
    }

    public Boolean checkIsDisplayedWithoutWait() {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Boolean checkIsDisplayedWithWait() {
        getPresentElement();
        waitUntilElementToBeVisible(locator);
        return element.isDisplayed();
    }

    protected abstract List<? extends BaseElement> getElementsList();

    public String getXpathForElements(WebElement element) {
        return (String) executor.executeScript(JavaScript.GET_ELEMENT_XPATH.getScript(), element);
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

    public String getText() {
        return getPresentElement().getText();
    }

    public String getXpathAsString() {
        return String.valueOf(locator).substring(String.valueOf(locator).indexOf("/"));
    }

    public void sendKeys(String keys) {
        getVisibleElement();
        element.sendKeys(keys);
    }
}