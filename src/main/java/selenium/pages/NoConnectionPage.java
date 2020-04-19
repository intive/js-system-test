package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestCommons;

import java.util.ArrayList;
import java.util.List;


public class NoConnectionPage extends TestCommons {


    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div")
    public WebElement snackBar;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div/div[2]/button")
    public WebElement exitButton;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div")
    public WebElement snackBar2;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div/div[2]/button")
    public WebElement exitButton2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[5]")
    public WebElement lastSensor;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement sensor1;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[2]")
    public WebElement sensor2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[3]")
    public WebElement sensor3;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]/div[1]")
    public WebElement sensor4;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]/div[2]")
    public WebElement sensor5;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;

    List<WebElement> sensorList = new ArrayList<>();

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

    public List<WebElement> getListOfSensors() {
        sensorList.add(sensor1);
        sensorList.add(sensor2);
        sensorList.add(sensor3);
        sensorList.add(sensor4);
        sensorList.add(sensor5);
        return sensorList;
    }

    public String getSensorValue(WebElement element) {
        return element.findElement(By.className("MuiListItemSecondaryAction-root")).getText();
    }

    public List<String> getListOfSensorValues(List <WebElement> listOfSensors) {
        List<String> sensorValues = new ArrayList<>();
        for (WebElement sensor : listOfSensors) {
            sensorValues.add(getSensorValue(sensor));
        }
        return sensorValues;
    }

    public void lastSensorIsDisplayed(WebElement lastSensor)
    {
    isElementDisplayed(driver, lastSensor, 5);
    }

    public String snackBarApiColour(WebElement snackBar) {
        return snackBar.getCssValue("background-color");
    }

    public void resetSensorsOnMap (){
        goTo("/api/v1/dashboard/delete");
    }

    public void clickToAddPoint(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }
}
