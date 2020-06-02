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
        assertionIfElementWasFound(markSensorOnHomePlanPage.firstConnectedSensor, "first connected sensor");
        markSensorOnHomePlanPage.deleteSensorsWhenRequired();
    }

    @DataProvider(name = "offsets")
    public Object[][] offsets() {
        return new Object[][]{{0, 0}, {0, 50}, {50, 0}, {-50, 0}, {0, -50}, {0, -100}, {0, -150}, {100, 0}, {150, 0}, {-100, 0}, {-150, 0}};
    }

    @Test(dataProvider = "offsets", priority = 1)
    public void testBorderAndElevationOfSelectedSensorOnHomePlan(Integer x, Integer y) throws InterruptedException {
        assertionIfElementWasFound(markSensorOnHomePlanPage.firstNotConnectedSensor, "first not connected sensor");
        markSensorOnHomePlanPage.addPointsOnHomePlan(x, y);
        Thread.sleep(500);
        assertionIfElementWasFound(markSensorOnHomePlanPage.lastSensorOnHomePlan, "last sensor on home plan");
        assertionIfElementWasFound(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList, "last sensor on connected sensors list");
        markSensorOnHomePlanPage.clickOnHomePlan(x, y); // Click on sensor on home plan to select it
        String actualSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.lastSensorOnHomePlan);
        String expectedSensorOnHomePlanBorderColor = "rgb(68, 68, 68)";
        String actualElevationValueOfSensorOnHomePlan = markSensorOnHomePlanPage.elevationValueOfSensor(markSensorOnHomePlanPage.lastSensorOnHomePlan);
        String actualElevationValueOfSensorOnTheList = markSensorOnHomePlanPage.elevationValueOfSensor(markSensorOnHomePlanPage.lastSensorOnHomePlan);
        String noElevationDisplayed = "none";

        Assert.assertNotEquals(actualElevationValueOfSensorOnHomePlan, noElevationDisplayed, "Sensor on home plan does not have elevation");
        Assert.assertNotEquals(actualElevationValueOfSensorOnTheList, noElevationDisplayed, "Sensor on the list does not have elevation");
        if (!(markSensorOnHomePlanPage.getSensorId(markSensorOnHomePlanPage.lastSensorOnConnectedSensorsList).contains("sensor2"))) {
            Assert.assertEquals(actualSensorOnHomePlanBorderColor, expectedSensorOnHomePlanBorderColor, "Incorrect border color of sensor on home plan");
        }
    }

    @Test(priority = 2)
    public void testBorderAndBackgroundColorAndElevationOfNotSelectedSensorOnHomePlan() {
        assertionIfElementWasFound(markSensorOnHomePlanPage.firstSensorOnHomePlan, "first sensor on home plan");
        assertionIfElementWasFound(markSensorOnHomePlanPage.secondSensorOnHomePlan, "second sensor on home plan");
        assertionIfElementWasFound(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList, "second sensor on connected sensors list");
        markSensorOnHomePlanPage.clickSensorOnHomePlan(markSensorOnHomePlanPage.firstSensorOnHomePlan);  // Select first sensor on home plan
        String actualBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String expectedBorderColor = "rgb(68, 68, 68)";
        String actualBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String expectedBackgroundColor = "rgba(255, 255, 255, 1)";
        String actualElevationValueOfSensorOnHomePlan = markSensorOnHomePlanPage.elevationValueOfSensor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String actualElevationValueOfSensorOnTheList = markSensorOnHomePlanPage.elevationValueOfSensor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        Assert.assertEquals(actualBorderColor, expectedBorderColor);
        Assert.assertEquals(actualBackgroundColor, expectedBackgroundColor);
        Assert.assertEquals(actualElevationValueOfSensorOnHomePlan, "none");
        Assert.assertEquals(actualElevationValueOfSensorOnTheList, "none");
    }

    @Test(priority = 3)
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
