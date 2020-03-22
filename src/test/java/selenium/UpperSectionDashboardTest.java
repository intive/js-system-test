package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.DashboardPage;

import java.util.Arrays;
import java.util.List;

public class UpperSectionDashboardTest extends TestBase {
    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        dashboardPage = new DashboardPage(driver);

        dashboardPage.goTo();
    }

    @Test
    public void testSectionNameText() {
        List<String> expectedSections = Arrays.asList("dashboard", "hvac", "autorzy");
        List<String> actualSections = dashboardPage.getSectionNames();

        Assert.assertEquals(actualSections, expectedSections);

        String expectedApplicationName = "Smart Home";
        String actualApplicationName = dashboardPage.getApplicationName();

        Assert.assertEquals(actualApplicationName, expectedApplicationName);

        String expectedNotificationButtonText = "notifications";
        String actualNotificationButtonText = dashboardPage.getNotificationsLabel();

        Assert.assertEquals(actualNotificationButtonText, expectedNotificationButtonText);
    }

    @Test
    public void testActionAfterClickingApplicationName() {
        dashboardPage.clickApplicationName();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://patronage20-js-master.herokuapp.com/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test
    public void testActionAfterClickingNotificationButton() {
        dashboardPage.clickNotifications();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://patronage20-js-master.herokuapp.com/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test
    public void testOpeningDashboardPage() {
        dashboardPage.clickDashboard();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://patronage20-js-master.herokuapp.com/";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test
    public void testOpeningHvacPage() {
        dashboardPage.clickHvac();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://patronage20-js-master.herokuapp.com/hvac";
        Assert.assertEquals(currentURL, expectedURL);
    }

    @Test
    public void testOpeningAuthorsPage() {
        dashboardPage.clickAuthors();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://patronage20-js-master.herokuapp.com/authors";
        Assert.assertEquals(currentURL, expectedURL);
    }


}
