package selenium.base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class TestCommons {

    protected WebDriver driver;
    private final String url = "https://patronage20-js-master.herokuapp.com";

    public TestCommons(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void goTo(String path) {
        driver.get(url + path);
    }

    public void refreshPage() {
        driver.get(driver.getCurrentUrl());
    }

    protected void addNewCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    protected void sendKeysToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected String getElementAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    protected Boolean isImageValid(WebElement imageElement) {
        String Source = imageElement.getAttribute("src");

        try {
            BufferedImage img = ImageIO.read(new URL(Source));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    protected int getElementWidth(WebElement element) {
        double sizeDouble = element.getSize().getWidth();
        int sizeInt = (int) Math.ceil(sizeDouble);
        return sizeInt;
    }

    protected int getElementHeight(WebElement element) {
        double sizeDouble = element.getSize().getHeight();
        int sizeInt = (int) Math.ceil(sizeDouble);
        return sizeInt;
    }

    protected Point getElementLocation(WebElement element) {
        return element.getLocation();
    }

    protected static boolean isElementDisplayed(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    protected static void waitForElementAttributeToChange(WebDriver driver, String text, String expected) {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    text.equals(expected);
                    return true;
                } catch
                (org.openqa.selenium.TimeoutException e) {
                    return false;
                }
            }
        });
    }

    protected static void waitUntilVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete", element);
                } catch
                (org.openqa.selenium.TimeoutException e) {
                    return false;
                }
            }
        });
    }

    protected static boolean isElementNotDisplayed(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOf(element));
            return !(element.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.StaleElementReferenceException
                | org.openqa.selenium.TimeoutException e) {
            return true;
        }
    }
}

