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
        assertionIfElementWasFound(addSensorOnMapPage.firstConnectedSensor, "first connected sensor");
        addSensorOnMapPage.deleteSensorsWhenRequired();
    }

    @Test
    public void testChangeOfBackgroundColorAndElevation() {
        assertionIfElementWasFound(addSensorOnMapPage.firstNotConnectedSensor, "first not connected sensor");
        assertionIfElementWasFound(addSensorOnMapPage.secondNotConnectedSensor, "second not connected sensor");
        WebElement element = addSensorOnMapPage.firstNotConnectedSensor;
        WebElement secondElement = addSensorOnMapPage.secondNotConnectedSensor;
        addSensorOnMapPage.clickNotConnectedSensorOnList();
        addSensorOnMapPage.moveMouseToElement(addSensorOnMapPage.homePlan);
        String selectedSensorBackgroundColor = addSensorOnMapPage.getElementBackgroundColor(element);
        String notSelectedSensorBackgroundColor = addSensorOnMapPage.getElementBackgroundColor(secondElement);
        String whiteBackgroundColor = "rgba(255, 255, 255, 1)";
        String actualElevationValueOfSelectedSensor = addSensorOnMapPage.elevationValueOfSensor(addSensorOnMapPage.firstNotConnectedSensor);
        String actualElevationValueOfNotSelectedSensor = addSensorOnMapPage.elevationValueOfSensor(addSensorOnMapPage.secondNotConnectedSensor);
        String noElevationDisplayed = "none";

        Assert.assertEquals(selectedSensorBackgroundColor, whiteBackgroundColor, "Selected sensor background color is not white");
        Assert.assertEquals(notSelectedSensorBackgroundColor, whiteBackgroundColor, "Not selected sensor background color is not white");
        Assert.assertNotEquals(actualElevationValueOfSelectedSensor, noElevationDisplayed, "Selected sensor is not elevated");
        Assert.assertEquals(actualElevationValueOfNotSelectedSensor, noElevationDisplayed, "Not selected sensor is elevated");
    }

    @Test
    public void testAddingSensorOnHomePlan() {
        assertionIfElementWasFound(addSensorOnMapPage.firstNotConnectedSensorType, "type of first not connected sensor");
        assertionIfElementWasFound(addSensorOnMapPage.firstNotConnectedSensorId, "Id of first not connected sensor");
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
