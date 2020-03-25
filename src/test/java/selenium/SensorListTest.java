package selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.SensorListPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorListTest extends TestBase {

    private SensorListPage sensorListPage;

    @BeforeClass
    public void beforeClass() {
        sensorListPage = new SensorListPage(driver);
        sensorListPage.goTo();
    }

    @Test
    public void listSectionsOrderTest() {
        List<String> sectionNamesList = sensorListPage.getListSectionNames();
        String firstSectionExpectedName = "Nie umieszczone na mapie";
        String secondSectionExpectedName = "Widoczne na mapie";

        Assert.assertEquals(sectionNamesList.get(0), firstSectionExpectedName);
        Assert.assertEquals(sectionNamesList.get(1), secondSectionExpectedName);
    }

    @Test
    public void listAutomaticScrollableTest() {
        Assert.assertTrue(sensorListPage.isListScrollable());
    }

    @Test
    public void sensorColorAccordingToTypeTest() {
        for (WebElement element : sensorListPage.getListOfAllSensors()) {
            String sensorType = sensorListPage.getSensorsType(element);
            String expectedColor = sensorTypeAndColorMap().get(sensorType);
            String actualColor = sensorListPage.getSensorsColor(element);

            Assert.assertEquals(actualColor, expectedColor, "In sensor type: <" + sensorType + "> ->");
        }
    }

    @Test
    public void sensorValueTypeTest() {
        for (WebElement element : sensorListPage.getListOfAllSensors()) {
            Assert.assertTrue(isValueTypeMatching(element), sensorListPage.getSensorsType(element) + " " + sensorListPage.getSensorsValue(element));
        }
    }

    public Map<String, String> sensorTypeAndColorMap() {
        Map<String, String> typesAndColors = new HashMap<>();
        typesAndColors.put("Temperatura", "rgb(255, 153, 51)");
        typesAndColors.put("Okno", "rgb(136, 77, 255)");
        typesAndColors.put("Zasłony", "rgb(224, 81, 148)");
        typesAndColors.put("RFID", "rgb(255, 141, 133)");
        typesAndColors.put("Światło", "rgb(41, 160, 58)");
        typesAndColors.put("Czujnik dymu", "rgb(128, 128, 128)");
        return typesAndColors;
    }

    public boolean isValueTypeMatching(WebElement element) {
        String sensorsValue = sensorListPage.getSensorsValue(element);
        switch (sensorListPage.getSensorsType(element)) {
            case "Okno":
                return sensorsValue.equals("OTWARTE") || sensorsValue.equals("ZAMKNIĘTE");
            case "Czujnik dymu":
                return sensorsValue.equals("WYKRYTO") || sensorsValue.equals("NIE WYKRYTO");
            case "Zasłony":
                return Integer.parseInt(sensorsValue) <= 100 && Integer.parseInt(sensorsValue) >= 0;
            case "Temperatura":
                return sensorsValue.matches("\\d*.*\\d*°C");
            case "RFID":
                return sensorsValue.matches("Ostatnia akcja:\\nRFIDTag \\d+\\n\\d{2}\\/\\d{2}\\/\\d{4}, \\d{2}:\\d{2}:\\d{2} (PM|AM)");
            case "Światło":
                return sensorsValue.matches("odcień: \\d+\\nnasycenie: \\d+\\njasność: \\d+");
            default:
                return false;
        }
    }
}
