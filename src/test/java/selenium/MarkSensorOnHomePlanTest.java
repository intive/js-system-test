package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.MarkSensorOnHomePlanPage;


public class MarkSensorOnHomePlanTest extends TestBase {
    private MarkSensorOnHomePlanPage markSensorOnHomePlanPage;

    @BeforeClass
    public void beforeClass() {
        markSensorOnHomePlanPage = new MarkSensorOnHomePlanPage(driver);
        markSensorOnHomePlanPage.goTo();
        markSensorOnHomePlanPage.deleteSensorsWhenRequired();
    }

    @DataProvider(name = "offsets")
    public Object[][] offsets() {
        return new Object[][]{{0, 0}, {0, 50}, {50, 0}, {-50, 0}, {0, -50}, {0, 100}, {0, -100}, {100, 0}, {-100, 0}, {-150, 0}, {150, 0}};
    }

    @Test(dataProvider = "offsets")
    public void testABorderOfSelectedSensorOnHomePlan(Integer x, Integer y) {
        markSensorOnHomePlanPage.addPointsOnHomePlan(x, y);
        markSensorOnHomePlanPage.clickOnHomePlan(x, y); // Click on sensor on home plan to select it
        String actualSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.lastSensorOnHomePlan);
        String actualSensorOnConnectedSensorsListBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList);
        String expectedSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList);
        String expectedCharSequenceInBackgroundColor = markSensorOnHomePlanPage.getSensorTypeBackgroundColor(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList);
        String actualElevationValue = markSensorOnHomePlanPage.elevationValueOfSensorOnHomePlan(markSensorOnHomePlanPage.lastSensorOnHomePlan);
        String notExpectedElevationValue = "none";

        Assert.assertNotEquals(actualElevationValue, notExpectedElevationValue, "Sensor on home plan does not have elevation");
        Assert.assertTrue(actualSensorOnConnectedSensorsListBackgroundColor.contains(expectedCharSequenceInBackgroundColor), "Incorrect background color of sensor on list");
        if (!(markSensorOnHomePlanPage.getSensorId(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList).contains("sensor2"))) {
            Assert.assertEquals(actualSensorOnHomePlanBorderColor, expectedSensorOnHomePlanBorderColor, "Incorrect border color of sensor on home plan");
        }
    }

    @Test
    public void testBorderAndBackgroundColorOfNotSelectedSensorOnHomePlan() {
        markSensorOnHomePlanPage.clickSensorOnHomePlan(markSensorOnHomePlanPage.firstSensorOnHomePlan);  // Select first sensor on home plan
        String actualBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String expectedBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String actualBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String expectedBackgroundColor = "rgba(255, 255, 255, 1)";
        String actualElevationValue = markSensorOnHomePlanPage.elevationValueOfSensorOnHomePlan(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        Assert.assertEquals(actualBorderColor, expectedBorderColor);
        Assert.assertEquals(actualBackgroundColor, expectedBackgroundColor);
        Assert.assertEquals(actualElevationValue, "none");
    }

    @Test
    public void testScrollAutoToSelectedSensor() throws InterruptedException {
        markSensorOnHomePlanPage.resizeBrowser();
        markSensorOnHomePlanPage.waitUntilHomePlanIsLoaded();
        WebElement sensor = markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList;
        Assert.assertFalse(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
        markSensorOnHomePlanPage.clickLastSensorOnHomePlan();
        markSensorOnHomePlanPage.waitForScrollToComplete(sensor);
        Assert.assertTrue(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
    }
}
