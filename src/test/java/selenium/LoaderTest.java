package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

public class LoaderTest extends TestBase {
    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goTo();
    }

    @Test
    public void testLoaderOnDashboardPage() throws InterruptedException {
        Assert.assertTrue(dashboardPage.isLoaderDisplayed(dashboardPage.loader));
    }

}
