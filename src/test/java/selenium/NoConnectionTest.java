package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.NoConnectionPage;
import java.io.IOException;

public class NoConnectionTest extends TestBase {
    private NoConnectionPage noConnectionPage;

    @BeforeClass
    public void startPage() {
        noConnectionPage = new NoConnectionPage(driver);
    }

    @Test
    public void testNoInternetMessage() throws IOException {
        noConnectionPage.goTo();
        noConnectionPage.isMapLoaded();
        noConnectionPage.internetConnection(false);
        WebElement healthCheckSnackBar;
        WebElement snackBarApi;
        WebElement healthCheckSnackBarExitButton;
        try {
            Assert.assertEquals(noConnectionPage.snackBar.getText(), "Hej, coś nie styka! Sprawdź połączenie.");
            healthCheckSnackBar = noConnectionPage.snackBar;
            healthCheckSnackBarExitButton = noConnectionPage.exitButton;
            snackBarApi = noConnectionPage.snackBar2;
        } catch (AssertionError e) {
            try {
                Assert.assertEquals(noConnectionPage.snackBar2.getText(), "Hej, coś nie styka! Sprawdź połączenie.");
                healthCheckSnackBar = noConnectionPage.snackBar2;
                healthCheckSnackBarExitButton = noConnectionPage.exitButton2;
                snackBarApi = noConnectionPage.snackBar;
            } catch (AssertionError e2){
                throw e2;
            }
        }
        noConnectionPage.snackBarExist(snackBarApi,10);
        boolean snackBarExistenceConfirmation = noConnectionPage.snackBarExist(healthCheckSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Nie wykryto prawidłowego snackbaru.");
        noConnectionPage.clickElement(healthCheckSnackBarExitButton);
        snackBarExistenceConfirmation = noConnectionPage.snackBarDoesntExist(healthCheckSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar nie zniknął.");
        snackBarExistenceConfirmation = noConnectionPage.snackBarExist(healthCheckSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Snackbar nie pojawił się ponownie.");
        noConnectionPage.internetConnection(true);
        snackBarExistenceConfirmation = noConnectionPage.snackBarDoesntExist(healthCheckSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar nie zniknął po wznowieniu połączenia internetowego.");
    }
}
