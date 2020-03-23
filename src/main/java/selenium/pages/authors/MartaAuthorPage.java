package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class MartaAuthorPage extends SpecificAuthorPage {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div[1]/h1")
    public WebElement name;

    @FindBy(css = "div.MuiGrid-root.MuiGrid-container.MuiGrid-align-items-xs-center.MuiGrid-justify-xs-center > div > div > div:nth-child(2) > div.MuiGrid-root.MuiGrid-container.MuiGrid-justify-xs-center > a")
    public WebElement github;

    @FindBy(css = "div.MuiGrid-root.MuiGrid-container.MuiGrid-align-items-xs-center.MuiGrid-justify-xs-center > div > div > div:nth-child(1) > div > img")
    public WebElement avatar;

    public MartaAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/1");
    }
}
