package selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

public class MapTest extends TestBase {
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeMethod() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clearHomePlan();
        dashboardPage.goTo();
    }

    @Test
    public void testACursorChangeOnHomePlan() {
        dashboardPage.clickFirstNotConnectedSensor();
        String cursorType = dashboardPage.getCursorType();
        String expectedCursorType = "cell";
        Assert.assertEquals(cursorType, expectedCursorType);
    }

    @DataProvider(name = "incorrectOffsets")
    public Object[] incorrectOffsets() {
        int incorrectOffsetHeight = dashboardPage.incorrectOffsetHeight();
        int incorrectOffsetWidth = dashboardPage.incorrectOffsetWidth();
        return new Object[][]{{0, 100 + incorrectOffsetHeight}, {0, (100 - incorrectOffsetHeight)}, {-(incorrectOffsetWidth), 100}, {(incorrectOffsetWidth), 100}};
    }

    @Test(dataProvider = "incorrectOffsets")
    public void testAddingPointWithin3percentCircle(Integer x, Integer y) {
        dashboardPage.waitUntilHomePlanIsLoaded();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(0, 100);
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(x, y);
        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();
        Assert.assertEquals(lastPointAfterClick, lastPointBeforeClick, "Point was added on home plan");
        if (dashboardPage.checkIfGapNeededBoxIsPresent()) {
            dashboardPage.clickOffsetToGapNeededBox(400, 200);
        }

    }

    @DataProvider(name = "correctOffsets")
    public Object[][] correctOffsets() {
        int correctOffsetHeight = dashboardPage.correctOffsetHeight();
        int correctOffsetWidth = dashboardPage.correctOffsetWidth();
        return new Object[][]{{0, correctOffsetHeight}, {0, -(correctOffsetHeight)}, {correctOffsetWidth, 0}, {-(correctOffsetWidth), 0}};
    }

    @Test(dataProvider = "correctOffsets")
    public void testAddingNewPointOnHomePlan(Integer x, Integer y) {
        dashboardPage.waitUntilHomePlanIsLoaded();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(0, 0);
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickFirstNotConnectedSensor();
        dashboardPage.clickToAddPoint(x, y);
        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();
        Assert.assertNotEquals(lastPointAfterClick, lastPointBeforeClick, "Point was not added on home plan");
        Assert.assertEquals(dashboardPage.getLastPointWidth(), dashboardPage.getLastPointHeight(), "Point is not a circle");  //Check if point is a circle
    }

    @Test
    public void testHomePlanRWD() {
        dashboardPage.waitUntilHomePlanIsLoaded();
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

}