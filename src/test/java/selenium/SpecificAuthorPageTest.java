package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.AuthorsPage;
import selenium.pages.authors.*;
import selenium.pages.SpecificAuthorPage;

import java.util.List;

public class SpecificAuthorPageTest extends TestBase {

    private AuthorsPage authorsPage;
    private List<String> avatarsLinks;
    private List<String> authorsNames;

    @BeforeClass
    public void beforeClass() {
        authorsPage = new AuthorsPage(driver);
        authorsPage.goTo();
        avatarsLinks = authorsPage.getAllAuthorsAvatarsLinks();
        authorsNames = authorsPage.getAllAuthorsNames();
    }

    @DataProvider(name = "author")
    public Object[][] authorProvider() {
        return new Object[][]{
                {new MartaAuthorPage(driver), 0},
                {new HannaAuthorPage(driver), 1},
                {new KamilAuthorPage(driver), 2},
                {new JakubAuthorPage(driver), 3},
                {new KarolAuthorPage(driver), 4}};
    }

    @Test(dataProvider = "author")
    public void showCaseTest(SpecificAuthorPage currentAuthorPage, int index) {
        currentAuthorPage.goTo();
        Assert.assertEquals(currentAuthorPage.getAuthorName().charAt(0), authorsNames.get(index).charAt(0));
        Assert.assertEquals(currentAuthorPage.getAvatarURL(), avatarsLinks.get(index));
        Assert.assertTrue(currentAuthorPage.isImageValid());
        Assert.assertTrue(currentAuthorPage.isGitHubLinkValid());
    }
}
