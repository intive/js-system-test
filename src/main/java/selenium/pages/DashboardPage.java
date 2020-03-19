package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;

public class DashboardPage extends TestCommons {

    @FindBy(tagName = "div")
    public List<WebElement> allDivElements;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public int getNumberOfSections() {
        int count = 0;
        for (WebElement element : allDivElements) {
            String className = element.getAttribute("class");
            if (className.contains("MuiGrid-item")) {
                count = count + 1;
            }
        }
        return count;

    }


}







