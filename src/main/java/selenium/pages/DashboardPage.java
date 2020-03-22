package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardPage extends TestCommons {

    @FindBy(tagName = "div")
    public List<WebElement> allDivElements;

    @FindBy(tagName = "h5")
    public WebElement appName;

    @FindBy(className = "MuiTab-wrapper")
    public List<WebElement> sectionName;

    @FindBy(tagName = "button")
    public WebElement notificationsButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public int getNumberOfSections() {
        int count = 0;
        for(WebElement element:allDivElements){
            String className = element.getAttribute("class");
            if (className.contains("MuiGrid-item")){
                count++;
            }
        }
        return count;

    }

    public List<String> getSectionNames() {
        return sectionName.stream().map(x -> x.getText().toLowerCase()).collect(Collectors.toList());
    }

    public String getApplicationName() {
        return getElementAttribute(appName, "innerHTML");
    }

    public String getNotificationsLabel() {
        return getElementAttribute(notificationsButton, "aria-label");
    }

    public void clickDashboard() {
        sectionName.get(0).click();
    }

    public void clickHvac() {
        sectionName.get(1).click();
    }

    public void clickAuthors() {
        sectionName.get(2).click();
    }

    public void clickApplicationName() {
        appName.click();
    }

    public void clickNotifications() {
        notificationsButton.click();
    }


}







