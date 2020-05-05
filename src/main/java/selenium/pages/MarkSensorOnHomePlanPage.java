package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;

public class MarkSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]")
    public WebElement firstConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    private WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[1]")
    public WebElement firstSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement lastSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[last()]")
    public WebElement lastSensorOnConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[2]")
    public WebElement secondSensorOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[2]")
    public WebElement secondSensorOnConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]")
    private WebElement connectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]")
    public WebElement notConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div")
    private WebElement homePlanByDiv;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/button[2]")
    private WebElement okButtonOnDeletingSensorBox;

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

    public void clickNotConnectedSensorOnList(WebElement element) {
        WebElement pointToClick = element.findElement(By.tagName("p"));
        scrollIntoView(element);
        clickElement(pointToClick);
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
            case "Light":
            case "Światło":
                return "rgba(194, 239, 201,";
            default:
                throw new IllegalStateException("Unexpected value: " + getSensorType(element));
        }
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
        clickNotConnectedSensorOnList(firstNotConnectedSensor);
        clickOnHomePlan(x, y);
        clickOnCoordinates(60, 180); // In case Gap Needed box is present, it will close the box
    }

    public void clickSensorOnHomePlan(WebElement element) {
        clickElement(element);
    }

    public void deleteFirstSensorFromHomePlan() {
        scrollIntoView(firstConnectedSensor);
        clickElement(firstSensorOnHomePlan);
        clickElement(firstConnectedSensor.findElement(By.tagName("button")));
        clickElement(okButtonOnDeletingSensorBox);
    }

    public void deleteSensorsWhenRequired() {
        while (connectedSensorsOnList().size() != 0) {
            String firstConnectedSensorId = getSensorId(firstConnectedSensor);
            deleteFirstSensorFromHomePlan();
            waitForDeletedSensor(firstConnectedSensorId);
        }

    }

    public List<WebElement> connectedSensorsOnList() {
        List<WebElement> allConnectedSensorsOnList = connectedSensorsList.findElements(By.tagName("div"));
        return allConnectedSensorsOnList;
    }

    public void waitForDeletedSensor(String idText) {
        isElementDisplayed(driver, notConnectedSensorById(idText));
    }

    public WebElement notConnectedSensorById(String idText) {
        return notConnectedSensorsList.findElement(By.id(idText));
    }

    public String getSensorId(WebElement element) {
        return getElementAttribute(element.findElement(By.tagName("li")), "id");
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public String elevationValueOfSensorOnHomePlan(WebElement element) {
        return element.getCssValue("box-shadow");
    }
}
