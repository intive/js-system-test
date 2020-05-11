package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.NoConnectionPage;
import java.io.IOException;
import java.util.List;

public class StateOfSensorsTest extends TestBase {
    private NoConnectionPage stateOfSensors;

    @BeforeClass
    public void startPage() {
        stateOfSensors = new NoConnectionPage(driver);
        stateOfSensors.resetSensorsOnMap();
        stateOfSensors.goTo();
        stateOfSensors.lastSensorIsDisplayed(stateOfSensors.lastSensor);
    }

    @Test (priority = 0)
    public void responseFailureDataUpdate() throws InterruptedException, IOException {

        stateOfSensors.clickElement(stateOfSensors.sensor1);
        stateOfSensors.clickToAddPoint(-50, -75);
        stateOfSensors.clickElement(stateOfSensors.sensor2);
        stateOfSensors.clickToAddPoint(150, 125);
        List<WebElement> sensors = stateOfSensors.getListOfSensors();
        List<WebElement> sensorsCheck2 = stateOfSensors.getListOfSensorsCheck2();
        List<String> sensorValuesFirstRequest = stateOfSensors.getListOfSensorValues(sensors);
        stateOfSensors.internetConnection(false);
        Thread.sleep(5000);
        List<String> sensorValuesSecondRequest = stateOfSensors.getListOfSensorValues(sensorsCheck2);
        Assert.assertEquals(sensorValuesFirstRequest, sensorValuesSecondRequest, "Wartości czujników powinny były pozostać niezmienione.");
    }

//              Ze względu na mockowe wartości, poniższy test obecnie nie ma możliwości przejść,
//              zostanie odkomentowany, gdy będą aktualizowane faktyczne wartości czujników.
//
//    @Test
//    public void correctResponseDataUpdate() throws InterruptedException {
//
//        List<WebElement> sensors = stateOfSensors.getListOfSensors();
//        List<String> sensorValuesFirstRequest = stateOfSensors.getListOfSensorValues(sensors);
//        Thread.sleep(5000);
//        List<String> sensorValuesSecondRequest = stateOfSensors.getListOfSensorValues(sensors);
//        Assert.assertNotEquals(sensorValuesFirstRequest, sensorValuesSecondRequest, "Wartości czujników powinny były się zmienić.");
//
//    }

    @Test (priority = 1)
    public void apiResponseFailureSnackbar() throws IOException {
        stateOfSensors.internetConnection(false);
        WebElement snackBarApi;
        WebElement theIncorrectSnackBar;
        WebElement theRightExitButton;
        try {
            Assert.assertEquals(stateOfSensors.snackBar.getText(), "Odświeżenie stanu czujników nie powiodło się.");
            snackBarApi = stateOfSensors.snackBar;
            theRightExitButton = stateOfSensors.exitButton;
            theIncorrectSnackBar = stateOfSensors.snackBar2;
        } catch (AssertionError e) {
            try {
                Assert.assertEquals(stateOfSensors.snackBar2.getText(), "Odświeżenie stanu czujników nie powiodło się.", "Brak snackbaru API lub snackbar API o nieprawidłowej nazwie.");
                snackBarApi = stateOfSensors.snackBar2;
                theRightExitButton = stateOfSensors.exitButton2;
                theIncorrectSnackBar = stateOfSensors.snackBar;
            } catch (AssertionError e2){
                throw e2;
            }
        }
        stateOfSensors.snackBarExist(theIncorrectSnackBar, 10);

        boolean snackBarExistenceConfirmation = stateOfSensors.snackBarExist(snackBarApi, 5);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Nie wykryto prawidłowego snackbaru API.");
        stateOfSensors.clickElement(theRightExitButton);
        snackBarExistenceConfirmation = stateOfSensors.snackBarDoesntExist(snackBarApi, 5);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar API nie zniknął.");
        snackBarExistenceConfirmation = stateOfSensors.snackBarExist(snackBarApi, 5);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Snackbar API nie pojawił się ponownie.");


        String snackApiColour = stateOfSensors.snackBarApiColour(snackBarApi);
        Assert.assertEquals(snackApiColour, "rgba(255, 160, 0, 1)", "Snackbar API ma nieprawidłowy kolor.");
        boolean[] snackBars = new boolean[2];
        boolean[] expectedSnackBars = {true,true};
        boolean[] expectedSnackBarsInOnlineMode = {false,false};
        snackBars[0] = stateOfSensors.snackBarExist(theIncorrectSnackBar,1);
        snackBars[1] = stateOfSensors.snackBarExist(snackBarApi,1);
        Assert.assertEquals(snackBars, expectedSnackBars, "Oba snackbary nie pojawiły się jednocześnie.");

        stateOfSensors.internetConnection(true);
        snackBars[0] = stateOfSensors.snackBarDoesntExist(snackBarApi,5);
        snackBars[1] = stateOfSensors.snackBarDoesntExist(theIncorrectSnackBar,5);
        Assert.assertEquals(snackBars, expectedSnackBarsInOnlineMode, "Snackbary nie zniknęły.");
        stateOfSensors.resetSensorsOnMap();
    }
}
