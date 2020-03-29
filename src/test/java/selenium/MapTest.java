package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

public class MapTest extends TestBase {
    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goTo();
    }

    @Test
    public void testCursorChangeOnHomePlan() {
        String cursorType = dashboardPage.getCursorType();
        String expectedCursorType = "cell";
        Assert.assertEquals(cursorType, expectedCursorType);
    }

    @DataProvider(name = "incorrectOffsets")
    public Object[][] incorrectOffsets() {
        return new Object[][]{{0, (100 + dashboardPage.incorrectOffsetHeight())}, {0, (100 - dashboardPage.incorrectOffsetHeight())}, {-(dashboardPage.incorrectOffsetWidth()), 100}, {(dashboardPage.incorrectOffsetWidth()), 100}};
    }

    @Test(dataProvider = "incorrectOffsets")
    public void testAddingPointWithin3percentCircle(Integer x, Integer y) {
        dashboardPage.clickToAddPoint(0, 100);
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickToAddPoint(x, y);
        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();
        Assert.assertEquals(lastPointAfterClick, lastPointBeforeClick);
        if (dashboardPage.checkIfGapNeededBoxIsPresent()) {
            dashboardPage.clickOffsetToGapNeededBox(400, 200);
        }
    }

    @DataProvider(name = "correctOffsets")
    public Object[][] correctOffsets() {
        return new Object[][]{{0, 0}, {0, dashboardPage.correctOffsetHeight()}, {0, 2 * (dashboardPage.correctOffsetHeight())}, {0, -(dashboardPage.correctOffsetHeight())}, {0, -2 * (dashboardPage.correctOffsetHeight())}, {(dashboardPage.correctOffsetWidth()), 0}, {2 * (dashboardPage.correctOffsetWidth()), 0}, {-(dashboardPage.correctOffsetWidth()), 0}, {-2 * (dashboardPage.correctOffsetWidth()), 0}};
    }

    @Test(dataProvider = "correctOffsets")
    public void testAddingNewPointOnMap(Integer x, Integer y) {
        String lastPointBeforeClick = dashboardPage.getPointUniqueInformation();
        dashboardPage.clickToAddPoint(x, y);
        String lastPointAfterClick = dashboardPage.getPointUniqueInformation();
        Assert.assertNotEquals(lastPointAfterClick, lastPointBeforeClick);
        Assert.assertEquals(dashboardPage.getLastPointWidth(), dashboardPage.getLastPointHeight());  //Check if point is a circle
    }

    @Test
    public void testMapRWD() {
        double mapWidthBeforeResize = dashboardPage.getMapWidth();
        double mapHeightBeforeResize = dashboardPage.getMapHeight();
        double pointXOffsetBeforeResize = Math.abs(dashboardPage.getXOffsetPointToCenterOfMap());
        double pointYOffsetBeforeResize = Math.abs(dashboardPage.getYOffsetPointToCenterOfMap());
        dashboardPage.resizeBrowser();
        double mapWidthAfterResize = dashboardPage.getMapWidth();
        double mapHeightAfterResize = dashboardPage.getMapHeight();
        double pointXOffsetAfterResize = Math.abs(dashboardPage.getXOffsetPointToCenterOfMap());
        double pointYOffsetAfterResize = Math.abs(dashboardPage.getYOffsetPointToCenterOfMap());
        int mapWidthRatio = (int) Math.round((mapWidthAfterResize * 100) / mapWidthBeforeResize);
        int mapHeightRatio = (int) Math.round((mapHeightAfterResize * 100) / mapHeightBeforeResize);
        int pointXOffsetRatio = (int) Math.round((pointXOffsetAfterResize * 100) / pointXOffsetBeforeResize);
        int pointYOffsetRatio = (int) Math.round((pointYOffsetAfterResize * 100) / pointYOffsetBeforeResize);
        Assert.assertEquals(mapWidthRatio, pointXOffsetRatio);
        Assert.assertEquals(mapHeightRatio, pointYOffsetRatio);
    }

}