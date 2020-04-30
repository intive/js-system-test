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
        markSensorOnHomePlanPage.clearHomePlan();
        markSensorOnHomePlanPage.goTo();
    }

    @DataProvider(name = "positionsOnHomePlan")
    public Object[][] positionsOnHomePlan() {
        return new Object[][]{{0, 0}, {-100, 0}, {100, 0}, {0, -100}, {0, 100}};
    }

    @Test(dataProvider = "positionsOnHomePlan")
    public void testABorderOfSelectedSensorOnHomePlan(Integer x, Integer y) {
        markSensorOnHomePlanPage.clickFirstNotConnectedSensorOnList();
        markSensorOnHomePlanPage.clickOnHomePlan(x, y);  // Add sensor on home plan
        markSensorOnHomePlanPage.clickOnHomePlan(x, y);  // Click on sensor on home plan to select it

        String actualSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.sensorOnHomePlan);
        String actualSensorOnConnectedSensorsListBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.sensorOnConnectedSensorsList);
        String sensorOnHomePlanBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.sensorOnHomePlan);
        String expectedSensorOnHomePlanBorderColor = markSensorOnHomePlanPage.sensorBackgroundAndBorderColorMap().get(sensorOnHomePlanBackgroundColor);
        String expectedCharSequenceInBackgroundColor = markSensorOnHomePlanPage.getSensorTypeBackgroundColor(markSensorOnHomePlanPage.sensorOnConnectedSensorsList);

        Assert.assertEquals(actualSensorOnHomePlanBorderColor, expectedSensorOnHomePlanBorderColor); //Check if selected sensor on home plan has border color of color matching sensor type
        Assert.assertTrue(actualSensorOnConnectedSensorsListBackgroundColor.contains(expectedCharSequenceInBackgroundColor)); //Check if selected sensor on connected sensors list has background color of color matching sensor type
    }

    @Test
    public void testBorderAndBackgroundColorOfNotSelectedSensorOnHomePlan() {
        markSensorOnHomePlanPage.clickOnHomePlan(0, 0);  // Select first sensor on home plan
        String actualBorderColor = markSensorOnHomePlanPage.getElementBorderColor(markSensorOnHomePlanPage.secondSensorOnHomePlan);
        String expectedBorderColor = "rgb(0, 0, 0)";
        String actualBackgroundColor = markSensorOnHomePlanPage.getElementBackgroundColor(markSensorOnHomePlanPage.secondSensorOnConnectedSensorsList);
        String expectedBackgroundColor = "rgba(0, 0, 0, 0)";

        Assert.assertEquals(actualBorderColor, expectedBorderColor);
        Assert.assertEquals(actualBackgroundColor, expectedBackgroundColor);
    }

    @Test
    public void testScrollAutoToSelectedSensor() throws InterruptedException {
        markSensorOnHomePlanPage.resizeBrowser();
        markSensorOnHomePlanPage.waitUntilHomePlanIsLoaded();
        WebElement sensor = markSensorOnHomePlanPage.sensorOnConnectedSensorsList;
        Assert.assertFalse(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
        markSensorOnHomePlanPage.clickLastSensorOnHomePlan();
        markSensorOnHomePlanPage.waitForScrollToComplete(sensor);
        Assert.assertTrue(markSensorOnHomePlanPage.isSensorInViewPort(sensor));
    }
}
