package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class KamilAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-10 > div > div > div:nth-child(1) > h1 > div")
    public WebElement name;

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-10 > div > div > div:nth-child(4) > div > div > a")
    public WebElement github;

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-10 > div > div > div.MuiGrid-root.jss533.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-lg-6 > div > img")
    public WebElement avatar;

    public KamilAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/3");
    }
}
