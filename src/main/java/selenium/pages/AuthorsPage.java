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
	
	@FindBy(css = "div.MuiCardHeader-content > span.MuiTypography-root.MuiCardHeader-subheader.MuiTypography-body2.MuiTypography-colorTextSecondary.MuiTypography-displayBlock")
	public List<WebElement> authorsNames;

	public AuthorsPage(WebDriver driver) {
		super(driver);
	}

	public void goTo() {
		goTo("/authors");
	}

	public String getHeaderText() {
		return getElementAttribute(header, "innerHTML");
	}
	
	public List<String> getAuthorsNames() {
		return authorsNames.stream().map(x -> x.getText()).collect(Collectors.toList());
	}
}

