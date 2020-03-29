package selenium.pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardPage extends TestCommons {

    @FindBy(tagName = "div")
    public List<WebElement> allDivElements;

    @FindBy(tagName = "h5")
    public WebElement appName;

    @FindBy(className = "MuiTab-wrapper")
    public List<WebElement> sectionName;

    @FindBy(tagName = "button")
    public WebElement notificationsButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[1]")
    public WebElement pointOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement lastPoint;

    @FindBy(css = "body > div:nth-child(3) > div.MuiPaper-root.jss245.MuiPaper-elevation3.MuiPaper-rounded")
    public WebElement gapNeededBox;

    @FindBy(className = "MuiCircularProgress-svg")
    public WebElement loader;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public int getNumberOfSections() {
        int findSize12 = 0;
        int findSize9 = 0;
        int findSize3 = 0;
        int count = 0;
        for (WebElement element : allDivElements) {
            String className = element.getAttribute("class");
            if (className.contains("MuiGrid-item")) {
                if (className.contains("xs-12")) {
                    findSize12 = 1;
                }
                if (className.contains("xs-9")) {
                    findSize9 = 1;
                }
                if (className.contains("xs-3")) {
                    findSize3 = 1;
                }
            }
            count = findSize12 + findSize9 + findSize3;
        }
        return count;
    }

    public List<String> getSectionNames() {
        return sectionName.stream().map(x -> x.getText().toLowerCase()).collect(Collectors.toList());
    }

    public String getApplicationName() {
        return getElementAttribute(appName, "innerHTML");
    }

    public String getNotificationsLabel() {
        return getElementAttribute(notificationsButton, "aria-label");
    }

    public void clickDashboard() {
        sectionName.get(0).click();
    }

    public void clickHvac() {
        sectionName.get(1).click();
    }

    public void clickAuthors() {
        sectionName.get(2).click();
    }

    public void clickApplicationName() {
        appName.click();
    }

    public void clickNotifications() {
        notificationsButton.click();
    }

    public String getCursorType() {
        String cursor = homePlan.getCssValue("cursor");
        return cursor;
    }

    public int getMapWidth() {
        return getElementWidth(homePlan);
    }

    public int getMapHeight() {
        return getElementHeight(homePlan);
    }

    public void clickToAddPoint(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }

    public void clickOffsetToGapNeededBox(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(gapNeededBox, xOffset, yOffset).click().perform();
    }

    public boolean checkIfGapNeededBoxIsPresent() {
        if (gapNeededBox.isDisplayed()) return true;
        else return false;
    }

    public String getPointUniqueInformation() {
        return getElementAttribute(lastPoint, "style");
    }

    public int getLastPointWidth() {
        return getElementWidth(lastPoint);
    }

    public int getLastPointHeight() {
        return getElementHeight(lastPoint);
    }

    public int getPointX() {
        return getElementLocation(pointOnHomePlan).getX();
    }

    public int getPointY() {
        return getElementLocation(pointOnHomePlan).getY();
    }

    public int getMapX() {
        return getElementLocation(homePlan).getX();
    }

    public int getMapY() {
        return getElementLocation(homePlan).getY();
    }

    public int getXOffsetPointToCenterOfMap() {
        int xOffset = getMapX() - getPointX();
        return xOffset;
    }

    public int getYOffsetPointToCenterOfMap() {
        int yOffset = getMapY() - getPointY();
        return yOffset;
    }

    public void resizeBrowser() {
        Dimension dimension = new Dimension(1000, 550);
        driver.manage().window().setSize(dimension);
    }

    public int correctOffsetHeight() {
        int OffsetHeight = (int) Math.round(0.04 * getMapHeight());
        return OffsetHeight;
    }

    public int correctOffsetWidth() {
        int OffsetWidth = (int) Math.round(0.04 * getMapWidth());
        return OffsetWidth;
    }

    public int incorrectOffsetHeight() {
        int OffsetHeight = (int) Math.round(0.03 * getMapHeight());
        return OffsetHeight;
    }

    public int incorrectOffsetWidth() {
        int OffsetWidth = (int) Math.round(0.03 * getMapWidth());
        return OffsetWidth;
    }

    public boolean isLoaderDisplayed() {
        return isElementDisplayed(driver, loader);
    }

}







