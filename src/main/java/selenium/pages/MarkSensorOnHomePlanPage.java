package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/li[2]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[1]")
    public WebElement firstSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement lastSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/li[last()]")
    public WebElement lastSensorOnConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[2]")
    public WebElement secondSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/li[3]")
    public WebElement secondSensorOnConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]")
    public WebElement connectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div")
    public WebElement homePlanByDiv;

    public MarkSensorOnHomePlanPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
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
        clickElement(lastSensorOnHomePlan);
    }

    public void clickOnHomePlan(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlanByDiv, xOffset, yOffset).click().perform();
    }

    public void resizeBrowser() {
        Dimension browserDimension = driver.manage().window().getSize();
        int xBrowser = browserDimension.getWidth();
        int yBrowser = (int) ((browserDimension.getHeight()) * 0.7);
        Dimension dimension = new Dimension(xBrowser, yBrowser);
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

    public void waitForScrollToComplete(WebElement element) throws InterruptedException {

        long endWaitTime = System.currentTimeMillis() + 5 * 1000;
        boolean isConditionMet = false;
        while (System.currentTimeMillis() < endWaitTime && !isConditionMet) {
            isConditionMet = isSensorInViewPort(element);
            if (isConditionMet) {
                break;
            } else {
                Thread.sleep(10);
            }
        }
    }

    public void waitUntilHomePlanIsLoaded() {
        waitUntilVisible(driver, homePlan);
    }

    public String getSensorType(WebElement element) {
        return element.findElement(By.tagName("p")).getText();
    }

    public String getSensorTypeBackgroundColor(WebElement element) {
        switch (getSensorType(element)) {
            case "Temperature":
            case "Temperatura":
                return "rgba(255, 230, 204,";
            case "Window":
            case "Okno":
                return "rgba(238, 229, 255,";
            case "Window blind":
            case "Rolety":
                return "rgba(247, 212, 228,";
            case "RFID":
                return "rgba(255, 236, 235,";
            case "Smoke sensor":
            case "Czujnik dymu":
                return "rgba(204, 204, 204,";
            default:
                throw new IllegalStateException("Unexpected value: " + getSensorType(element));
        }
    }

    public Map<String, String> sensorBackgroundAndBorderColorMap() {
        Map<String, String> backgroundAndBorderColor = new HashMap<>();
        backgroundAndBorderColor.put("rgba(255, 153, 51, 1)", "rgb(153, 77, 0)");
        backgroundAndBorderColor.put("rgba(136, 77, 255, 1)", "rgb(59, 0, 179)");
        backgroundAndBorderColor.put("rgba(224, 82, 148, 1)", "rgb(130, 23, 73)");
        backgroundAndBorderColor.put("rgba(255, 141, 133, 1)", "rgb(235, 16, 0)");
        backgroundAndBorderColor.put("rgba(128, 128, 128, 1)", "rgb(51, 51, 51)");
        return backgroundAndBorderColor;
    }

    public List<WebElement> pointsOnHomePlan() {
        List<WebElement> allPointsOnHomePlan = homePlanByDiv.findElements(By.tagName("div"));
        return allPointsOnHomePlan;
    }

    public void clickOnCoordinates(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveByOffset(xOffset, yOffset).click().perform();
    }

    public void addPointsOnHomePlan(int x, int y) {
        if (pointsOnHomePlan().size() < 5) {
            clickFirstNotConnectedSensorOnList();
            clickOnHomePlan(x, y);
            clickOnCoordinates(60, 180); // In case Gap Needed box is present, it will close the box
        }
    }

    public void addPointsOnHomePlanWhenRequired() {
        addPointsOnHomePlan(0, 0);
        addPointsOnHomePlan(0, 20);
        addPointsOnHomePlan(0, 40);
        addPointsOnHomePlan(0, 80);
        addPointsOnHomePlan(0, 100);
        addPointsOnHomePlan(20, 0);
        addPointsOnHomePlan(40, 0);
        addPointsOnHomePlan(80, 0);
        addPointsOnHomePlan(100, 0);
        addPointsOnHomePlan(20, 20);
        addPointsOnHomePlan(40, 40);
        addPointsOnHomePlan(80, 80);
        addPointsOnHomePlan(100, 100);
    }

    public void clickSensorOnHomePlan(WebElement element) {
        clickElement(element);
    }

    public List<WebElement> sensorsOnList() {
        List<WebElement> allSensorsOnHomePlan = connectedSensorsList.findElements(By.tagName("li"));
        allSensorsOnHomePlan.remove(0);
        return allSensorsOnHomePlan;
    }

}
