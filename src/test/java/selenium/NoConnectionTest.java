package selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement theRightSnackBar = null;
        WebElement theIncorrectSnackBar = null;
        WebElement theRightExitButton = null;

        if (noConnectionPage.snackBar.getText().contains("Hej, coś nie styka! Sprawdź połączenie.")) {
            theRightSnackBar = noConnectionPage.snackBar;
            theRightExitButton = noConnectionPage.exitButton;
            theIncorrectSnackBar = noConnectionPage.snackBar2;
        } else if (noConnectionPage.snackBar2.getText().contains("Hej, coś nie styka! Sprawdź połączenie.")) {
            theRightSnackBar = noConnectionPage.snackBar2;
            theRightExitButton = noConnectionPage.exitButton2;
            theIncorrectSnackBar = noConnectionPage.snackBar;
        } else {
            Assert.assertEquals(1, 2);
        }

//        Alternative approach, for future use.
//        try {
//            Assert.assertEquals(noConnectionPage.snackBar.getText(), "Hej, coś nie styka! Sprawdź połączenie.");
//            theRightSnackBar = noConnectionPage.snackBar;
//            theRightExitButton = noConnectionPage.exitButton;
//        } catch (AssertionError e) {
//            try {
//                Assert.assertEquals(noConnectionPage.snackBar2.getText(), "Hej, coś nie styka! Sprawdź połączenie.", "Brak snackbaru o prawidłowej nazwie.");
//                theRightSnackBar = noConnectionPage.snackBar2;
//                theRightExitButton = noConnectionPage.exitButton2;
//            }
//            catch (AssertionError e2){
//                Assert.fail();
//            }
//        }
        //Poniższa linijka sprawdza, czy drugi snackBar znajduje się już na stronie.
        //Dzięki temu w teście bazuemy już na stabilnej stronie z widocznymi elementami.
        noConnectionPage.snackBarExist(theIncorrectSnackBar,10);

        //Czekaj aż pojawi się snackbar "Hej, ..." i rozpocznij testy
        boolean snackBarExistenceConfirmation = noConnectionPage.snackBarExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Nie wykryto prawidłowego snackbaru.");
        noConnectionPage.clickElement(theRightExitButton);
        snackBarExistenceConfirmation = noConnectionPage.snackBarDoesntExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, false, "Snackbar nie zniknął.");
        snackBarExistenceConfirmation = noConnectionPage.snackBarExist(theRightSnackBar, 10);
        Assert.assertEquals(snackBarExistenceConfirmation, true, "Snackbar nie pojawił się ponownie.");
    }
}
