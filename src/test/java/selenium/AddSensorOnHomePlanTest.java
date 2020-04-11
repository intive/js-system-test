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
    }

    @Test
    public void testChangeOfBackgroundColor() throws InterruptedException {
        WebElement element = addSensorOnMapPage.firstNotConnectedSensor;
        String sensorTypeColor = addSensorOnMapPage.getUniversalSensorTypeColor(element);
        addSensorOnMapPage.clickNotConnectedSensorOnList();
        addSensorOnMapPage.moveMouseToElement(addSensorOnMapPage.homePlan);
        Thread.sleep(160);
        String selectedSensorBackgroundColor = addSensorOnMapPage.getElementBackgroundColor(element);
        Assert.assertEquals(selectedSensorBackgroundColor, sensorTypeColor, "Sensor background color is not matching sensor color");
    }

    @Test
    public void testAddingSensorOnHomePlan() throws InterruptedException {
        String notConnectedSensorType = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstNotConnectedSensorType);
        String notConnectedSensorId = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.firstNotConnectedSensorId);
        addSensorOnMapPage.clickNotConnectedSensorOnList();
        addSensorOnMapPage.moveMouseToElement(addSensorOnMapPage.homePlan);
        Thread.sleep(160);
        String selectedSensorColor = addSensorOnMapPage.getElementBackgroundColor(addSensorOnMapPage.firstNotConnectedSensor);
        addSensorOnMapPage.clickToAddPointOnHomePlan(0, 0);
        String sensorOnHomePlanColor = addSensorOnMapPage.getElementBackgroundColor(addSensorOnMapPage.pointOnHomePlan);
        String connectedSensorType = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.connectedSensorType);
        String connectedSensorId = addSensorOnMapPage.getTextFromElement(addSensorOnMapPage.connectedSensorId);

        Assert.assertEquals(sensorOnHomePlanColor, selectedSensorColor, "Sensor on home plan color is not matching sensor type color");

        Assert.assertEquals(notConnectedSensorType, connectedSensorType);
        Assert.assertEquals(notConnectedSensorId, connectedSensorId);

        Assert.assertEquals(addSensorOnMapPage.isSensorNotClickable(), "true", "Sensors is clickable");
    }

    @Test
    public void testAddingPointOnHomePlanWithoutSelectingSensor() {
        String cursorType = addSensorOnMapPage.getCursorType();
        String expectedCursorType = "not-allowed";
        Assert.assertEquals(cursorType, expectedCursorType);
    }
}
