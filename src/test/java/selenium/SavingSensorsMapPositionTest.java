package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.SavingSensorsMapPositionPage;

import java.io.IOException;

public class SavingSensorsMapPositionTest extends TestBase {

    private SavingSensorsMapPositionPage savingSensorsMapPositionPage;

    @BeforeClass
    public void beforeClass() {
        savingSensorsMapPositionPage = new SavingSensorsMapPositionPage(driver);
    }

    @BeforeMethod
    public void beforeMethod() {
        savingSensorsMapPositionPage.removeAllSensorsFromMap();
    }

    @DataProvider(name = "idAndOffset")
    public Object[][] sensorIdAndOffsets() {
        return new Object[][]{
                {21, 0, 0},
                {51, 50, 50},
                {71, 100, 100},
                {81, -100, -100}};
    }

    @Test(dataProvider = "idAndOffset")
    public void savingSensorsMapPositionTest(int id, int xOffset, int yOffset) {
        savingSensorsMapPositionPage.goTo();

        savingSensorsMapPositionPage.setSensorId(id);

        savingSensorsMapPositionPage.addSensorOnMap(xOffset, yOffset);

        double[] clickedPositionOnTheMap = savingSensorsMapPositionPage.getClickedPositionOnTheMap(xOffset, yOffset);
        double[] sensorPositionOnTheMap = savingSensorsMapPositionPage.getSensorPositionOnTheMap();

        Assert.assertEquals(clickedPositionOnTheMap[0], sensorPositionOnTheMap[0], 1);
        Assert.assertEquals(clickedPositionOnTheMap[1], sensorPositionOnTheMap[1], 1);

        savingSensorsMapPositionPage.refreshPage();

        double[] sensorPositionOnTheMapAfterRefresh = savingSensorsMapPositionPage.getSensorPositionOnTheMap();
        Assert.assertEquals(sensorPositionOnTheMapAfterRefresh, sensorPositionOnTheMap);

        savingSensorsMapPositionPage.goToApi();
        Assert.assertEquals(savingSensorsMapPositionPage.getSensorPositionFromAPI(id), sensorPositionOnTheMap, 1);
    }

    @Test(dataProvider = "idAndOffset")
    public void addingSensorsWhenOfflineTest(int id, int xOffset, int yOffset) throws IOException {
        savingSensorsMapPositionPage.goTo();

        savingSensorsMapPositionPage.setSensorId(id);

        savingSensorsMapPositionPage.internetConnection(false);
        savingSensorsMapPositionPage.addSensorOnMap(xOffset, yOffset);
        savingSensorsMapPositionPage.internetConnection(true);

        String expectedSnackbarErrorMessage = "Czujnik " + id + " nie został dodany.";
        String actualSnackbarErrorMessage = savingSensorsMapPositionPage.getErrorSnackbarText();
        Assert.assertEquals(actualSnackbarErrorMessage, expectedSnackbarErrorMessage);

        Assert.assertNull(savingSensorsMapPositionPage.getSensorOnTheMap());
        Assert.assertFalse(savingSensorsMapPositionPage.hasSensorOnListXButton());

        savingSensorsMapPositionPage.goToApi();
        Assert.assertNull(savingSensorsMapPositionPage.getSensorPositionFromAPI(id));
    }
}