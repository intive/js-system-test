package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

public class DashboardTest extends TestBase {

    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);

        dashboardPage.goTo();
    }

    @Test
    public void testNumberOfFields() {

        Assert.assertEquals(dashboardPage.getNumberOfSections(), 3);
    }


}
