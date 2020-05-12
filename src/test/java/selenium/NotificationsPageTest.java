package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.NotificationsPage;

import java.text.ParseException;

public class NotificationsPageTest extends TestBase {
    private NotificationsPage notificationsPage;

    @BeforeClass
    public void beforeClass() {
        notificationsPage = new NotificationsPage(driver);
        notificationsPage.goTo();
    }

    @Test
    public void pageIsLoaded() {
        Assert.assertTrue(notificationsPage.notificationsHeaderIsDisplayed(), "Notifications page is not loaded");
    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testNotificationsAreDisplayedByTimestamp() throws ParseException {
        if ((notificationsPage.thereAreActiveNotifications()) && (notificationsPage.getAllActiveNotifications() >= 2)) {
            int timestamp = notificationsPage.compareDateAndTime(notificationsPage.firstActiveNotification, notificationsPage.secondActiveNotification);
            Assert.assertTrue(timestamp >= 0, "Active notifications are not displayed in order by date and time");
        }
        if ((notificationsPage.thereAreInactiveNotifications()) && (notificationsPage.getAllInactiveNotifications() >= 2)) {
            int timestamp = notificationsPage.compareDateAndTime(notificationsPage.firstInactiveNotification, notificationsPage.secondInactiveNotification);
            Assert.assertTrue(timestamp >= 0, "Inactive notifications are not displayed in order by date and time");
        }
    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testPagination() {
        int notificationsCount = notificationsPage.countAllNotificationsOnPage();
        Assert.assertTrue(notificationsCount <= 20, "There are more than 20 notifications on first page");
        if (notificationsCount == 20) {
            if (notificationsPage.showMoreButtonIsDisplayed()) {
                notificationsPage.clickShowMoreButton();
                Assert.assertTrue(notificationsPage.countAllNotificationsOnPage() > 20, "There are less than 20 notifications on second page");
                Assert.assertTrue(notificationsPage.countAllNotificationsOnPage() <= 40, "There are more than 40 notifications on second page");
            }
        }
    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testAccessToPageByLink() {
        notificationsPage.clickAuthorsButton();
        notificationsPage.clickNotificationsButton();
        notificationsPage.clickLinkToNotificationsPage();
        notificationsPage.clickOutsideDrawer(100, 100);
        String expectedUrl = "https://patronage20-js-master.herokuapp.com/notifications";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "No access to notification page by link in drawer");

    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testIntegrationOfNotificationPageWithCounterAndDrawer() {
        int notificationsOnPage = notificationsPage.getAllActiveNotifications();
        int notificationsInCounter = notificationsPage.getActiveNotificationsFromCounter();
        notificationsPage.clickNotificationsButton();
        int notificationsInDrawer = notificationsPage.getAllActiveNotificationsInDrawer();

        Assert.assertEquals(notificationsOnPage, notificationsInDrawer, "Initial count on page and in drawer is not matching");
        Assert.assertEquals(notificationsOnPage, notificationsInCounter, "Initial count on page and in counter is not matching");

        notificationsPage.clickOutsideDrawer(100, 100);

        int notificationsOnPageAfterChangeOnPage;
        int notificationsInDrawerAfterChangeOnPage;
        int notificationsInCounterAfterChangeOnPage = 0;

        if (notificationsInCounter > 0) {
            notificationsPage.deleteActiveNotificationOnPage();
            String expectedCounter = Integer.toString(notificationsInCounter - 1);
            notificationsPage.waitForDeletedNotificationOnPage(expectedCounter);
            notificationsPage.clickNotificationsButton();
            notificationsPage.waitForDeletedNotificationInDrawer(expectedCounter);
            notificationsInDrawerAfterChangeOnPage = notificationsPage.getAllActiveNotificationsInDrawer();
            notificationsPage.clickOutsideDrawer(100, 100);
            notificationsOnPageAfterChangeOnPage = notificationsPage.getAllActiveNotifications();
            notificationsInCounterAfterChangeOnPage = notificationsPage.getActiveNotificationsFromCounter();

            Assert.assertEquals(notificationsOnPageAfterChangeOnPage, notificationsOnPage - 1, "Incorrect count on page");
            Assert.assertEquals(notificationsInDrawerAfterChangeOnPage, notificationsInDrawer - 1, "Incorrect count in drawer");
            Assert.assertEquals(notificationsInCounterAfterChangeOnPage, notificationsInCounter - 1, "Incorrect count in counter");
        }

        int notificationsOnPageAfterChangeInDrawer;
        int notificationsInDrawerAfterChangeInDrawer;
        int notificationsInCounterAfterChangeInDrawer;

        if (notificationsInCounterAfterChangeOnPage > 0) {
            notificationsPage.clickNotificationsButton();
            notificationsPage.deleteNotificationInDrawer();
            String expectedCounter = Integer.toString(notificationsInCounterAfterChangeOnPage - 1);
            notificationsPage.waitForDeletedNotificationInDrawer(expectedCounter);
            notificationsPage.clickOutsideDrawer(100, 100);
            notificationsPage.waitForDeletedNotificationOnPage(expectedCounter);
            notificationsOnPageAfterChangeInDrawer = notificationsPage.getAllActiveNotifications();
            notificationsInCounterAfterChangeInDrawer = notificationsPage.getActiveNotificationsFromCounter();
            notificationsPage.clickNotificationsButton();
            notificationsInDrawerAfterChangeInDrawer = notificationsPage.getAllActiveNotificationsInDrawer();
            notificationsPage.clickOutsideDrawer(100, 100);

            Assert.assertEquals(notificationsOnPageAfterChangeInDrawer, notificationsOnPage - 2, "Incorrect count on page");
            Assert.assertEquals(notificationsInDrawerAfterChangeInDrawer, notificationsInDrawer - 2, "Incorrect count in drawer");
            Assert.assertEquals(notificationsInCounterAfterChangeInDrawer, notificationsInCounter - 2, "Incorrect count in counter");
        }
    }
}
