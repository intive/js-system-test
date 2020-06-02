package selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

public class MapTest extends TestBase {
    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goTo();
        assertionIfElementWasFound(dashboardPage.firstConnectedSensor, "first connected sensor");
        dashboardPage.deleteSensorsWhenRequired();
    }

    @Test(priority = 3)
    public void testCursorChangeOnHomePlan() {
        assertionIfElementWasFound(dashboardPage.firstNotConnectedSensor, "first not connected sensor");
        dashboardPage.clickFirstNotConnectedSensor();
        String cursorType = dashboardPage.getCursorType();
        String expectedCursorType = "cell";
        Assert.assertEquals(cursorType, expectedCursorType);
        driver.manage().window().maximize();
        dashboardPage.clickFirstNotConnectedSensor();
    }

    @DataProvider(name = "incorrectOffsets")
    public Object[] incorrectOffsets() {
        int incorrectOffsetHeight = dashboardPage.incorrectOffsetHeight();
        int incorrectOffsetWidth = dashboardPage.incorrectOffsetWidth();
        return new Object[][]{{0, (-100 + incorrectOffsetHeight)}, {0, (-100 - incorrectOffsetHeight)}, {-(incorrectOffsetWidth), -100}, {(incorrectOffsetWidth), -100}};
    }

    @Test(dataProvider = "incorrectOffsets", priority = 5)
    public void testAddingPointWithin3percentCircle(Integer x, Integer y) {
        dashboardPage.waitUntilHomePlanIsLoaded();
        assertionIfElementWasFound(dashboardPage.firstNotConnectedSensor, "first not connected sensor");
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(0, -100);
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(x, y);

        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();

        Assert.assertEquals(lastPointAfterClick, lastPointBeforeClick, "Point was added on home plan");

        if (dashboardPage.checkIfGapNeededBoxIsPresent()) {
            dashboardPage.clickCloseButtonOnGapNeededBox();
        }
    }

    @DataProvider(name = "correctOffsets")
    public Object[][] correctOffsets() {
        int correctOffsetHeight = dashboardPage.correctOffsetHeight();
        int correctOffsetWidth = dashboardPage.correctOffsetWidth();
        return new Object[][]{{0, correctOffsetHeight}, {0, -(correctOffsetHeight)}, {correctOffsetWidth, 0}, {-(correctOffsetWidth), 0}};
    }

    @Test(dataProvider = "correctOffsets", priority = 4)
    public void testAddingNewPointOnHomePlan(Integer x, Integer y) throws InterruptedException {
        dashboardPage.waitUntilHomePlanIsLoaded();
        assertionIfElementWasFound(dashboardPage.firstNotConnectedSensor, "first not connected sensor");
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(0, 0);
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(x, y);
        Thread.sleep(500);
        dashboardPage.clickOnCoordinates(60, 180);
        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();

        Assert.assertNotEquals(lastPointAfterClick, lastPointBeforeClick, "Point was not added on home plan");
        Assert.assertEquals(dashboardPage.getLastPointWidth(), dashboardPage.getLastPointHeight(), "Point is not a circle");  //Check if point is a circle


        String secondSensorId = dashboardPage.getSensorId(dashboardPage.lastConnectedSensor);
        dashboardPage.deleteSecondSensorFromHomePlan();
        dashboardPage.waitForDeletedSensor(secondSensorId);
    }

    @Test(priority = 2)
    public void testHomePlanRWD() {
        dashboardPage.waitUntilHomePlanIsLoaded();
        assertionIfElementWasFound(dashboardPage.firstNotConnectedSensor, "first not connected sensor");
        double mapWidthBeforeResize = dashboardPage.getMapWidth();
        double mapHeightBeforeResize = dashboardPage.getMapHeight();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(100, 100);
        double pointXOffsetBeforeResize = Math.abs(dashboardPage.getXOffsetPointToMap());
        double pointYOffsetBeforeResize = Math.abs(dashboardPage.getYOffsetPointToMap());
        dashboardPage.resizeBrowser();
        dashboardPage.waitUntilHomePlanIsLoaded();
        double mapWidthAfterResize = dashboardPage.getMapWidth();
        double mapHeightAfterResize = dashboardPage.getMapHeight();
        double pointXOffsetAfterResize = Math.abs(dashboardPage.getXOffsetPointToMap());
        double pointYOffsetAfterResize = Math.abs(dashboardPage.getYOffsetPointToMap());
        int mapWidthRatio = (int) Math.round((mapWidthAfterResize * 100) / mapWidthBeforeResize);
        int mapHeightRatio = (int) Math.round((mapHeightAfterResize * 100) / mapHeightBeforeResize);
        int pointXOffsetRatio = (int) Math.round((pointXOffsetAfterResize * 100) / pointXOffsetBeforeResize);
        int pointYOffsetRatio = (int) Math.round((pointYOffsetAfterResize * 100) / pointYOffsetBeforeResize);

        Assert.assertEquals(mapWidthRatio, pointXOffsetRatio, "Width point/map proportion not correct");
        Assert.assertEquals(mapHeightRatio, pointYOffsetRatio, "Height point/map proportion not correct");
    }

    @Test(priority = 1)
    public void testSensorDimension() {
        dashboardPage.waitUntilHomePlanIsLoaded();
        assertionIfElementWasFound(dashboardPage.firstNotConnectedSensor, "first not connected sensor");
        int mapHeight = dashboardPage.getMapHeight();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(-100, -100);
        int pointHeight = dashboardPage.getLastPointHeight();
        int pointWidth = dashboardPage.getLastPointWidth();
        Assert.assertEquals(pointHeight, mapHeight / 20);
        Assert.assertEquals(pointWidth, mapHeight / 20);
    }
}