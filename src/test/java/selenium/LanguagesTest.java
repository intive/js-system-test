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
    public void languageLabelsAndSnackbarsTest () throws IOException {
        SoftAssert softAssert = new SoftAssert();
        languagePage.mapExists();
        languagePage.languageLabelExists();
        languagePage.verifyPlTranslation(softAssert);
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsPl(softAssert);
        languagePage.switchLanguage("en");
        languagePage.verifyEnTranslation(softAssert);
        languagePage.verifySnackbarsEn(softAssert);
        languagePage.switchLanguage("pl");
        languagePage.verifySnackbarsPlAfterLanguageChange(softAssert);
        languagePage.internetConnection(true);
        languagePage.clickSensor1();
        languagePage.clickToAddPoint(-50, -75);
        languagePage.clickSensor2();
        languagePage.clickToAddPoint(-60, -85);
        languagePage.verifyProximityWarningPl();
        languagePage.clickSensorProximityWarningExitButton();
        languagePage.switchLanguage("en");
        languagePage.languageLabelExists();
        languagePage.clickSensor3();
        languagePage.clickToAddPoint(90, 100);
        languagePage.clickSensor4();
        languagePage.clickToAddPoint(100, 110);
        languagePage.verifyProximityWarningEn();
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.internetConnection(false);
        languagePage.switchLanguage("pl");
        languagePage.verifyLoadMapPl(softAssert);
        languagePage.switchLanguage("en");
        languagePage.verifyLoadMapEn(softAssert);
        languagePage.internetConnection(true);
        languagePage.switchLanguage("pl");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void languageChoiceMemory () throws IOException {
        SoftAssert softAssert = new SoftAssert();
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.mapExists();
        languagePage.verifyChosenLanguagePl();
        languagePage.verifyPlTranslation(softAssert);
        languagePage.switchLanguage("en");
        languagePage.verifyEnTranslation(softAssert);
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn(softAssert);
        languagePage.internetConnection(true);
        languagePage.goTo();
        languagePage.languageLabelExists();
        languagePage.verifyChosenLanguageEn();
        languagePage.verifyEnTranslation(softAssert);
        languagePage.internetConnection(true);
        languagePage.switchLanguage("pl");
        softAssert.assertAll();
    }
}
