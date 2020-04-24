package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.CookieAuthorizationPage;

public class CookieAuthorizationTest extends TestBase {

    private CookieAuthorizationPage cookieAuthorizationPage;
    private final String expectedErrorText = "{\"error\":\"Unauthorized Error\"}";
    private String[] pathList;

    @BeforeClass
    public void beforeClass() {
        cookieAuthorizationPage = new CookieAuthorizationPage(driver);
        pathList = new String[]{"/", "/dashboard", "/hvac", "/authors", "/authors/1", "/authors/5"};
    }

    @Test
    public void enterWebsiteWithNoCookieTest() {
        cookieAuthorizationPage.deleteAllCookies();
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            Assert.assertEquals(actualErrorText, expectedErrorText);
        }
    }

    @DataProvider(name = "cookieNameAndValueProvider")
    public static Object[][] cookieNameAndValueProvider() {
        return new Object[][]{
                {"incorrect_name", "incorrect_value"},
                {"secret_cookie", "incorrect_value"},
                {"incorrect_name", "3241231213fsdj23kj4kl32j4"},
                {"incorrect_name", ""},
                {"secret_cookie", ""}};
    }

    @Test(dataProvider = "cookieNameAndValueProvider")
    public void enterWebsiteWithWrongCookieTest(String cookieName, String cookieValue) {
        cookieAuthorizationPage.deleteAllCookies();
        cookieAuthorizationPage.addNewCookie(cookieName, cookieValue);
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            Assert.assertEquals(actualErrorText, expectedErrorText);
        }
    }

    @Test
    public void enterWebsiteWithCorrectCookieTest() {
        cookieAuthorizationPage.deleteAllCookies();
        cookieAuthorizationPage.addNewCookie("secret_cookie", "3241231213fsdj23kj4kl32j4");
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            Assert.assertNotEquals(actualErrorText, expectedErrorText);
        }
    }

    @Test
    public void deleteCookiesWhenOnWebsiteTest() {
        cookieAuthorizationPage.addNewCookie("secret_cookie", "3241231213fsdj23kj4kl32j4");
        cookieAuthorizationPage.goTo("/");
        cookieAuthorizationPage.deleteAllCookies();

        Assert.assertTrue(cookieAuthorizationPage.isSnackbarDisplayed());
    }

    @Test
    public void deleteCookiesThenAddCorrectWhenOnWebsite() {
        cookieAuthorizationPage.goTo("/");
        cookieAuthorizationPage.deleteAllCookies();
        Assert.assertTrue(cookieAuthorizationPage.isSnackbarDisplayed());

        cookieAuthorizationPage.addNewCookie("secret_cookie", "3241231213fsdj23kj4kl32j4");
        Assert.assertTrue(cookieAuthorizationPage.isSnackbarHidden());
    }

    @Test
    public void deleteCookiesThenAddWrongWhenOnWebsite() {
        cookieAuthorizationPage.goTo("/");
        cookieAuthorizationPage.deleteAllCookies();
        Assert.assertTrue(cookieAuthorizationPage.isSnackbarDisplayed());

        cookieAuthorizationPage.addNewCookie("incorrect_name", "incorrect_value");
        Assert.assertFalse(cookieAuthorizationPage.isSnackbarHidden());
    }
}
