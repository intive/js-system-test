package selenium.base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

import static selenium.base.TestCommons.isElementDisplayed;

public class TestBase {

    protected WebDriver driver;

    @BeforeSuite
    public void setChromedriverPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String driverPath = classLoader.getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    @BeforeTest
    public void setUp (ITestContext context) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        String language = context.getCurrentXmlTest().getParameter("browserLanguage");
        if(language!=null) {
            options.addArguments("--lang=" + language);
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://patronage20-js-master.herokuapp.com");
        driver.manage().addCookie(new Cookie("SuperToken", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    protected void assertionIfElementWasFound(WebElement element, String nameOfElement) {
        Assert.assertTrue(isElementDisplayed(driver, element), "Element " + nameOfElement + " cannot be found on webside");
    }
}
