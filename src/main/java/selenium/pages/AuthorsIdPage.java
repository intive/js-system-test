package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

public class AuthorsIdPage extends TestCommons {

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > main > h1")
    public WebElement errorMessage;

    public AuthorsIdPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(Object id) {
        String idString = id.toString();
        goTo("/authors/" + idString);
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }

}
