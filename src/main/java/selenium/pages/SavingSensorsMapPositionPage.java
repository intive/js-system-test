package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestCommons;

import java.util.List;

public class SavingSensorsMapPositionPage extends TestCommons {

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div > div")
    private WebElement mapImage;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div > div > div")
    private List<WebElement> allSensorsOnMapList;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div:nth-child(2) > ul > div")
    private List<WebElement> allSensorsOnListList;

    @FindBy(css = "#root > div > div:nth-child(3) > div")
    private List<WebElement> errorSnackbars;

    private WebElement sensorOnTheMap;

    private WebElement sensorOnList;

    private WebElement xButton;

    private int sensorId;
    private double xOffset;
    private double yOffset;
    private double xMapSize;
    private double yMapSize;

    public SavingSensorsMapPositionPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void goToApi() {
        goTo("/api/v1/dashboard");
    }

    public void setSensorId(int id) {
        this.sensorId = id;
        new WebDriverWait(driver, 30).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(mapImage));
        setSensorOnList();
    }

    public void setSensorOnList() {
        sensorOnList = allSensorsOnListList.stream().filter(x -> x.findElement(By.cssSelector("div > div > li")).getAttribute("id").equals("sensor" + sensorId)).findFirst().get();
    }

    public void setSensorOnTheMap() {
        clickOnSensorOnList();
        sensorOnTheMap = allSensorsOnMapList.stream().filter(x -> !x.getCssValue("box-shadow").equals("none")).findFirst().orElse(null);
    }

    public WebElement getSensorOnTheMap() {
        setSensorOnList();
        setSensorOnTheMap();
        return sensorOnTheMap;
    }

    public void clickOnSensorOnList() {
        clickElement(sensorOnList);
    }

    public void clickOnMap(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        Actions builder = new Actions(driver);
        builder.moveToElement(mapImage, xOffset, yOffset).click().perform();
    }

    public void addSensorOnMap(int xOffset, int yOffset) {
        clickOnSensorOnList();
        clickOnMap(xOffset, yOffset);
    }

    public boolean hasSensorOnListXButton() {
        xButton = sensorOnList.findElement(By.tagName("button"));
        return xButton.isDisplayed();
    }

    public double[] getSensorPositionOnTheMap() {
        setSensorOnList();
        setSensorOnTheMap();

        xMapSize = mapImage.getSize().getWidth();
        yMapSize = mapImage.getSize().getHeight();

        double sensorXPosition = convertPixelCssValueToDouble(sensorOnTheMap.getCssValue("left"));
        double sensorYPosition = convertPixelCssValueToDouble(sensorOnTheMap.getCssValue("top"));

        double sensorWidth = convertPixelCssValueToDouble(sensorOnTheMap.getCssValue("width"));
        double sensorHeight = convertPixelCssValueToDouble(sensorOnTheMap.getCssValue("height"));

        double x = sensorXPosition + sensorWidth / 2;
        double y = sensorYPosition + sensorHeight / 2;

        double xPercent = x / xMapSize * 100;
        double yPercent = y / yMapSize * 100;

        return new double[]{xPercent, yPercent};
    }

    public double[] getClickedPositionOnTheMap(int xOffset, int yOffset) {
        xMapSize = mapImage.getSize().getWidth();
        yMapSize = mapImage.getSize().getHeight();

        double x = xMapSize / 2 + xOffset;
        double y = yMapSize / 2 + yOffset;

        double xPercent = x / xMapSize * 100;
        double yPercent = y / yMapSize * 100;

        return new double[]{xPercent, yPercent};
    }

    public double[] getSensorPositionFromAPI(int id) {
        String apiJson = driver.findElement(By.tagName("body")).getText();

        int start = apiJson.indexOf("\"id\":" + id);
        int end = apiJson.indexOf("\"id\"", start + 1);

        String sensorJson = apiJson.substring(start, end);

        int xInString = sensorJson.indexOf("\"x\"");
        int yInString = sensorJson.indexOf("\"y\"");

        if (xInString == -1 && yInString == -1) {
            return null;
        }

        int endOfX = sensorJson.indexOf(",", xInString);
        int endOfY = sensorJson.indexOf("}", yInString);

        String x = sensorJson.substring(xInString + 4, endOfX);
        String y = sensorJson.substring(yInString + 4, endOfY);

        return new double[]{Double.parseDouble(x), Double.parseDouble(y)};
    }

    public String getErrorSnackbarText() {
        return errorSnackbars.stream().map(x -> x.getText()).filter(x -> x.contains("Czujnik")).findFirst().orElse("Snackbar NotFound");
    }
}
