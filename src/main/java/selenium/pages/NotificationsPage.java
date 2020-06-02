package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationsPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[1]/ul")
    private WebElement activeNotifications;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/ul")
    private WebElement inactiveNotifications;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[1]/ul/div[1]")
    public WebElement firstActiveNotification;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[1]/ul/div[2]")
    public WebElement secondActiveNotification;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/ul/div[1]")
    public WebElement firstInactiveNotification;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/ul/div[2]")
    public WebElement secondInactiveNotification;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/button")
    private WebElement showMoreButtonDiv2;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[3]/button")
    private WebElement showMoreButtonDiv3;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/header/div/div[2]/button/span[1]/span/span")
    public WebElement activeNotificationsCounter;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/header/div/div[2]/button")
    private WebElement notificationsButton;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[2]/ul")
    private WebElement activeNotificationsInDrawer;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[2]/ul/div[1]")
    public WebElement firstNotificationInDrawer;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/header/div/div[1]/div/div/a[3]")
    private WebElement authorsButton;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]")
    private WebElement notificationPageLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/h6")
    private WebElement notificationsHeader;

    public NotificationsPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/notifications");
    }

    public String getDataAndTimeOfNotification(WebElement element) {
        return (element.findElement(By.tagName("p")).getText());
    }

    public Date getDateAndTimeFormat(WebElement element) throws ParseException {
        String sData = getDataAndTimeOfNotification(element);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        Date date = formatter.parse(sData);
        return date;
    }

    public Integer compareDateAndTime(WebElement firstNotification, WebElement secondNotification) throws ParseException {
        return getDateAndTimeFormat(firstNotification).compareTo(getDateAndTimeFormat(secondNotification));

    }

    public Integer getAllActiveNotifications() {
        int i;
        if (thereAreActiveNotifications()) {
            List<WebElement> allActiveNotifications = activeNotifications.findElements(By.tagName("li"));
            i = allActiveNotifications.size();
        } else {
            i = 0;
        }
        return i;
    }

    public Integer getAllActiveNotificationsInDrawer() {
        int i;
        if (thereAreNotificationsInDrawer()) {
            List<WebElement> allActiveNotificationsInDrawer = activeNotificationsInDrawer.findElements(By.tagName("li"));
            i = allActiveNotificationsInDrawer.size();
        } else {
            i = 0;
        }
        return i;
    }

    public Integer getAllInactiveNotifications() {
        int i;
        if (thereAreInactiveNotifications()) {
            List<WebElement> allInactiveNotifications = inactiveNotifications.findElements(By.tagName("li"));
            i = allInactiveNotifications.size();
        } else {
            i = 0;
        }
        return i;
    }

    public boolean thereAreActiveNotifications() {
        return isElementDisplayed(driver, activeNotifications);
    }

    public boolean thereAreNotificationsInDrawer() {
        return isElementDisplayed(driver, activeNotificationsInDrawer);
    }

    public boolean thereAreInactiveNotifications() {
        return isElementDisplayed(driver, inactiveNotifications);
    }

    public boolean showMoreButtonIsDisplayed() {
        boolean elementDisplayed = false;
        if (thereAreActiveNotifications() && thereAreInactiveNotifications()) {
            elementDisplayed = isElementDisplayed(driver, showMoreButtonDiv3);
        } else {
            elementDisplayed = isElementDisplayed(driver, showMoreButtonDiv2);
        }
        return elementDisplayed;
    }

    public Integer countAllNotificationsOnPage() {
        int notificationCount = 0;
        notificationCount = getAllActiveNotifications() + getAllInactiveNotifications();
        return notificationCount;
    }

    public void clickShowMoreButton() {
        if (thereAreActiveNotifications() && thereAreInactiveNotifications()) {
            clickElement(showMoreButtonDiv3);
        } else {
            clickElement(showMoreButtonDiv2);
        }
    }

    public void clickNotificationsButton() {
        clickElement(notificationsButton);
    }

    public void clickAuthorsButton() {
        clickElement(authorsButton);
    }

    public void clickLinkToNotificationsPage() {
        clickElement(notificationPageLink);
    }

    public void deleteActiveNotificationOnPage() {
        clickElement(firstActiveNotification.findElement(By.tagName("button")));
    }

    public void deleteNotificationInDrawer() {
        clickElement(firstNotificationInDrawer.findElement(By.tagName("button")));
    }

    public Integer getActiveNotificationsFromCounter() {
        int i;
        if (thereAreActiveNotifications()) {
            i = Integer.parseInt(activeNotificationsCounter.getText());
        } else {
            i = 0;
        }
        return i;
    }

    public void clickOutsideDrawer(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveByOffset(xOffset, yOffset).click().perform();
    }

    public boolean notificationsHeaderIsDisplayed() {
        return isElementDisplayed(driver, notificationsHeader);
    }

    public boolean notificationIsInactive(String timestamp) {
        List<WebElement> allInactiveNotificationsByTimestamp = inactiveNotifications.findElements(By.tagName("p"));
        boolean isInactive;
        int i = 0;
        for (WebElement notification : allInactiveNotificationsByTimestamp) {
            if (notification.getText().equals(timestamp)) {
                i = 1;
            }
        }
        if (i == 1) {
            isInactive = true;
        } else {
            isInactive = false;
        }
        return isInactive;
    }

}
