package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class HannaAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div.MuiGrid-root.jss190.MuiGrid-container.MuiGrid-direction-xs-column > div.MuiGrid-root.jss194.MuiGrid-container > div.MuiGrid-root.jss191.MuiGrid-container.MuiGrid-direction-xs-column > p.jss196")
    public WebElement name;

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div.MuiGrid-root.jss190.MuiGrid-container.MuiGrid-direction-xs-column > div.MuiBox-root.jss242 > div.MuiBox-root.jss260 > div > div.MuiGrid-root.jss262.MuiGrid-container.MuiGrid-spacing-xs-1 > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-6 > a")
    public WebElement github;

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > div > div.MuiGrid-root.jss190.MuiGrid-container.MuiGrid-direction-xs-column > div.MuiGrid-root.jss194.MuiGrid-container > div.MuiAvatar-root.MuiAvatar-circle.jss195 > img")
    public WebElement avatar;

    public HannaAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/2");
    }
}
