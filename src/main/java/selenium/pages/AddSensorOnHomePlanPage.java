package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;


public class AddSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[2]")
    public WebElement secondNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]")
    public WebElement connectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]/div[1]/span/span[1]")
    public WebElement firstNotConnectedSensorType;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]/div[1]/span/span[2]")
    public WebElement firstNotConnectedSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div/div[1]/span/span[1]")
    public WebElement connectedSensorType;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div/div[1]/span/span[2]")
    public WebElement connectedSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[last()]")
    public WebElement pointOnHomePlan;

    public AddSensorOnHomePlanPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void clearHomePlan() {
        goTo("/api/v1/dashboard/delete");
    }

    public String getColorOfSensorLeftBorder(WebElement element) {
        return element.getCssValue("border-color");
    }

    public String getElementBackgroundColor(WebElement element) {
        return element.getCssValue("background-color");
    }

    public void moveMouseToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void clickNotConnectedSensorOnList() {
        clickElement(firstNotConnectedSensor);
    }

    public String isSensorNotClickable() {
        return getElementAttribute(connectedSensor, "aria-disabled");
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

    public String getSensorColorWithTransition(String borderColor, String transition, int index) {
        String borderColorBegin = borderColor.substring(0, index);
        String borderColorEnd = borderColor.substring(index);
        return borderColorBegin + transition + borderColorEnd;
    }

    public String getUniversalSensorTypeColor(WebElement element) {
        String sensorBorderColor = getColorOfSensorLeftBorder(element);
        String sensorColorTransitionFirstStep = getSensorColorWithTransition(sensorBorderColor, ", 1", sensorBorderColor.length() - 1);
        String sensorColorTransitionFinalStep = getSensorColorWithTransition(sensorColorTransitionFirstStep, "a", 3);
        return sensorColorTransitionFinalStep;
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
                return "rgba(255, 236, 235)";
            case "Smoke sensor":
            case "Czujnik dymu":
                return "rgba(204, 204, 204)";
            default:
                throw new IllegalStateException("Unexpected value: " + getSensorType(element));
        }
    }

}
