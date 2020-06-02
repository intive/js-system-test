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
        deleteSensorFromHomePlanPage.goTo();
        assertionIfElementWasFound(deleteSensorFromHomePlanPage.firstNotConnectedSensor, "first not connected sensor");
        deleteSensorFromHomePlanPage.addPointsOnHomePlanWhenRequired();
    }

    @Test
    public void testAPresenceOfDeleteButtonOnSensor() {
        assertionIfElementWasFound(deleteSensorFromHomePlanPage.firstConnectedSensor, "first connected sensor");
        assertionIfElementWasFound(deleteSensorFromHomePlanPage.secondConnectedSensor, "second connected sensor");
        deleteSensorFromHomePlanPage.clickSensorOnHomePlan();
        Assert.assertEquals(deleteSensorFromHomePlanPage.isDeleteButtonDisplayed(deleteSensorFromHomePlanPage.firstConnectedSensor), "flex");
        deleteSensorFromHomePlanPage.clickSensorOnList(deleteSensorFromHomePlanPage.secondConnectedSensor);
        Assert.assertEquals(deleteSensorFromHomePlanPage.isDeleteButtonDisplayed(deleteSensorFromHomePlanPage.secondConnectedSensor), "flex");
    }

    @Test
    public void testDeletingSensorFromHomePlan() {
        assertionIfElementWasFound(deleteSensorFromHomePlanPage.firstConnectedSensor, "first connected sensor");
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
        assertionIfElementWasFound(deleteSensorFromHomePlanPage.firstConnectedSensor, "first connected sensor");
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
