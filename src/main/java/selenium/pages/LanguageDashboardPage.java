package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import selenium.base.TestCommons;
import java.util.ArrayList;
import java.util.List;

public class LanguageDashboardPage extends TestCommons {

    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[2]/div/div/div")
    public WebElement languageLabel;
    @FindBy(css = "#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(1)")
    public WebElement enLanguageLabel;
    @FindBy(xpath = "/html/body/div[2]/div[3]/ul/li[2]")
    public WebElement plLanguageLabel;
    @FindBy(xpath = "/html/body/div[1]/div/div[1]/header/div/h6")
    public WebElement title;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[1]/span[1]")
    public WebElement mainPanelTile;
    @FindBy(xpath =  "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[2]/span[1]")
    public WebElement hvacTile;
    @FindBy(xpath = "/html/body/div/div/div[1]/header/div/div[1]/div/div/a[3]/span[1]")
    public WebElement authorsTile;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div/div/div/div")
    public WebElement snackBar;
    @FindBy(xpath = "/html/body/div/div/div[3]/div[2]/div/div/div/div")
    public WebElement snackBar2;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]")
    public WebElement inactiveList;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]")
    public WebElement activeList;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/h2/div")
    public WebElement loadMapSnackBar;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/button")
    public WebElement refreshButton;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/div[1]")
    public WebElement sensor1;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/img")
    public WebElement homePlan;
    
//    @FindBy(xpath = "/html/body/div/div/div[3]/div[3]/div/div/div")
//    public WebElement sensorAddSnackbar;
//    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[1]/li")
//    public WebElement notPlacedSensorsTitle;
//    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/ul[2]/li")
//    public WebElement placedSensorsTitle;

    ArrayList<String> listOfSensorsTextsPl = new ArrayList<>();
    ArrayList<String> listOfSensorsTextsEn = new ArrayList<>();
//    ArrayList<String> listOfExpectedSensorsTextsPl = new ArrayList<>();

    public ArrayList<String> getSensorStringsPl(){
        listOfSensorsTextsPl.add(inactiveList.getText());
        listOfSensorsTextsPl.add(activeList.getText());
        return listOfSensorsTextsPl;
    }

    public List<String> getSensorStringsEn(){
        listOfSensorsTextsEn.add(inactiveList.getText());
        listOfSensorsTextsEn.add(activeList.getText());
        return listOfSensorsTextsEn;
    }

    public String getElementTranslations(WebElement element){
        return element.getText();
    }

    @Override
    public void clickElement(WebElement element) {
        super.clickElement(element);
    }

    public LanguageDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goTo() {
        goTo("/");
    }

    public void waitingForLanguageLabelVisibility()
    {
        isElementDisplayed(driver, languageLabel, 5);
    }

    public void verifyPlTranslation(){
        Assert.assertEquals(getElementTranslations(languageLabel), "PL", "Domyślny język nie jest zgodny z językiem przeglądarki (polski)");
        Assert.assertEquals(getElementTranslations(title), "Smart Home", "Niepoprawne tłumaczenie tytułu strony");
        Assert.assertEquals(getElementTranslations(mainPanelTile), "PANEL GŁÓWNY", "Niepoprawne tłumaczenie zakładki DASHBOARD");
        Assert.assertEquals(getElementTranslations(hvacTile), "HVAC", "Niepoprawne tłumaczenie zakładki HVAC");
        Assert.assertEquals(getElementTranslations(authorsTile), "AUTORZY", "Niepoprawne tłumaczenie zakładki AUTHORS");
    }

    public void verifyEnTranslation(){
        Assert.assertEquals(getElementTranslations(languageLabel), "EN", "Domyslny język nie jest językiem angielskim");
        Assert.assertEquals(getElementTranslations(title), "Smart Home", "Niepoprawne tłumaczenie tytułu strony");
        Assert.assertEquals(getElementTranslations(mainPanelTile), "DASHBOARD", "Niepoprawne tłumaczenie zakładki Panel główny");
        Assert.assertEquals(getElementTranslations(hvacTile), "HVAC", "Niepoprawne tłumaczenie zakładki HVAC");
        Assert.assertEquals(getElementTranslations(authorsTile), "AUTHORS", "Niepoprawne tłumaczenie zakładki Autorzy");
    }

    public void verifySnackbarsPl(){
        snackBarExist(snackBar, 10);
        snackBarExist(snackBar2, 10);
        Assert.assertEquals(getElementTranslations(snackBar2), "Odświeżenie stanu czujników nie powiodło się.");
        Assert.assertEquals(getElementTranslations(snackBar), "Hej, coś nie styka! Sprawdź połączenie.");
    }

    public void verifySnackbarsPlAfterLanguageChange(){
        snackBarExist(snackBar, 10);
        snackBarExist(snackBar2, 10);
        Assert.assertEquals(getElementTranslations(snackBar), "Odświeżenie stanu czujników nie powiodło się.");
        Assert.assertEquals(getElementTranslations(snackBar2), "Hej, coś nie styka! Sprawdź połączenie.");
    }

    public void verifySnackbarsEn(){
        snackBarExist(snackBar, 10);
        snackBarExist(snackBar2, 10);
        Assert.assertEquals(getElementTranslations(snackBar2), "Could not refresh sensors' status.");
        Assert.assertEquals(getElementTranslations(snackBar), "Hey, something isn't quite right! Check your connection.");
    }

    public void verifyLoadMapPl(String loadMapSnackBar,String refreshButton){
        Assert.assertEquals(loadMapSnackBar, "Coś poszło nie tak....", "Nieprawidłowy komunikat niepowodzenia wczytania mapy Home PL.");
        Assert.assertEquals(refreshButton, "ODŚWIEŻ", "Nieprawidłowa nazwa przycisku odświeżania PL.");
    }
    public void verifyLoadMapEn(String loadMapSnackBar,String refreshButton){
        Assert.assertEquals(loadMapSnackBar, "Something went wrong....", "Nieprawidłowy komunikat niepowodzenia wczytania mapy Home EN.");
        Assert.assertEquals(refreshButton, "REFRESH", "Nieprawidłowa nazwa przycisku odświeżania EN.");
    }
//    dla uzycia w przyszlosci
    public void verifySensorAddSnackbarPl(String sensorAddSnackbarPl){
        Assert.assertEquals(sensorAddSnackbarPl, "Czujnik 61 nie został dodany." );
    }
    public void verifySensorAddSnackbarEn(String sensorAddSnackbarEn){
        Assert.assertEquals(sensorAddSnackbarEn, "Sensor 61 could not be added." );
    }

    public boolean mapLoaded(WebElement webElement, int time){
       return isElementDisplayed(driver, webElement, time);
    }

    public boolean snackBarExist(WebElement webElement, int time) {
        return isElementDisplayed(driver, webElement, time);
    }

    public void clickToAddPoint(int xOffset, int yOffset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(homePlan, xOffset, yOffset).click().perform();
    }
}
