package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class HannaAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div > div:nth-child(2) > p")
    private WebElement name;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(5) > div:nth-child(2) > a")
    private WebElement github;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div > div > img")
    private WebElement avatar;

    public HannaAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/2");
    }
}