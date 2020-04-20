package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.MarkSensorOnHomePlanPage;

import java.util.HashMap;
import java.util.Map;

public class MarkSensorOnHomePlanTest extends TestBase {
    private MarkSensorOnHomePlanPage markSensorOnHomePlanPage;

    @BeforeClass
    public void beforeClass() {
        markSensorOnHomePlanPage = new MarkSensorOnHomePlanPage(driver);
        markSensorOnHomePlanPage.clearHomePlan();
        markSensorOnHomePlanPage.goTo();
    }

    @DataProvider(name = "positionsOnHomePlan")
    public Object [][] positionsOnHomePlan() {
        return new Object[][] {{0,0}, {-100,0},{100,0},{0,-100},{0,100}};
    }

    @Test(dataProvider = "positionsOnHomePlan")
    public void testABorderOfSelectedSensorOnHomePlan(Integer x, Integer y) throws InterruptedException {
        markSensorOnHomePlanPage.clickFirstNotConnectedSensorOnList();
        markSensorOnHomePlanPage.clickOnHomePlan(x, y);  // Add sensor on home plan
        markSensorOnHomePlanPage.clickOnHomePlan(x, y);  // Click on sensor on home plan to select it
        Thread.sleep(500);
        //markSensorOnHomePlanPage.waitForCompleteBackgroundColor(markSensorOnHomePlanPage.sensorOnConnectedSensorsList);
        String sensorOnHomePlanBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.sensorOnHomePlan);
        String actualSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.sensorOnHomePlan);
        String actualSensorOnConnectedSensorsListBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.sensorOnConnectedSensorsList);
        String expectedSensorOnHomePlanBorderColor = sensorBackgroundAndBorderColorMap().get(sensorOnHomePlanBackgroundColor);

        System.out.println(sensorOnHomePlanBackgroundColor);
        System.out.println(actualSensorOnConnectedSensorsListBackgroundColor);
        System.out.println(expectedSensorOnHomePlanBorderColor);
        System.out.println(actualSensorOnHomePlanBorderColor);

        Assert.assertEquals(actualSensorOnHomePlanBorderColor,expectedSensorOnHomePlanBorderColor);
        Assert.assertEquals(actualSensorOnConnectedSensorsListBackgroundColor,sensorOnHomePlanBackgroundColor);
    }

    public Map<String, String> sensorBackgroundAndBorderColorMap() {
        Map<String, String> backgroundAndBorderColor = new HashMap<>();
        backgroundAndBorderColor.put("rgba(255, 153, 51, 1)", "rgb(204, 102, 0)");
        backgroundAndBorderColor.put("rgba(136, 77, 255, 1)", "rgb(85, 26, 204)");
        backgroundAndBorderColor.put("rgba(224, 81, 148, 1)", "rgb(176, 32, 97)");
        backgroundAndBorderColor.put("rgba(255, 141, 133, 1)", "rgb(204, 90, 82)");
        backgroundAndBorderColor.put("rgba(128, 128, 128, 1)", "rgb(80, 80, 80)");
        return backgroundAndBorderColor;
    }

    @Test
    public void testBorderAndBackgroundColorOfNotSelectedSensorOnHomePlan() {
        markSensorOnHomePlanPage.clickOnHomePlan(0, 0);  // Select first sensor on home plan
        String actualBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String expectedBorderColor = "rgb(0, 0, 0)";
        String actualBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String expectedBackgroundColor = "rgba(0, 0, 0, 0)";

        Assert.assertEquals(actualBorderColor,expectedBorderColor);
        Assert.assertEquals(actualBackgroundColor,expectedBackgroundColor);
    }

    @Test
    public void testScrollAutoToSelectedSensor() {
        markSensorOnHomePlanPage.resizeBrowser();
        WebElement sensor = markSensorOnHomePlanPage.sensorOnConnectedSensorsList;
        Assert.assertFalse(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
        markSensorOnHomePlanPage.clickLastSensorOnHomePlan();
        markSensorOnHomePlanPage.waitForScrollToComplete(sensor);
        Assert.assertTrue(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
    }
}
