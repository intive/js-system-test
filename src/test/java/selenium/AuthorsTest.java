package selenium;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import selenium.base.TestBase;
import selenium.pages.AuthorsPage;

public class AuthorsTest extends TestBase {

	private AuthorsPage authorsPage;
	
	@BeforeClass
	public void before() {
		authorsPage = new AuthorsPage(driver);
		authorsPage.goTo();
	}

	@Test
	public void testHeaderText() {
		String expectedHeader = "Authors";
		String actualHeader = authorsPage.getHeaderText();

		Assert.assertEquals(actualHeader, expectedHeader);
	}
	
	@Test
	public void testAuthorsNames() {
		List<String> expectedNames = Arrays.asList("Marta", "Hania", "Kamil", "Jakub", "Karol");
		List<String> actualNames = authorsPage.getAuthorsNames();
		
		Assert.assertEquals(actualNames, expectedNames);
	}
}