package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;


public class AddSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[2]")
    public WebElement secondNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]")
    private WebElement firstConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]/div[1]/div[1]/li/div[1]/span/span[1]")
    public WebElement firstNotConnectedSensorType;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]/div[1]/div[1]/li/div[1]/span/span[2]")
    public WebElement firstNotConnectedSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]/div[1]/div[1]/li/div[1]/span/span[1]")
    public WebElement firstConnectedSensorType;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]/div[1]/div[1]/li/div[1]/span/span[2]")
    public WebElement firstConnectedSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement pointOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[1]")
    private WebElement firstPointOnHomePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]")
    private WebElement notConnectedSensorsList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]")
    private WebElement connectedSensorsList;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/button[2]")
    private WebElement okButtonOnDeletingSensorBox;

    public AddSensorOnHomePlanPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public String getElementBackgroundColor(WebElement element) {
        return element.getCssValue("background-color");
    }

    public void moveMouseToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void clickNotConnectedSensorOnList() {
        WebElement element = firstNotConnectedSensor.findElement(By.tagName("p"));
        scrollIntoView(firstNotConnectedSensor);
        clickElement(element);
    }

    public String isSensorNotClickable() {
        return getElementAttribute(firstConnectedSensor, "aria-disabled");
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public String getCursorType() {
        String cursor = homePlan.getCssValue("cursor");
        return cursor;
    }

    public void clickToAddPointOnHomePlan(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }

    public String getCompleteTransitionOfColor(WebElement element) {
        String fullString = element.getCssValue("background-color");
        int beginIndex = fullString.length() - 4;
        int endIndex = fullString.length() - 1;
        String partialString = fullString.substring(beginIndex, endIndex);
        return partialString;
    }

    public void waitForCompleteBackgroundColor(WebElement element) {
        String partialStringFromBackgroundColor = getCompleteTransitionOfColor(element);
        String expected = ", 1";
        waitForElementAttributeToChange(driver, partialStringFromBackgroundColor, expected);
    }

    public String getSensorType(WebElement element) {
        return element.findElement(By.tagName("p")).getText();
    }

    public String getSensorTypeBackgroundColor(WebElement element) {
        switch (getSensorType(element)) {
            case "Temperature":
            case "Temperatura":
                return "rgba(255, 230, 204, 1)";
            case "Window":
            case "Okno":
                return "rgba(238, 229, 255, 1)";
            case "Window blind":
            case "Rolety":
                return "rgba(247, 212, 228, 1)";
            case "RFID":
                return "rgba(255, 236, 235, 1)";
            case "Smoke sensor":
            case "Czujnik dymu":
                return "rgba(204, 204, 204, 1)";
            case "RGB light":
            case "Światło RGB":
                return "rgba(41, 158, 58, 1)";
            default:
                throw new IllegalStateException("Unexpected value: " + getSensorType(element));
        }
    }

    public void deleteFirstSensorFromHomePlan() {
        //scrollIntoView(firstConnectedSensor);
        clickElement(firstPointOnHomePlan);
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
}
