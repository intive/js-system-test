package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.AuthorsPage;

import java.util.Arrays;
import java.util.List;

public class AuthorsTest extends TestBase {

    private AuthorsPage authorsPage;

    @BeforeClass
    public void beforeClass() {
        authorsPage = new AuthorsPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(){
        authorsPage.goTo();
    }

    @DataProvider(name = "namesToIds")
    public Object[][] namesToIds() {
        return new Object[][]{{"Marta", 1}, {"Hania", 2}, {"Kamil", 3}, {"Jakub", 4}, {"Karol", 5}};
    }

    @Test
    public void testHeaderText() {
        String expectedHeader = "Autorzy";
        String actualHeader = authorsPage.getHeaderText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testAuthorsNames() {
        List<String> expectedNames = Arrays.asList("Marta", "Hania", "Kamil", "Jakub", "Karol");
        List<String> actualNames = authorsPage.getAllAuthorsNames();

        Assert.assertEquals(actualNames, expectedNames);
    }

    @Test(dataProvider = "namesToIds")
    public void testAuthorsItemLinks(String name, Integer id) {
        String authorName = authorsPage.getAuthorName(id);
        authorsPage.clickOnAuthorItem(id);
        Integer idFromURL = authorsPage.getAuthorIdFromURL();

        Assert.assertEquals(name, authorName);
        Assert.assertEquals(id, idFromURL);
    }
}