package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.HvacPage;

public class HvacTest3 extends TestBase {
    private HvacPage hvacPage;

    @BeforeClass
    public void beforeClass() {
        hvacPage = new HvacPage(driver);
        hvacPage.goTo();
    }

    @Test
    public void ruleWithRequiredInputsForHvacRoom1() {
        hvacPage.changeToEnglishLanguage();
        hvacPage.step1ForRoom1WithoutName();
        hvacPage.step2TemperatureSensor62();
        hvacPage.step2DeselectAllWindowSensors();
        hvacPage.step3Temperatures();
        String heatingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.heatingTemperature);
        String coolingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.coolingTemperature);
        String hysteresisOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.hysteresis);
        hvacPage.finishHvacRule();
        hvacPage.showRule1DetailsForRequiredInputs();
        Assert.assertEquals(hvacPage.rule1Name.getText(), "HVAC Rule 1");
        Assert.assertTrue(hvacPage.rule1HeatingTemperature.getText().contains(heatingTemperatureOnSlider), "Heating temperature doesn't match");
        Assert.assertTrue(hvacPage.rule1CoolingTemperature.getText().contains(coolingTemperatureOnSlider), "Cooling temperature doesn't match");
        Assert.assertTrue(hvacPage.rule1Hysteresis.getText().contains(hysteresisOnSlider), "Hysteresis doesn't match");
        Assert.assertTrue(hvacPage.rule1TemperatureSensorId.getText().contains("62"), "Temperature sensor id doesn't match");
        Assert.assertFalse(hvacPage.areWindowSensorsNotDisplayedInRule1(), "Window sensors are displayed");
    }

}
