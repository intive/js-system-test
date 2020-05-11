package selenium.base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    @BeforeSuite
    public void setChromedriverPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String driverPath = classLoader.getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
    }

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://patronage20-js-master.herokuapp.com");
        driver.manage().addCookie(new Cookie("secret_cookie", "3241231213fsdj23kj4kl32j4"));
        driver.manage().addCookie(new Cookie("SuperToken", "59c5f5b2cb7ca698b5b9dd199a10914dc6047ef1afe07d2879c89637fef05ae2"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
