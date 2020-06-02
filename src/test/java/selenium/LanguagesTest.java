package selenium;

import org.testng.asserts.SoftAssert;
import selenium.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.pages.LanguageDashboardPage;
import java.io.IOException;

public class LanguagesTest extends TestBase {
    private LanguageDashboardPage languagePage;

    @BeforeClass
    public void startPage() {
        languagePage = new LanguageDashboardPage(driver);
        languagePage.removeAllSensorsFromMap();
        languagePage.goTo();
    }

    @Test(priority = 0)
    public void languageLabelsAndSnackbarsTest () throws IOException, InterruptedException {
        SoftAssert total_Assertion = new SoftAssert();
        languagePage.mapExists();
        languagePage.languageLabelExists();
        languagePage.verifyPlTranslation(total_Assertion);
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsPl(total_Assertion);
        languagePage.switchLanguage("en");
        languagePage.verifyEnTranslation(total_Assertion);
        languagePage.verifySnackbarsEn(total_Assertion);
        languagePage.switchLanguage("pl");
        languagePage.verifySnackbarsPlAfterLanguageChange(total_Assertion);
        languagePage.internetConnection(true);
        languagePage.clickElement(languagePage.sensor1);
        languagePage.clickToAddPoint(-50, -75);
        languagePage.clickElement(languagePage.sensor2);
        languagePage.clickToAddPoint(-70, -95);
        languagePage.verifyProximityWarningPl();
        languagePage.clickElement(languagePage.sensorProximityWarningExitButton);
        languagePage.switchLanguage("en");
        languagePage.clickElement(languagePage.sensor3);
        languagePage.clickToAddPoint(90, 100);
        languagePage.clickElement(languagePage.sensor4);
        languagePage.clickToAddPoint(110, 120);
        languagePage.verifyProximityWarningEn();
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.internetConnection(false);
        languagePage.switchLanguage("pl");
        String loadMapSnackBar = languagePage.getElementTranslations(languagePage.loadMapSnackBar);
        String refreshButton = languagePage.getElementTranslations(languagePage.refreshButton);
        languagePage.verifyLoadMapPl(total_Assertion, loadMapSnackBar, refreshButton);
        languagePage.switchLanguage("en");
        loadMapSnackBar = languagePage.getElementTranslations(languagePage.loadMapSnackBar);
        refreshButton = languagePage.getElementTranslations(languagePage.refreshButton);
        languagePage.verifyLoadMapEn(total_Assertion, loadMapSnackBar, refreshButton);
        languagePage.internetConnection(true);
        languagePage.switchLanguage("pl");
        total_Assertion.assertAll();
    }

    @Test(priority = 1)
    public void languageChoiceMemory () throws IOException, InterruptedException {
        SoftAssert total_Assertion = new SoftAssert();
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.mapExists();
//        Thread.sleep(1000);
        languagePage.verifyChosenLanguagePl();
        languagePage.verifyPlTranslation(total_Assertion);
        languagePage.switchLanguage("en");
        languagePage.verifyEnTranslation(total_Assertion);
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn(total_Assertion);
        languagePage.internetConnection(true);
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.verifyChosenLanguageEn();
        languagePage.verifyEnTranslation(total_Assertion);
        languagePage.internetConnection(true);
        languagePage.switchLanguage("pl");
        total_Assertion.assertAll();
    }
}
