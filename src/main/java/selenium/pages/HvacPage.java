package selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestCommons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HvacPage extends TestCommons {

    @FindBy(id = "mui-component-select-id")
    private WebElement hvacRoomIdInput;

    @FindBy(xpath = "//*[@id=\"menu-id\"]/div[3]/ul/li[1]")
    private WebElement hvacRoom1;

    @FindBy(xpath = "//*[@id=\"menu-id\"]/div[3]/ul/li[2]")
    private WebElement hvacRoom2;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[1]/div/div/div/div/div[1]/div[2]/div/input")
    private WebElement hvacNameInput;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[1]/div/div/div/div/div[2]/div/button[2]")
    private WebElement nextButtonStep1;

    @FindBy(id = "mui-component-select-temperatureSensorId")
    private WebElement temperatureSensorIdInput;

    @FindBy(xpath = "//*[@id=\"menu-temperatureSensorId\"]/div[3]/ul/li[1]")
    private WebElement temperatureSensor61;

    @FindBy(xpath = "//*[@id=\"menu-temperatureSensorId\"]/div[3]/ul/li[2]")
    private WebElement temperatureSensor62;

    @FindBy(id = "windowSensorIds")
    private WebElement windowSensorIdInput;

    @FindBy(xpath = "//*[@id=\"menu-windowSensorIds\"]/div[3]/ul/li[1]")
    private WebElement windowSensor81;

    @FindBy(xpath = "//*[@id=\"menu-windowSensorIds\"]/div[3]/ul/li[2]")
    private WebElement windowSensor82;

    @FindBy(xpath = "//*[@id=\"menu-windowSensorIds\"]/div[3]/ul/li[3]")
    private WebElement windowSensor83;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[3]/div/div/div/div/div[2]/div/button[2]")
    private WebElement nextButtonStep2;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[5]/div/div/div/div/div[1]/div[1]/span/span[7]")
    public WebElement heatingTemperature;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[5]/div/div/div/div/div[1]/div[2]/span/span[7]")
    public WebElement coolingTemperature;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[5]/div/div/div/div/div[1]/div[3]/span/span[7]")
    public WebElement hysteresis;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[5]/div/div/div/div/div[2]/div/button[3]")
    private WebElement finishButton;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div[2]/button[3]")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[1]/div[1]/div/div[1]/p")
    public WebElement rule1Name;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/div/div[1]/p")
    public WebElement rule2Name;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[1]/div[2]")
    private WebElement rule1ExpandButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[1]/div[2]")
    private WebElement rule2ExpandButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/p[2]")
    public WebElement rule1HeatingTemperature;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[2]/div[1]/p[2]")
    public WebElement rule1CoolingTemperature;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[3]/div[1]/p[2]")
    public WebElement rule1Hysteresis;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[4]/div[2]/p")
    public WebElement rule1TemperatureSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[5]/div[2]/p")
    private WebElement rule1WindowSensorId1;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[6]/div[2]/p")
    private WebElement rule1WindowSensorId2;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[7]/div[2]/p")
    private WebElement rule1WindowSensorId3;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div[1]/p[2]")
    public WebElement rule2HeatingTemperature;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[1]/p[2]")
    public WebElement rule2CoolingTemperature;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[3]/div[1]/p[2]")
    public WebElement rule2Hysteresis;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[4]/div[2]/p")
    public WebElement rule2TemperatureSensorId;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[5]/div[2]/p")
    private WebElement rule2WindowSensorId1;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[6]/div[2]/p")
    private WebElement rule2WindowSensorId2;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[7]/div[2]/p")
    private WebElement rule2WindowSensorId3;

    @FindBy(id = "client-snackbar")
    private WebElement incompleteFormSnackbar;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/div[2]")
    private WebElement notSavedRuleSnackbar;

    @FindBy(xpath = "//*[@id=\"hvac-form\"]/div/div[5]/div/div/div/div/div[1]/div[2]/div/div[2]")
    public WebElement minimumCoolingTemperature;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/header/div/div[2]/div[1]/div")
    private WebElement languageSelection;

    @FindBy(xpath = "//*[@id=\"menu-\"]/div[3]/ul/li[1]")
    private WebElement englishLanguage;

    public HvacPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/hvac");
    }

    public void step1ForRoom1WithName() {
        isElementDisplayed(driver, hvacRoomIdInput, 10);
        clickElement(hvacRoomIdInput);
        clickElement(hvacRoom1);
        sendKeysToElement(hvacNameInput, "QA Test 1");
        clickElement(nextButtonStep1);
    }

    public void step1ForRoom1WithoutName() {
        isElementDisplayed(driver, hvacRoomIdInput, 10);
        clickElement(hvacRoomIdInput);
        clickElement(hvacRoom1);
        clickElement(nextButtonStep1);
    }

    public void step1ForRoom2WithName() {
        isElementDisplayed(driver, hvacRoomIdInput, 10);
        clickElement(hvacRoomIdInput);
        clickElement(hvacRoom2);
        sendKeysToElement(hvacNameInput, "QA Test 2");
        clickElement(nextButtonStep1);
    }

    public void step1ForRoom2WithoutName() {
        isElementDisplayed(driver, hvacRoomIdInput, 10);
        clickElement(hvacRoomIdInput);
        clickElement(hvacRoom2);
        clickElement(nextButtonStep1);
    }

    public void step2TemperatureSensor61() {
        isElementDisplayed(driver, nextButtonStep2);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", temperatureSensorIdInput);
        clickElement(temperatureSensorIdInput);
        clickElement(temperatureSensor61);
    }

    public void step2TemperatureSensor62() {
        isElementDisplayed(driver, nextButtonStep2);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", temperatureSensorIdInput);
        clickElement(temperatureSensorIdInput);
        clickElement(temperatureSensor62);
    }

    public void clickOutsideWindowSensorsBox(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveByOffset(xOffset, yOffset).click().build().perform();
    }

    public void step2SelectAllWindowSensors() {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", windowSensorIdInput);
        clickElement(windowSensorIdInput);
        if (!isAttributePresent(windowSensor81, "aria-selected")) {
            clickElement(windowSensor81);
        }
        if (!isAttributePresent(windowSensor82, "aria-selected")) {
            clickElement(windowSensor82);
        }
        if (!isAttributePresent(windowSensor83, "aria-selected")) {
            clickElement(windowSensor83);
        }
        clickOutsideWindowSensorsBox(50, 100);
        clickElement(nextButtonStep2);
    }

    public void step2DeselectAllWindowSensors() {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", windowSensorIdInput);
        clickElement(windowSensorIdInput);
        if (isAttributePresent(windowSensor81, "aria-selected")) {
            clickElement(windowSensor81);
        }
        if (isAttributePresent(windowSensor82, "aria-selected")) {
            clickElement(windowSensor82);
        }
        if (isAttributePresent(windowSensor83, "aria-selected")) {
            clickElement(windowSensor83);
        }
        clickOutsideWindowSensorsBox(50, 100);
        clickElement(nextButtonStep2);
    }

    public void moveTemperatureSlider(WebElement element, int xOffset) {
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(element, xOffset, 0).perform();
    }

    public String getTemperatureFromSlider(WebElement element) {
        return getElementAttribute(element, "aria-valuenow");
    }

    public void step3Temperatures() {
        isElementDisplayed(driver, finishButton);
        moveTemperatureSlider(heatingTemperature, 40);
        moveTemperatureSlider(coolingTemperature, 40);
        moveTemperatureSlider(hysteresis, 120);
    }

    public void finishHvacRule() {
        clickElement(finishButton);
        isElementDisplayed(driver, addButton, 10);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", addButton);
    }

    public void showRule1Details() {
        clickElement(rule1ExpandButton);
        while (!isElementDisplayed(driver, rule1HeatingTemperature, 1)) {
            clickElement(rule1ExpandButton);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(rule1Name, "innerHTML", "QA Test 1"));
    }

    public void showRule1DetailsForRequiredInputs() {
        clickElement(rule1ExpandButton);
        while (!isElementDisplayed(driver, rule1HeatingTemperature, 1)) {
            clickElement(rule1ExpandButton);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(rule1Name, "innerHTML", "HVAC Rule 1"));
    }

    public void showRule2Details() {
        clickElement(rule2ExpandButton);
        while (!isElementDisplayed(driver, rule2HeatingTemperature, 1)) {
            clickElement(rule2ExpandButton);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(rule2Name, "innerHTML", "QA Test 2"));
    }

    public void showRule2DetailsForRequiredInputs() {
        clickElement(rule2ExpandButton);
        while (!isElementDisplayed(driver, rule2HeatingTemperature, 1)) {
            clickElement(rule2ExpandButton);
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(rule2Name, "innerHTML", "HVAC Rule 2"));
    }

    public boolean findWindowIdRule1(String windowId) {
        List<WebElement> allWindowsId = new ArrayList<>();
        allWindowsId.add(rule1WindowSensorId1);
        allWindowsId.add(rule1WindowSensorId2);
        allWindowsId.add(rule1WindowSensorId3);
        boolean isIdPresent = false;
        for (WebElement id : allWindowsId) {
            if (id.getText().contains(windowId))
                isIdPresent = true;
        }
        return isIdPresent;
    }

    public boolean findWindowIdRule2(String windowId) {
        List<WebElement> allWindowsId = new ArrayList<>();
        allWindowsId.add(rule2WindowSensorId1);
        allWindowsId.add(rule2WindowSensorId2);
        allWindowsId.add(rule2WindowSensorId3);
        boolean isIdPresent = false;
        for (WebElement id : allWindowsId) {
            if (id.getText().contains(windowId))
                isIdPresent = true;
        }
        return isIdPresent;
    }

    public void ruleWithNoData() {
        clickElement(nextButtonStep1);
        isElementDisplayed(driver, nextButtonStep2, 10);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", nextButtonStep2);
        isElementDisplayed(driver, finishButton, 10);
        ex.executeScript("arguments[0].click()", finishButton);
        isElementDisplayed(driver, addButton, 10);
        ex.executeScript("arguments[0].click()", addButton);
    }

    public boolean isIncompleteFormSnackbarDisplayed() {
        return isElementDisplayed(driver, incompleteFormSnackbar, 10);
    }

    public boolean isNotSavedRuleSnackbarDisplayed() {
        return isElementDisplayed(driver, notSavedRuleSnackbar, 10);
    }

    public void ruleWithNoInternetConnection() throws IOException {
        step1ForRoom1WithoutName();
        isElementDisplayed(driver, nextButtonStep2, 10);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", nextButtonStep2);
        isElementDisplayed(driver, finishButton, 10);
        ex.executeScript("arguments[0].click()", finishButton);
        isElementDisplayed(driver, addButton, 10);
        internetConnection(false);
        ex.executeScript("arguments[0].click()", addButton);
    }

    public void setOnline() throws IOException {
        isNotSavedRuleSnackbarDisplayed();
        internetConnection(true);
    }

    public void ruleWithTooLongName() {
        clickElement(hvacRoomIdInput);
        clickElement(hvacRoom1);
        sendKeysToElement(hvacNameInput, "Typing long name more than 30. signs");
        clickElement(nextButtonStep1);
        isElementDisplayed(driver, nextButtonStep2, 10);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", nextButtonStep2);
        isElementDisplayed(driver, finishButton, 10);
        ex.executeScript("arguments[0].click()", finishButton);
        isElementDisplayed(driver, addButton, 10);
        ex.executeScript("arguments[0].click()", addButton);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(rule1Name, "innerHTML", "Typing long name more than 30."));
    }

    public void setMinimumCoolingTemperature() {
        step1ForRoom1WithoutName();
        isElementDisplayed(driver, nextButtonStep2, 10);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", nextButtonStep2);
        step3Temperatures();
    }

    public boolean areWindowSensorsNotDisplayedInRule1() {
        return isElementDisplayed(driver, rule1WindowSensorId1, 2);
    }

    public boolean areWindowSensorsNotDisplayedInRule2() {
        return isElementDisplayed(driver, rule2WindowSensorId1, 2);
    }

    public void changeToEnglishLanguage() {
        clickElement(languageSelection);
        clickElement(englishLanguage);
    }

}
