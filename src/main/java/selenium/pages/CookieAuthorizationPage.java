package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestCommons;

public class CookieAuthorizationPage extends TestCommons {

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(css = "#root > div > div:nth-child(3)")
    private WebElement snackbar;

    public CookieAuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String path) {
        super.goTo(path);
    }

    public String getBodyText() {
        return body.getText();
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void addNewCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    public boolean isSnackbarDisplayed() {
        try {
            new WebDriverWait(driver, 10).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(snackbar));
            return snackbar.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSnackbarHidden() {
        try {
            return new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#root > div > div:nth-child(3)")));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
