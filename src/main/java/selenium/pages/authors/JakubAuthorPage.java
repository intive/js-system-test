package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class JakubAuthorPage extends SpecificAuthorPage {

    @FindBy(css = "#root > div > div:nth-child(2) > main > div:nth-child(2) > div > div:nth-child(2) > h1")
    private WebElement name;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[5]/footer/p/a")
    private WebElement github;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/div[2]/div/div[1]/img")
    private WebElement avatar;

    public JakubAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/4");
    }
}
