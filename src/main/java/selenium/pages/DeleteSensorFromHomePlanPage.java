package selenium.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeleteSensorFromHomePlanPage extends TestCommons {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement firstNotConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div")
    private WebElement homePlanByDiv;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[1]")
    public WebElement firstConnectedSensor;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[2]/div[2]")
    public WebElement secondConnectedSensor;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/button[1]")
    public WebElement cancelButton;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/button[2]")
    public WebElement okButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/ul[1]")
    private WebElement notConnectedSensorsList;

    @FindBy(id = "client-snackbar")
    private List<WebElement> snackbars;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[3]")
    private WebElement snackbarBox;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/div[1]")
    public WebElement firstSensorOnHomePlan;

    public DeleteSensorFromHomePlanPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void clickSensorOnList(WebElement element) {
        clickElement(element);
    }

    public void clickOnHomePlan(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlanByDiv, xOffset, yOffset).click().perform();
    }

    public void clickSensorOnHomePlan() {
        firstSensorOnHomePlan.click();
    }

    public WebElement deleteButton(WebElement element) {
        return element.findElement(By.tagName("button"));
    }

    public String isDeleteButtonDisplayed(WebElement element) {
        return deleteButton(element).getCssValue("display");
    }

    public void clickButton(WebElement element) {
        clickElement(element);
    }

    public List<WebElement> pointsOnHomePlan() {
        List<WebElement> allPointsOnHomePlan = homePlanByDiv.findElements(By.tagName("div"));
        return allPointsOnHomePlan;
    }

    public int countPointsOnHomePlan() {
        return pointsOnHomePlan().size();
    }

    public String getSensorId() {
        return getElementAttribute(firstConnectedSensor.findElement(By.tagName("li")), "id");
    }

    public WebElement notConnectedSensorById(String idText) {
        return notConnectedSensorsList.findElement(By.id(idText));
    }

    public void waitForDeletedSensor(String idText) {
        isElementDisplayed(driver, notConnectedSensorById(idText));
    }

    public void refreshPage() {
        goTo();
    }

    public void setNetworkConnectionOffline(boolean condition) throws IOException {
        Map map = new HashMap();
        map.put("offline", condition);
        map.put("latency", 5);
        map.put("download_throughput", 500);
        map.put("upload_throughput", 1024);


        CommandExecutor executor = ((ChromeDriver) driver).getCommandExecutor();
        Response response = executor.execute(
                new Command(((ChromeDriver) driver).getSessionId(), "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map)))
        );
    }


    public List<String> getTextOnSnackbars() {
        List<String> textOnSnackbars = new LinkedList<>();
        for (WebElement element : snackbars) {
            textOnSnackbars.add(element.getText());
        }
        return textOnSnackbars;
    }

    public boolean isSnackbarForErrorDuringRemovingSensorDisplayed() {
        String expectedEn = "Error during removing sensor";
        String expectedPl = "Błąd przy usuwaniu czujnika";
        boolean value = false;
        for (String text : getTextOnSnackbars()) {
            if (text.contains(expectedEn) || text.contains(expectedPl)) {
                value = true;
            } else {
                value = false;
            }
        }
        return value;
    }

    public void waitForAnySnackbar() {
        isElementDisplayed(driver, snackbarBox);
    }

    public boolean isSnackbarNotDisplayed() throws InterruptedException {

        long endWaitTime = System.currentTimeMillis() + 10 * 1000;
        boolean isConditionMet = true;
        while (System.currentTimeMillis() < endWaitTime && isConditionMet) {
            isConditionMet = isSnackbarForErrorDuringRemovingSensorDisplayed();
            if (isConditionMet == false) {
                break;
            } else {
                Thread.sleep(100);
            }
        }
        return !isConditionMet;
    }

    public void clickOnCoordinates(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveByOffset(xOffset, yOffset).click().perform();
    }

    public void clickNotConnectedSensorOnList(WebElement element) {
        WebElement pointToClick = element.findElement(By.tagName("p"));
        scrollIntoView(element);
        clickElement(pointToClick);
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void addPointOnHomePlan(int x, int y) {
        clickNotConnectedSensorOnList(firstNotConnectedSensor);
        clickOnHomePlan(x, y);
        clickOnCoordinates(60, 180); // In case Gap Needed box is present, it will close the box
    }

    public void addPointsOnHomePlanWhenRequired() {
        int x = 0;
        int y = 0;
        while (pointsOnHomePlan().size() < 3) {
            addPointOnHomePlan(x, y);
            x = x + 30;
            y = y + 30;
        }
    }
}
