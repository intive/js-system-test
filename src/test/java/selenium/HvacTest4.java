package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.HvacPage;

public class HvacTest4 extends TestBase {
    private HvacPage hvacPage;

    @BeforeClass
    public void beforeClass() {
        hvacPage = new HvacPage(driver);
        hvacPage.goTo();
    }

    @Test
    public void ruleWithRequiredInputsForHvacRoom2() {
        hvacPage.changeToEnglishLanguage();
        hvacPage.step1ForRoom2WithoutName();
        hvacPage.step2TemperatureSensor61();
        hvacPage.step2DeselectAllWindowSensors();
        hvacPage.step3Temperatures();
        String heatingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.heatingTemperature);
        String coolingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.coolingTemperature);
        String hysteresisOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.hysteresis);
        hvacPage.finishHvacRule();
        hvacPage.showRule2DetailsForRequiredInputs();
        Assert.assertEquals(hvacPage.rule2Name.getText(), "HVAC Rule 2");
        Assert.assertTrue(hvacPage.rule2HeatingTemperature.getText().contains(heatingTemperatureOnSlider), "Heating temperature doesn't match");
        Assert.assertTrue(hvacPage.rule2CoolingTemperature.getText().contains(coolingTemperatureOnSlider), "Cooling temperature doesn't match");
        Assert.assertTrue(hvacPage.rule2Hysteresis.getText().contains(hysteresisOnSlider), "Hysteresis doesn't match");
        Assert.assertTrue(hvacPage.rule2TemperatureSensorId.getText().contains("61"), "Temperature sensor id doesn't match");
        Assert.assertFalse(hvacPage.areWindowSensorsNotDisplayedInRule2(), "Window sensors are displayed");
    }
}
