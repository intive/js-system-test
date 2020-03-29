package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

import java.util.List;

public class NumberOfSectionsDashboardTest extends TestBase {

    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);

        dashboardPage.goTo();
    }

    @Test
    public void testNumberOfFields() throws InterruptedException {
        Thread.sleep(1000);

        Assert.assertEquals(dashboardPage.getNumberOfSections(), 3);
    }


}
