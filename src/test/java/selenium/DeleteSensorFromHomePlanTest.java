package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DeleteSensorFromHomePlanPage;

import java.io.IOException;

public class DeleteSensorFromHomePlanTest extends TestBase {

    private DeleteSensorFromHomePlanPage deleteSensorFromHomePlanPage;

    @BeforeClass
    public void beforeClass() {
        deleteSensorFromHomePlanPage = new DeleteSensorFromHomePlanPage(driver);
        deleteSensorFromHomePlanPage.clearHomePlan();
        deleteSensorFromHomePlanPage.goTo();
        deleteSensorFromHomePlanPage.waitUntilHomePlanIsLoaded();
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstNotConnectedSensor);
        deleteSensorFromHomePlanPage.clickOnHomePlan(0, 50); //First sensor
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstNotConnectedSensor);
        deleteSensorFromHomePlanPage.clickOnHomePlan(0, -50); //Second sensor
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstNotConnectedSensor);
        deleteSensorFromHomePlanPage.clickOnHomePlan(0, 0); //Third sensor
    }

    @Test
    public void testAPresenceOfDeleteButtonOnSensor() {

        deleteSensorFromHomePlanPage.clickOnHomePlan(0, 50); //Select sensor on map
        Assert.assertEquals(deleteSensorFromHomePlanPage.isDeleteButtonDisplayed(deleteSensorFromHomePlanPage.firstConnectedSensor), "block");
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.secondConnectedSensor); //Select sensor on list
        Assert.assertEquals(deleteSensorFromHomePlanPage.isDeleteButtonDisplayed(deleteSensorFromHomePlanPage.secondConnectedSensor), "block");
    }

    @Test
    public void testDeletingSensorFromHomePlan() {
        int connectedSensors = deleteSensorFromHomePlanPage.countPointsOnHomePlan();
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstConnectedSensor);
        String firstSensorId = deleteSensorFromHomePlanPage.getSensorId();
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.deleteButton(deleteSensorFromHomePlanPage.firstConnectedSensor));
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.cancelButton);
        int connectedSensorsAfterClickingCancel = deleteSensorFromHomePlanPage.countPointsOnHomePlan();
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.deleteButton(deleteSensorFromHomePlanPage.firstConnectedSensor));
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.okButton);
        deleteSensorFromHomePlanPage.waitForDeletedSensor(firstSensorId);
        int connectedSensorsAfterClickingOk = deleteSensorFromHomePlanPage.countPointsOnHomePlan();
        deleteSensorFromHomePlanPage.refreshPage();
        int connectedSensorsAfterRefreshingPage = deleteSensorFromHomePlanPage.countPointsOnHomePlan();

        Assert.assertEquals(connectedSensorsAfterClickingCancel, connectedSensors);
        Assert.assertEquals(connectedSensorsAfterClickingOk, connectedSensors - 1);
        Assert.assertEquals(connectedSensorsAfterClickingOk, connectedSensorsAfterRefreshingPage);
    }

    @Test
    public void testDeletingSensorWhenOffline() throws InterruptedException, IOException {
        int connectedSensors = deleteSensorFromHomePlanPage.countPointsOnHomePlan();
        String firstSensorId = deleteSensorFromHomePlanPage.getSensorId();
        deleteSensorFromHomePlanPage.setNetworkConnectionOffline(true);
        deleteSensorFromHomePlanPage.waitForAnySnackbar();
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstConnectedSensor);
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.deleteButton(deleteSensorFromHomePlanPage.firstConnectedSensor));
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.okButton);
        int connectedSensorsAfterDeletingOffline = deleteSensorFromHomePlanPage.countPointsOnHomePlan();

        Assert.assertTrue(deleteSensorFromHomePlanPage.isSnackbarForErrorDuringRemovingSensorDisplayed());
        Assert.assertEquals(connectedSensorsAfterDeletingOffline, connectedSensors);

        deleteSensorFromHomePlanPage.setNetworkConnectionOffline(false);
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.firstConnectedSensor);
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.deleteButton(deleteSensorFromHomePlanPage.firstConnectedSensor));
        deleteSensorFromHomePlanPage.clickButton(deleteSensorFromHomePlanPage.okButton);
        deleteSensorFromHomePlanPage.waitForDeletedSensor(firstSensorId);
        int connectedSensorsAfterDeletingOnline = deleteSensorFromHomePlanPage.countPointsOnHomePlan();

        Assert.assertEquals(connectedSensorsAfterDeletingOnline, connectedSensors - 1);
        Assert.assertTrue(deleteSensorFromHomePlanPage.isSnackbarNotDisplayed(), "Snackbar is displayed");
    }

}
