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
    public void testAPagination() {
        int notificationsCount = notificationsPage.countAllNotificationsOnPage();
        Assert.assertTrue(notificationsCount <= 20, "There are more than 20 notifications on first page");
        int x = 20;
        int y = 40;
        int page = 2;
        while (notificationsPage.showMoreButtonIsDisplayed()) {
            notificationsPage.clickShowMoreButton();
            Assert.assertTrue(notificationsPage.countAllNotificationsOnPage() > x, "There are less than " + x + " notifications on page " + page);
            Assert.assertTrue(notificationsPage.countAllNotificationsOnPage() <= y, "There are more than " + y + " notifications on page " + page);
            x = x + 20;
            y = y + 20;
            page++;
        }
    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testTheAccessToPageByLink() {
        notificationsPage.clickOutsideDrawer(100, 100);
        notificationsPage.clickAuthorsButton();
        notificationsPage.clickNotificationsButton();
        notificationsPage.clickLinkToNotificationsPage();
        String expectedUrl = "https://patronage20-js-master.herokuapp.com/notifications";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "No access to notification page by link in drawer");

    }

    @Test(dependsOnMethods = {"pageIsLoaded"})
    public void testIntegrationOfNotificationPageWithCounterAndDrawer() throws InterruptedException {
        while (notificationsPage.activeNotificationsCounter.getText().equals("99+")) {
            notificationsPage.deleteActiveNotificationOnPage();
        }
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
            String firstActiveNotificationTimestamp = notificationsPage.getDataAndTimeOfNotification(notificationsPage.firstActiveNotification);
            notificationsPage.deleteActiveNotificationOnPage();
            Thread.sleep(2000);
            notificationsOnPageAfterChangeOnPage = notificationsPage.getAllActiveNotifications();
            notificationsInCounterAfterChangeOnPage = notificationsPage.getActiveNotificationsFromCounter();
            notificationsPage.clickNotificationsButton();
            notificationsInDrawerAfterChangeOnPage = notificationsPage.getAllActiveNotificationsInDrawer();

            if (notificationsOnPageAfterChangeOnPage == (notificationsOnPage - 1)) {
                Assert.assertEquals(notificationsOnPageAfterChangeOnPage, notificationsOnPage - 1, "Incorrect count on page when deleting notification on page");
                Assert.assertEquals(notificationsInDrawerAfterChangeOnPage, notificationsInDrawer - 1, "Incorrect count in drawer when deleting notification on page");
                Assert.assertEquals(notificationsInCounterAfterChangeOnPage, notificationsInCounter - 1, "Incorrect count in counter when deleting notification on page");
            } else {
                Assert.assertTrue(notificationsPage.notificationIsInactive(firstActiveNotificationTimestamp), "Notification has not been deleted");
                Assert.assertEquals(notificationsPage.getAllActiveNotifications(), notificationsPage.getActiveNotificationsFromCounter(), "Number of notifications on page and in counter does not match after deleting notification on page");
            }

        }

        int notificationsOnPageAfterChangeInDrawer;
        int notificationsInDrawerAfterChangeInDrawer;
        int notificationsInCounterAfterChangeInDrawer;

        if (notificationsInCounterAfterChangeOnPage > 0) {
            notificationsPage.clickOutsideDrawer(100, 100);
            notificationsInCounter = notificationsPage.getActiveNotificationsFromCounter();
            notificationsOnPage = notificationsPage.getAllActiveNotifications();
            notificationsPage.clickNotificationsButton();
            notificationsInDrawer = notificationsPage.getAllActiveNotificationsInDrawer();
            String firstActiveNotificationTimestamp = notificationsPage.getDataAndTimeOfNotification(notificationsPage.firstNotificationInDrawer);
            notificationsPage.deleteNotificationInDrawer();
            Thread.sleep(2000);
            notificationsInDrawerAfterChangeInDrawer = notificationsPage.getAllActiveNotificationsInDrawer();
            notificationsPage.clickOutsideDrawer(100, 100);
            notificationsOnPageAfterChangeInDrawer = notificationsPage.getAllActiveNotifications();
            notificationsInCounterAfterChangeInDrawer = notificationsPage.getActiveNotificationsFromCounter();

            if (notificationsOnPageAfterChangeInDrawer == (notificationsInDrawer - 1)) {
                Assert.assertEquals(notificationsOnPageAfterChangeInDrawer, notificationsOnPage - 1, "Incorrect count on page when deleting notification in drawer");
                Assert.assertEquals(notificationsInDrawerAfterChangeInDrawer, notificationsInDrawer - 1, "Incorrect count in drawer when deleting notification in drawer");
                Assert.assertEquals(notificationsInCounterAfterChangeInDrawer, notificationsInCounter - 1, "Incorrect count in counter when deleting notification in drawer");
            } else {
                Assert.assertTrue(notificationsPage.notificationIsInactive(firstActiveNotificationTimestamp), "Notification has not been deleted");
                Assert.assertEquals(notificationsPage.getAllActiveNotifications(), notificationsPage.getActiveNotificationsFromCounter(), "Number of notifications on page and in counter does not match after deleting notification in drawer");
            }
        }

    }
}
