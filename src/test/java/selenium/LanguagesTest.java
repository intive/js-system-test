package selenium;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.pages.LanguageDashboardPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LanguagesTest extends TestBase {
    private LanguageDashboardPage languagePage;

    @BeforeClass
    public void startPage() {
        languagePage = new LanguageDashboardPage(driver);
        languagePage.goTo();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test(priority = 0)
    public void languageLabelsAndSnackbarsTest () throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.visibilityOf(languagePage.homePlan));
        languagePage.waitingForLanguageLabelVisibility();
        Thread.sleep(10000);
//        Sprawdzanie poprawności tłumaczenia labelek i komunikatów, także przy zmianie języka, gdy nie ma połączenai internetowego
//        languagePage.internetConnection(false);
        languagePage.verifyPlTranslation();
//        languagePage.internetConnection(false);
        languagePage.verifySnackbarsPl();
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.enLanguageLabel);
        languagePage.verifyEnTranslation();
        languagePage.verifySnackbarsEn();
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.plLanguageLabel);
        languagePage.verifySnackbarsPlAfterLanguageChange();
        languagePage.clickElement(languagePage.sensor1);
        languagePage.clickToAddPoint(-50, -75);
//Tu chcę zaimplementować metodę, która będzie sprawdzać treść snacka o nieudanym umijecowieniu czujnika. Obecnie tego nie robię, gdyż metoda powinna być zmieniona na bardziej generyczną
        languagePage.internetConnection(true);
        languagePage.goTo();
        languagePage.internetConnection(false);
//       Sprawdzenie komunikatu o nieudanym załadowaniu mapy Home oraz tłumaczenia przycisku odświeżania
//       Zmieniany jest też język, aby sprawdzić te 2 napisy w wersji angielskiej
        String loadMapSnackBar = languagePage.loadMapSnackBar.getText();
        String refreshButton = languagePage.refreshButton.getText();
        languagePage.verifyLoadMapPl(loadMapSnackBar, refreshButton);
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.enLanguageLabel);
        loadMapSnackBar = languagePage.loadMapSnackBar.getText();
        refreshButton = languagePage.refreshButton.getText();
        languagePage.verifyLoadMapEn(loadMapSnackBar, refreshButton);
//        powrot do stanu poczatkowego
        languagePage.internetConnection(true);
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.plLanguageLabel);
       }

    @Test(priority = 1)
    public void languageChoiceMemory () throws IOException, InterruptedException {
        languagePage.goTo();
        languagePage.waitingForLanguageLabelVisibility();
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.visibilityOf(languagePage.homePlan));
        Thread.sleep(1000);
//        Test sprawdza, czy strona zapamiętuje wybór użytkownika w kontekście wybranego wcześniej języka.
        Assert.assertEquals(languagePage.getElementTranslations(languagePage.languageLabel), "PL", "Przeglądarka nie jest odpalona w języku polskim.");
        languagePage.verifyPlTranslation();
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.enLanguageLabel);
        languagePage.verifyEnTranslation();
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn();
        languagePage.internetConnection(true);
        languagePage.goTo();
        languagePage.waitingForLanguageLabelVisibility();
        Assert.assertEquals(languagePage.getElementTranslations(languagePage.languageLabel), "EN", "Nie zapamiętano uprzednio wybranego języka");
        languagePage.verifyEnTranslation();
        //        powrot do stanu poczatkowego
        languagePage.internetConnection(true);
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.plLanguageLabel);
    }

    @Test(priority = 2)
    public void defaultLanguageTest() throws IOException {
//        Ten będzie działał na 100% dobrze jak tylko zaimplementuę metodę z wywoływaniem zestawu testów z parametrem dla języka.
        Assert.assertEquals(languagePage.getElementTranslations(languagePage.languageLabel), "EN", "Język angielski nie jest językiem domyślnym.");
        languagePage.verifyEnTranslation();
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn();
//        powrot do stanu poczatkowego
        languagePage.internetConnection(true);
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.plLanguageLabel);

    }
//        listOfSensorsNames = languagePage.getSensorStrings();
//        Assert.assertEquals(listOfSensorsNames, correctListTranslationPl, "Nieprawidłowości w tłumaczeniu tytułów list lub nazw i wartości czujników" );
//        listOfSensorsNames = languagePage.getSensorStrings();
//        Assert.assertEquals(listOfSensorsNames, correctListTranslationPl, "Nieprawidłowości w tłumaczeniu tytułów list lub nazw i wartości czujników" );
}
