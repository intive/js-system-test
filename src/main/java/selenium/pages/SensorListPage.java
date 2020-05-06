package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;
import java.util.stream.Collectors;

public class SensorListPage extends TestCommons {

    @FindBy(css = "#root > div > div:nth-child(2) > div > div:nth-child(2)")
    private WebElement sensorList;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div:nth-child(2) > ul > li:first-child")
    private List<WebElement> sensorListSections;

    @FindBy(css = "div.MuiButtonBase-root.MuiListItem-root.jss263.MuiListItem-gutters.MuiListItem-button")
    private List<WebElement> listOfAllSensors;

    public SensorListPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public boolean isListScrollable() {
        return sensorList.getCssValue("overflow").equals("auto");
    }

    public List<String> getListSectionNames() {
        return sensorListSections.stream().map(x -> x.getText()).collect(Collectors.toList());
    }

    public List<WebElement> getListOfAllSensors() {
        return listOfAllSensors;
    }

    public String getSensorsType(WebElement element) {
        return element.findElement(By.tagName("p")).getText();
    }

    public String getSensorsColor(WebElement element) {
        return element.getCssValue("border-color");
    }

    public String getSensorsValue(WebElement element) {
        return element.findElement(By.className("MuiListItemSecondaryAction-root")).getText();
    }
}
