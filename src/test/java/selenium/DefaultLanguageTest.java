package selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import selenium.base.TestBase;
import selenium.pages.LanguageDashboardPage;
import java.io.IOException;

public class DefaultLanguageTest extends TestBase{
    private LanguageDashboardPage languagePage;

    @BeforeClass
    public void startPage() {
        languagePage = new LanguageDashboardPage(driver);
        languagePage.goTo();
    }
    @Test
    public void defaultLanguageTest() throws IOException {
        SoftAssert total_Assertion = new SoftAssert();
        languagePage.verifyChosenLanguageEn();
        languagePage.verifyEnTranslation(total_Assertion);
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn(total_Assertion);
//        powrot do stanu poczatkowego
        languagePage.internetConnection(true);
        languagePage.switchLanguage("pl");
    }
}
