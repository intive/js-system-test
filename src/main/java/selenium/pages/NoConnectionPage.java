package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
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
    private WebElement sensor3;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[4]")
    private WebElement sensor4;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]/div[1]")
    private WebElement sensorAdded1;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]/div[2]")
    private WebElement sensorAdded2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[6]")
    private WebElement sensor6;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    private WebElement homePlan;

    List<WebElement> sensorList = new ArrayList<>();

    public NoConnectionPage(WebDriver driver) {
        super(driver);
    }

    public boolean snackBarExist(WebElement webElement, int time) {
        return isElementDisplayed(driver, webElement, time);
    }

    public boolean snackBarDoesntExist(WebElement webElement, int time) {
        return isElementNotDisplayed(driver, webElement, time );
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
        sensorList.add(sensor6);
        return sensorList;
    }

    public List<WebElement> getListOfSensorsCheck2() {
        sensorList.clear();
        sensorList.add(sensor1);
        sensorList.add(sensor4);
        sensorList.add(sensorAdded1);
        sensorList.add(sensorAdded2);
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

    public void isMapLoaded() { isElementDisplayed(driver, homePlan, 5); }

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
