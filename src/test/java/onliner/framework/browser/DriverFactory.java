package onliner.framework.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        switch (System.getProperty("browser")) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-popup-blocking");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("safebrowsing-disable-extension-blacklist");
                driver = new ChromeDriver(options);
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Incorrect Browser Name");
        }
        return driver;
    }
}