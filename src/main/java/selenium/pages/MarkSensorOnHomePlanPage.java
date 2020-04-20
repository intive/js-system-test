package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

public class MarkSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement sensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[last()]")
    public WebElement sensorOnConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[2]")
    public WebElement secondSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[2]")
    public WebElement secondSensorOnConnectedSensorsList;

    public MarkSensorOnHomePlanPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void clearHomePlan() {
        goTo("/api/v1/dashboard/delete");
    }

    public String getElementBackgroundColor(WebElement element) {
        return element.getCssValue("background-color");
    }

    public String getElementBorderColor(WebElement element) {
        return element.getCssValue("border-color");
    }

    public void clickFirstNotConnectedSensorOnList() {
        clickElement(firstNotConnectedSensor);
    }

    public void clickLastSensorOnHomePlan() {
        clickElement(sensorOnHomePlan);
    }

    public void clickOnHomePlan(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }

    public void resizeBrowser() {
        Dimension dimension = new Dimension(1200, 600);
        driver.manage().window().setSize(dimension);
    }

    public boolean isSensorInViewPort(WebElement element) {
        Dimension elementDimension = element.getSize();
        Point elementLocation = element.getLocation();
        Dimension browserDimension = driver.manage().window().getSize();
        int yBrowser = browserDimension.getHeight();
        int yElement = elementDimension.getHeight() + elementLocation.getY();
        return yElement <= yBrowser;
    }

    public void waitForScrollToComplete(WebElement element) {
        isElementDisplayed(driver, element);
    }

    /*public String getCompleteTransitionOfColor(WebElement element) {
        String fullString = element.getCssValue("background-color");
        int beginIndex = fullString.length() - 4;
        int endIndex = fullString.length() - 1;
        String partialString = fullString.substring(beginIndex, endIndex);
        return partialString;
    } */

    public void waitForCompleteBackgroundColor(WebElement element) {
        waitForElementAttributeToChange(driver, getElementBackgroundColor(element), getSensorTypeColor(element));
    }

    public String getSensorType(WebElement element) {
        return element.findElement(By.tagName("p")).getText();
    }

    public String getSensorTypeColor(WebElement element) {
        switch (getSensorType(element)) {
            case "Temperature":
            case "Temperatura":
                return "rgba(255, 153, 51, 1)";
            case "Window":
            case "Okno":
                return "rgba(136, 77, 255, 1)";
            case "Window blind":
            case "Rolety":
                return "rgba(224, 81, 148, 1)";
            case "RFID":
                return "rgba(255, 141, 133, 1)";
            case "Smoke sensor":
            case "Czujnik dymu":
                return "rgba(128, 128, 128, 1)";
            default:
                throw new IllegalStateException("Unexpected value: " + getSensorType(element));
        }
    }

}
