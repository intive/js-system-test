package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class KamilAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div:nth-child(2) > div > div > div > h1 > div")
    private WebElement name;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div:nth-child(2) > div > div > div:nth-child(4) > div > div > a")
    private WebElement github;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div:nth-child(2) > div > div > div:nth-child(2) > div > img")
    private WebElement avatar;

    public KamilAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/3");
    }
}
