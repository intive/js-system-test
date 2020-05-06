package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.TestCommons;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorsPage extends TestCommons {

    @FindBy(css = "#root > div > div > header > div > div:nth-child(2) > div > div > a:nth-child(3) > span")
    private WebElement header;

    @FindBy(css = "#root > div > div:nth-child(2) > div > div > div")
    private List<WebElement> authorsDivs;

    @FindBy(className = "MuiAvatar-img")
    private List<WebElement> authorsAvatars;

    public AuthorsPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/authors");
    }

    public String getHeaderText() {
        return getElementAttribute(header, "innerHTML");
    }

    public List<String> getAllAuthorsNames() {
        return authorsDivs.stream().map(x -> x.getText()).collect(Collectors.toList());
    }

    public List<WebElement> getAuthorsAvatars() {
        return authorsAvatars;
    }

    public List<String> getAllAuthorsAvatarsLinks() {
        return authorsAvatars.stream().map(x -> x.getAttribute("src")).collect(Collectors.toList());
    }

    public String getAuthorName(int id) {
        return authorsDivs.get(id - 1).getText();
    }

    public void clickOnAuthorItem(int id) {
        clickElement(authorsDivs.get(id - 1));
    }

    public int getAuthorIdFromURL() {
        String url = driver.getCurrentUrl();
        char lastURLCharacter = url.charAt(url.length() - 1);
        return Character.getNumericValue(lastURLCharacter);
    }

    public Boolean isAvatarValidImage(WebElement avatarLink) {
        return isImageValid(avatarLink);
    }
}
