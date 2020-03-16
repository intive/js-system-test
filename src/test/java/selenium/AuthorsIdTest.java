package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.AuthorsIdPage;

public class AuthorsIdTest extends TestBase {

    private AuthorsIdPage authorsIdPage;

    @BeforeClass
    public void beforeMethod() {
        authorsIdPage = new AuthorsIdPage(driver);
    }

    @DataProvider(name = "wrongIds")
    public static Object[] wrongIdProvider() {
        return new Object[]{0, 6, "tekst"};
    }

    @Test(dataProvider = "wrongIds")
    public void testWrongId(Object id) {
        authorsIdPage.goTo(id);
        String expectedMessage = "404 - Autor się rozmyślił...";
        String actualMessage = authorsIdPage.getErrorMessageText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
