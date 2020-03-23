package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class JakubAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div.MuiGrid-root.jss2.MuiGrid-container > main > div.MuiPaper-root.MuiGrid-root.jss193.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-8.MuiGrid-grid-md-5.MuiPaper-elevation6 > div > div.MuiBox-root.jss229 > h1")
    public WebElement name;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[5]/footer/p/a")
    public WebElement github;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[1]/img")
    public WebElement avatar;

    public JakubAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/4");
    }
}
