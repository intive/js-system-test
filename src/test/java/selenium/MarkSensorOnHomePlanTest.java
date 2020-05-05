package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.MarkSensorOnHomePlanPage;

import java.util.List;


public class MarkSensorOnHomePlanTest extends TestBase {
    private MarkSensorOnHomePlanPage markSensorOnHomePlanPage;

    @BeforeClass
    public void beforeClass() {
        markSensorOnHomePlanPage = new MarkSensorOnHomePlanPage(driver);
        markSensorOnHomePlanPage.goTo();
        markSensorOnHomePlanPage.addPointsOnHomePlanWhenRequired();
    }

    @Test
    public void testABorderOfSelectedSensorOnHomePlan() {
        List<WebElement> allPointsOnHomePlan = markSensorOnHomePlanPage.pointsOnHomePlan();
        List<WebElement> allConnectedSensors = markSensorOnHomePlanPage.sensorsOnList();
        for (int i = 0; i < allPointsOnHomePlan.size(); i++) {
            markSensorOnHomePlanPage.clickSensorOnHomePlan(allPointsOnHomePlan.get(i));  // Click on sensor on home plan to select it
            String actualSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(allPointsOnHomePlan.get(i));
            String actualSensorOnConnectedSensorsListBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(allConnectedSensors.get(i));
            String sensorOnHomePlanBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(allPointsOnHomePlan.get(i));
            String expectedSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.sensorBackgroundAndBorderColorMap().get(sensorOnHomePlanBackgroundColor);
            String expectedCharSequenceInBackgroundColor = markSensorOnHomePlanPage.getSensorTypeBackgroundColor(allConnectedSensors.get(i));
            System.out.println(actualSensorOnConnectedSensorsListBackgroundColor);
            Assert.assertEquals(actualSensorOnHomePlanBorderColor, expectedSensorOnHomePlanBorderColor); //Check if selected sensor on home plan has border color of color matching sensor type
            Assert.assertTrue(actualSensorOnConnectedSensorsListBackgroundColor.contains(expectedCharSequenceInBackgroundColor)); //Check if selected sensor on connected sensors list has background color of color matching sensor type
        }
    }

    @Test
    public void testBorderAndBackgroundColorOfNotSelectedSensorOnHomePlan() {
        markSensorOnHomePlanPage.clickSensorOnHomePlan(markSensorOnHomePlanPage.firstSensorOnHomePlan);  // Select first sensor on home plan
        String actualBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String expectedBorderColor = "rgb(0, 0, 0)";
        String actualBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String expectedBackgroundColor = "rgba(255, 255, 255, 1)";
        Assert.assertEquals(actualBorderColor, expectedBorderColor);
        Assert.assertEquals(actualBackgroundColor, expectedBackgroundColor);
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
