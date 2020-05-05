package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.AddSensorOnHomePlanPage;


public class AddSensorOnHomePlanTest extends TestBase {

    private AddSensorOnHomePlanPage addSensorOnMapPage;

    @BeforeClass
    public void beforeClass() {
        addSensorOnMapPage = new AddSensorOnHomePlanPage(driver);
        addSensorOnMapPage.goTo();
        addSensorOnMapPage.deleteSensorsWhenRequired();
    }

    @Test
    public void testChangeOfBackgroundColor() {

        WebElement element = addSensorOnMapPage.firstNotConnectedSensor;
        WebElement secondElement = addSensorOnMapPage.secondNotConnectedSensor;
        String sensorTypeBackgroundColor = addSensorOnMapPage.getSensorTypeBackgroundColor(element);
        addSensorOnMapPage.clickNotConnectedSensorOnList();
        addSensorOnMapPage.moveMouseToElement(addSensorOnMapPage.homePlan);
        addSensorOnMapPage.waitForCompleteBackgroundColor(element);
        String selectedSensorBackgroundColor = addSensorOnMapPage.getElementBackgroundColor(element);
        String notSelectedSensorBackgroundColor = addSensorOnMapPage.getElementBackgroundColor(secondElement);
        String whiteBackgroundColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(selectedSensorBackgroundColor, sensorTypeBackgroundColor, "Sensor background color is not matching sensor color");
        Assert.assertEquals(notSelectedSensorBackgroundColor, whiteBackgroundColor, "Not selected sensor background color is not white");
    }

    @Test
    public void testAddingSensorOnHomePlan() {
        String notConnectedSensorType = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstNotConnectedSensorType);
        String notConnectedSensorId = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstNotConnectedSensorId);
        addSensorOnMapPage.clickNotConnectedSensorOnList();
        addSensorOnMapPage.moveMouseToElement(addSensorOnMapPage.homePlan);
        addSensorOnMapPage.clickToAddPointOnHomePlan(0, 0);
        String sensorOnHomePlanColor = addSensorOnMapPage.getElementBackgroundColor(addSensorOnMapPage.pointOnHomePlan);
        String connectedSensorType = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstConnectedSensorType);
        String connectedSensorId = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstConnectedSensorId);
        String whiteBackgroundColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(sensorOnHomePlanColor, whiteBackgroundColor);

        Assert.assertEquals(notConnectedSensorType, connectedSensorType);
        Assert.assertEquals(notConnectedSensorId, connectedSensorId);

        Assert.assertEquals(addSensorOnMapPage.isSensorNotClickable(), null, "Sensors are not clickable");
    }

    @Test
    public void testAddingPointOnHomePlanWithoutSelectingSensor() {
        String cursorType = addSensorOnMapPage.getCursorType();
        String expectedCursorType = "not-allowed";
        Assert.assertEquals(cursorType, expectedCursorType);
    }
}
