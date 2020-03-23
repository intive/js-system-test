package selenium.pages.authors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.SpecificAuthorPage;

public class KarolAuthorPage extends SpecificAuthorPage {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div[2]/h3")
    public WebElement name;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div/a[1]")
    public WebElement github;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div[1]/a/div/img")
    public WebElement avatar;

    public KarolAuthorPage(WebDriver driver) {
        super(driver);
        setUp(name, github, avatar);
    }

    public void goTo() {
        goTo("/authors/5");
    }
}
