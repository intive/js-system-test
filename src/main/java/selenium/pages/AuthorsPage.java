package selenium.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.base.TestCommons;

public class AuthorsPage extends TestCommons {

    @FindBy(className = "jss12")
    public WebElement header;

    @FindBy(css = "div.MuiCardHeader-content > span.MuiTypography-root.MuiCardHeader-title.MuiTypography-body2.MuiTypography-displayBlock")
    public List<WebElement> authorsNames;

    @FindBy(css = "#root > ul > ul > li")
    public List<WebElement> authorsItemsList;

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
        return authorsNames.stream().map(x -> x.getText()).collect(Collectors.toList());
    }

    public String getAuthorName(int id){
    	return authorsNames.get(id-1).getText();
	}

    public void clickOnAuthorItem(int id) {
        clickElement(authorsNames.get(id-1));
    }

    public int getAuthorIdFromURL(){
    	String url = driver.getCurrentUrl();
		char lastURLCharacter = url.charAt(url.length() - 1);
    	return Character.getNumericValue(lastURLCharacter);
	}
}
