package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import selenium.base.TestCommons;
import java.util.ArrayList;
import java.util.List;

public class LanguageDashboardPage extends TestCommons {


    @FindBy(xpath = "/html/body/div/div/div[2]/div/h2/div")
    public WebElement loadMapSnackBar;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/button")
    public WebElement refreshButton;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[1]/div/div[1]/div")
    public WebElement sensor1;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[2]/div/div[1]/div")
    public WebElement sensor2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[3]/div/div[1]/div")
    public WebElement sensor3;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[4]/div/div[1]/div")
    public WebElement sensor4;
    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div[3]/button")
    public WebElement sensorProximityWarningExitButton;
    @FindBy(xpath = "/html/body/div[2]/div[3]/div")
    private WebElement sensorProximityWarning;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[2]/div/div/div")
    private WebElement languageLabel;
    @FindBy(css = "#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(1)")
    private WebElement enLanguageLabel;
    @FindBy(xpath = "/html/body/div[2]/div[3]/ul/li[2]")
    private WebElement plLanguageLabel;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    private WebElement homePlan;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/header/div/h6")
    private WebElement title;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[1]/span[1]")
    private WebElement mainPanelTile;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[2]/span[1]")
    private WebElement hvacTile;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[3]/span[1]")
    private WebElement authorsTile;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div")
    private WebElement snackBar;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div")
    private WebElement snackBar2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]")
    private WebElement inactiveList;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]")
    private WebElement activeList;
//    --------------------
    @Override
    public void clickElement(WebElement element) {
        super.clickElement(element);
    }
//    --------------------
    public LanguageDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void verifyChosenLanguagePl (){
        Assert.assertEquals(getElementTranslations(languageLabel), "PL", "Przeglądarka nie jest odpalona w języku polskim.");
    }

    public void verifyChosenLanguageEn(){
        Assert.assertEquals(getElementTranslations(languageLabel), "EN", "Język angielski nie jest językiem domyślnym.");
    }

    public String getElementTranslations(WebElement element) {
        return element.getText();
    }

    public void languageLabelExists() {
        isElementDisplayed(driver, languageLabel, 5);
    }

    public void mapExists() {
        isElementDisplayed(driver, homePlan, 5);
    }

    public boolean snackBarExists(WebElement webElement, int time) {
        return isElementDisplayed(driver, webElement, time);
    }

    public void clickToAddPoint(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }

    public void switchLanguage(String language){
        if(language.toLowerCase().equals("en")){
        clickElement(languageLabel);
         clickElement(enLanguageLabel);}
        else if(language.toLowerCase().equals("pl")){
        clickElement(languageLabel);
        clickElement(plLanguageLabel);}
    }

    public void verifyPlTranslation(SoftAssert appendTotalAssert) {
        appendTotalAssert.assertEquals(getElementTranslations(languageLabel), "PL", "Domyślny język nie jest zgodny z językiem przeglądarki (polski)");
        appendTotalAssert.assertEquals(getElementTranslations(title), "Smart Home", "Niepoprawne tłumaczenie tytułu strony");
        appendTotalAssert.assertEquals(getElementTranslations(mainPanelTile), "PANEL GŁÓWNY", "Niepoprawne tłumaczenie zakładki DASHBOARD");
        appendTotalAssert.assertEquals(getElementTranslations(hvacTile), "HVAC", "Niepoprawne tłumaczenie zakładki HVAC");
        appendTotalAssert.assertEquals(getElementTranslations(authorsTile), "AUTORZY", "Niepoprawne tłumaczenie zakładki AUTHORS");
        appendTotalAssert.assertEquals(getElementTranslations(title), "Smart Home", "Nazwa aplikacji nie powinna być tłumaczona.");
    }

    public void verifyEnTranslation(SoftAssert appendTotalAssert) {
        appendTotalAssert.assertEquals(getElementTranslations(languageLabel), "EN", "Domyslny język nie jest językiem angielskim");
        appendTotalAssert.assertEquals(getElementTranslations(title), "Smart Home", "Niepoprawne tłumaczenie tytułu strony");
        appendTotalAssert.assertEquals(getElementTranslations(mainPanelTile), "DASHBOARD", "Niepoprawne tłumaczenie zakładki Panel główny");
        appendTotalAssert.assertEquals(getElementTranslations(hvacTile), "HVAC", "Niepoprawne tłumaczenie zakładki HVAC");
        appendTotalAssert.assertEquals(getElementTranslations(authorsTile), "AUTHORS", "Niepoprawne tłumaczenie zakładki Autorzy");
        appendTotalAssert.assertEquals(getElementTranslations(title), "Smart Home", "Nazwa aplikacji nie powinna być tłumaczona.");
    }

    public void verifySnackbarsPl(SoftAssert appendTotalAssert) {
        snackBarExists(snackBar, 10);
        snackBarExists(snackBar2, 10);
        appendTotalAssert.assertEquals(getElementTranslations(snackBar2), "Odświeżenie stanu czujników nie powiodło się.");
        appendTotalAssert.assertEquals(getElementTranslations(snackBar), "Hej, coś nie styka! Sprawdź połączenie.");
    }

    public void verifySnackbarsPlAfterLanguageChange(SoftAssert appendTotalAssert) {
        snackBarExists(snackBar, 10);
        snackBarExists(snackBar2, 10);
        appendTotalAssert.assertEquals(getElementTranslations(snackBar), "Odświeżenie stanu czujników nie powiodło się.");
        appendTotalAssert.assertEquals(getElementTranslations(snackBar2), "Hej, coś nie styka! Sprawdź połączenie.");
    }

    public void verifySnackbarsEn(SoftAssert appendTotalAssert) {
        snackBarExists(snackBar, 10);
        snackBarExists(snackBar2, 10);
        appendTotalAssert.assertEquals(getElementTranslations(snackBar2), "Could not refresh sensors' status.");
        appendTotalAssert.assertEquals(getElementTranslations(snackBar), "Hey, something isn't quite right! Check your connection.");
    }

    public void verifyLoadMapPl(SoftAssert appendTotalAssert, String loadMapSnackBar, String refreshButton) {
        appendTotalAssert.assertEquals(loadMapSnackBar, "Coś poszło nie tak....", "Nieprawidłowy komunikat niepowodzenia wczytania mapy Home PL.");
        appendTotalAssert.assertEquals(refreshButton, "ODŚWIEŻ", "Nieprawidłowa nazwa przycisku odświeżania PL.");
    }

    public void verifyLoadMapEn(SoftAssert appendTotalAssert, String loadMapSnackBar, String refreshButton) {
        appendTotalAssert.assertEquals(loadMapSnackBar, "Something went wrong....", "Nieprawidłowy komunikat niepowodzenia wczytania mapy Home EN.");
        appendTotalAssert.assertEquals(refreshButton, "REFRESH", "Nieprawidłowa nazwa przycisku odświeżania EN.");
    }

    public void verifyProximityWarningPl(){
        Assert.assertEquals(getElementTranslations(sensorProximityWarning), "Potrzebny odstęp!\n" + "Zostaw większy odstęp między sensorami.\n" + "ZAMKNIJ", "Niepoprawne ostrzeżenie o zbyt bliskim umiejscowieniu sensorów po polsku.");
    }

    public void verifyProximityWarningEn(){
        Assert.assertEquals(getElementTranslations(sensorProximityWarning), "Gap needed!\n" + "Leave a gap between sensors, please.\n" + "CLOSE", "Niepoprawne ostrzeżenie o zbyt bliskim umiejscowieniu sensorów po angielsku.");
    }
}
