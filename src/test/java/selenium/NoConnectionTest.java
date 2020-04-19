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
        noConnectionPage.internetConnection(false);
        WebElement theRightSnackBar;
        WebElement theIncorrectSnackBar;
        WebElement theRightExitButton;

//        Alternative approach, for future use.
//        if (noConnectionPage.snackBar.getText().contains("Hej, coś nie styka! Sprawdź połączenie.")) {
//            theRightSnackBar = noConnectionPage.snackBar;
//            theRightExitButton = noConnectionPage.exitButton;
//            theIncorrectSnackBar = noConnectionPage.snackBar2;
//        } else if (noConnectionPage.snackBar2.getText().contains("Hej, coś nie styka! Sprawdź połączenie.")) {
//            theRightSnackBar = noConnectionPage.snackBar2;
//            theRightExitButton = noConnectionPage.exitButton2;
//            theIncorrectSnackBar = noConnectionPage.snackBar;
//        } else {
//            Assert.assertEquals(1, 2);
//        }


        try {
            Assert.assertEquals(noConnectionPage.snackBar.getText(), "Hej, coś nie styka! Sprawdź połączenie.");
            theRightSnackBar = noConnectionPage.snackBar;
            theRightExitButton = noConnectionPage.exitButton;
            theIncorrectSnackBar = noConnectionPage.snackBar2;
        } catch (AssertionError e) {
            try {
                Assert.assertEquals(noConnectionPage.snackBar2.getText(), "Hej, coś nie styka! Sprawdź połączenie.", "Brak snackbaru o prawidłowej nazwie.");
                theRightSnackBar = noConnectionPage.snackBar2;
                theRightExitButton = noConnectionPage.exitButton2;
                theIncorrectSnackBar = noConnectionPage.snackBar;
            }
            catch (AssertionError e2){
                theRightSnackBar = null;
                theIncorrectSnackBar = null;
                theRightExitButton = null;
                Assert.fail();
            }
        }
        noConnectionPage.snackBarExist(theIncorrectSnackBar,10);

        boolean snackBarExistenceConfirmation = noConnectionPage.snackBarExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Nie wykryto prawidłowego snackbaru.");
        noConnectionPage.clickElement(theRightExitButton);
        snackBarExistenceConfirmation = noConnectionPage.snackBarDoesntExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar nie zniknął.");
        snackBarExistenceConfirmation = noConnectionPage.snackBarExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Snackbar nie pojawił się ponownie.");

        noConnectionPage.internetConnection(true);
        snackBarExistenceConfirmation = noConnectionPage.snackBarDoesntExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar nie zniknął po wznowieniu połączenia internetowego.");
    }
}
