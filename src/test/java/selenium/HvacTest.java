package selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.base.TestBase;
import selenium.pages.HvacPage;

import java.io.IOException;

public class HvacTest extends TestBase {

    private HvacPage hvacPage;

    @BeforeClass
    public void beforeClass() {
        hvacPage = new HvacPage(driver);
        hvacPage.goTo();
    }

    @Test(priority = 1)
    public void ruleWithAllInputsForHvacRoom1() {
        hvacPage.step1ForRoom1WithName();
        hvacPage.step2TemperatureSensor61();
        hvacPage.step2SelectAllWindowSensors();
        hvacPage.step3Temperatures();
        String heatingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.heatingTemperature);
        String coolingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.coolingTemperature);
        String hysteresisOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.hysteresis);
        hvacPage.finishHvacRule();
        hvacPage.showRule1Details();
        Assert.assertEquals(hvacPage.rule1Name.getText(), "QA Test 1");
        Assert.assertTrue(hvacPage.rule1HeatingTemperature.getText().contains(heatingTemperatureOnSlider), "Heating temperature doesn't match");
        Assert.assertTrue(hvacPage.rule1CoolingTemperature.getText().contains(coolingTemperatureOnSlider), "Cooling temperature doesn't match");
        Assert.assertTrue(hvacPage.rule1Hysteresis.getText().contains(hysteresisOnSlider), "Hysteresis doesn't match");
        Assert.assertTrue(hvacPage.rule1TemperatureSensorId.getText().contains("61"), "Temperature sensor id doesn't match");
        Assert.assertTrue(hvacPage.findWindowIdRule1("81"), "Window sensor 81 has not been found");
        Assert.assertTrue(hvacPage.findWindowIdRule1("82"), "Window sensor 82 has not been found");
        Assert.assertTrue(hvacPage.findWindowIdRule1("83"), "Window sensor 83 has not been found");
    }

    @Test(priority = 2)
    public void incompleteRule() {
        hvacPage.refreshPage();
        hvacPage.ruleWithNoData();
        Assert.assertTrue(hvacPage.isIncompleteFormSnackbarDisplayed(), "Snackbar for incomplete rule is not displayed");
    }

    @Test(priority = 5)
    public void ruleWithNoInternetConnection() throws IOException {
        hvacPage.refreshPage();
        hvacPage.ruleWithNoInternetConnection();
        Assert.assertTrue(hvacPage.isNotSavedRuleSnackbarDisplayed(), "Snackbar for not saved rule is not displayed");
        hvacPage.setOnline();
    }

    @Test(priority = 3)
    public void hvacNameInputValidation() {
        hvacPage.refreshPage();
        hvacPage.ruleWithTooLongName();
        Assert.assertEquals(hvacPage.rule1Name.getText(), "Typing long name more than 30.");
        Assert.assertEquals(hvacPage.rule1Name.getText().length(), 30);
    }

    @Test(priority = 4)
    public void minimumCoolingTemperature() {
        hvacPage.refreshPage();
        hvacPage.setMinimumCoolingTemperature();
        String heatingTemperatureOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.heatingTemperature);
        String hysteresisOnSlider = hvacPage.getTemperatureFromSlider(hvacPage.hysteresis);
        double heatingTemperature = Double.parseDouble(heatingTemperatureOnSlider);
        double hysteresis = Double.parseDouble(hysteresisOnSlider);
        double minimumCoolingTemperature = heatingTemperature + hysteresis;
        String minimumCoolingTemperatureString = String.valueOf(minimumCoolingTemperature);
        String substringMinCoolingTemp = minimumCoolingTemperatureString.substring(2);
        if (substringMinCoolingTemp.equals(".0")) {
            minimumCoolingTemperatureString = minimumCoolingTemperatureString.replace(".0", "");
        }
        if (minimumCoolingTemperature > 10.0) {
            Assert.assertTrue(hvacPage.minimumCoolingTemperature.getText().contains(minimumCoolingTemperatureString));
        } else {
            Assert.assertTrue(hvacPage.minimumCoolingTemperature.getText().contains("10"));
        }
    }
}
