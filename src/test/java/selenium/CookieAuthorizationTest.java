package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
        SoftAssert softAssert = new SoftAssert();
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            softAssert.assertEquals(actualErrorText, expectedErrorText);
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "cookieNameAndValueProvider")
    public static Object[][] cookieNameAndValueProvider() {
        return new Object[][]{
                {"incorrect_name", "incorrect_value"},
                {"SuperToken", "incorrect_value"},
                {"incorrect_name", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2"},
                {"incorrect_name", ""},
                {"SuperToken", ""}};
    }

    @Test(dataProvider = "cookieNameAndValueProvider")
    public void enterWebsiteWithWrongCookieTest(String cookieName, String cookieValue) {
        cookieAuthorizationPage.deleteAllCookies();
        cookieAuthorizationPage.addNewCookie(cookieName, cookieValue);
        SoftAssert softAssert = new SoftAssert();
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            softAssert.assertEquals(actualErrorText, expectedErrorText);
        }
        softAssert.assertAll();
    }

    @Test
    public void enterWebsiteWithCorrectCookieTest() {
        cookieAuthorizationPage.deleteAllCookies();
        cookieAuthorizationPage.addNewCookie("SuperToken", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2");

        SoftAssert softAssert = new SoftAssert();
        for (String path : pathList) {
            cookieAuthorizationPage.goTo(path);
            String actualErrorText = cookieAuthorizationPage.getBodyText();

            softAssert.assertNotEquals(actualErrorText, expectedErrorText);
        }
        softAssert.assertAll();
    }

    @Test
    public void deleteCookiesWhenOnWebsiteTest() {
        cookieAuthorizationPage.addNewCookie("SuperToken", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2");
        cookieAuthorizationPage.goTo("/");
        cookieAuthorizationPage.deleteAllCookies();

        Assert.assertTrue(cookieAuthorizationPage.isSnackbarDisplayed());
    }

    @Test
    public void deleteCookiesThenAddCorrectWhenOnWebsite() {
        cookieAuthorizationPage.goTo("/");
        cookieAuthorizationPage.deleteAllCookies();
        Assert.assertTrue(cookieAuthorizationPage.isSnackbarDisplayed());

        cookieAuthorizationPage.addNewCookie("SuperToken", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2");
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
