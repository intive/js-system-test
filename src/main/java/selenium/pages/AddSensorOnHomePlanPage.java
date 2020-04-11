package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;


public class AddSensorOnHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

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

}
