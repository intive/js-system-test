package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.LanguageDashboardPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DefaultLanguageTest extends TestBase{
    private LanguageDashboardPage languagePage;

    @BeforeClass
    public void startPage() {
        languagePage = new LanguageDashboardPage(driver);
        languagePage.goTo();
    }
    @Test
    public void defaultLanguageTest() throws IOException {
        Assert.assertEquals(languagePage.getElementTranslations(languagePage.languageLabel), "EN", "Język angielski nie jest językiem domyślnym.");
        languagePage.verifyEnTranslation();
        languagePage.internetConnection(false);
        languagePage.verifySnackbarsEn();
//        powrot do stanu poczatkowego
        languagePage.internetConnection(true);
        languagePage.clickElement(languagePage.languageLabel);
        languagePage.clickElement(languagePage.plLanguageLabel);
    }
}
