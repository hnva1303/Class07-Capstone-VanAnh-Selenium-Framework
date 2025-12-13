package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final Logger LOG = LogManager.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        LOG.info("waitForVisibilityOfElementLocated: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        LOG.info("waitForElementToBeClickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void sendKeys(By locator, String value) {
        LOG.info("sendKeys: " + locator + " with " + value);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        element.sendKeys(value);
    }

    public void click(By locator) {
        LOG.info("click: " + locator);
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    public String getText(By locator) {
        LOG.info("getText: " + locator);
        WebElement element = waitForVisibilityOfElementLocated(locator);
        return element.getText();
    }
}
