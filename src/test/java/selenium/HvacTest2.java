package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.HvacPage;

public class HvacTest2 extends TestBase {
    private HvacPage hvacPage;

    @BeforeClass
    public void beforeClass() {
        hvacPage = new HvacPage(driver);
        hvacPage.goTo();
    }

    @Test
    public void ruleWithAllInputsForHvacRoom2() {
        hvacPage.step1ForRoom2WithName();
        hvacPage.step2TemperatureSensor62();
        hvacPage.step2SelectAllWindowSensors();
        hvacPage.step3Temperatures();
        String heatingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.heatingTemperature);
        String coolingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.coolingTemperature);
        String hysteresisOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.hysteresis);
        hvacPage.finishHvacRule();
        hvacPage.showRule2Details();
        Assert.assertEquals(hvacPage.rule2Name.getText(), "QA Test 2");
        Assert.assertTrue(hvacPage.rule2HeatingTemperature.getText().contains(heatingTemperatureOnSlider), "Heating temperature doesn't match");
        Assert.assertTrue(hvacPage.rule2CoolingTemperature.getText().contains(coolingTemperatureOnSlider), "Cooling temperature doesn't match");
        Assert.assertTrue(hvacPage.rule2Hysteresis.getText().contains(hysteresisOnSlider), "Hysteresis doesn't match");
        Assert.assertTrue(hvacPage.rule2TemperatureSensorId.getText().contains("62"), "Temperature sensor id doesn't match");
        Assert.assertTrue(hvacPage.findWindowIdRule2("81"), "Window sensor 81 has not been found");
        Assert.assertTrue(hvacPage.findWindowIdRule2("82"), "Window sensor 82 has not been found");
        Assert.assertTrue(hvacPage.findWindowIdRule2("83"), "Window sensor 83 has not been found");
    }

}
