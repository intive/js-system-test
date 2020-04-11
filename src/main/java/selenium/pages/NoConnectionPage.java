package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestCommons;


public class NoConnectionPage extends TestCommons {


    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div")
    public WebElement snackBar;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div/div[2]/button")
    public WebElement exitButton;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div")
    public WebElement snackBar2;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div/div[2]/button")
    public WebElement exitButton2;


    public NoConnectionPage(WebDriver driver) {
        super(driver);
    }

    public boolean snackBarExist(WebElement webElement, int time) {
        if (isElementDisplayed(driver, webElement, time)==true) {
            return true;
        }
        return false;
    }

    public boolean snackBarDoesntExist(WebElement webElement, int time) {
        if (isElementNotDisplayed(driver, webElement, time)==true) {
            return true;
        }
        return false;
    }

    @Override
    public void clickElement(WebElement element) {
        super.clickElement(element);
    }

    public void goTo() {
        goTo("/");
    }
}
