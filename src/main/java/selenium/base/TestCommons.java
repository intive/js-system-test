package selenium.base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class TestCommons {

    protected WebDriver driver;
    private final String url = "https://patronage20-js-master.herokuapp.com";

    public TestCommons(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void goTo(String path) {
        driver.get(url + path);
    }

    protected void sendKeysToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected String getElementAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    protected Boolean isImageValid(WebElement imageElement) {
        String Source = imageElement.getAttribute("src");

        try {
            BufferedImage img = ImageIO.read(new URL(Source));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    protected int getElementWidth(WebElement element) {
        double sizeDouble = element.getSize().getWidth();
        int sizeInt = (int) Math.round(sizeDouble);
        return sizeInt;
    }

    protected int getElementHeight(WebElement element) {
        double sizeDouble = element.getSize().getHeight();
        int sizeInt = (int) Math.round(sizeDouble);
        return sizeInt;
    }

    protected Point getElementLocation(WebElement element) {
        return element.getLocation();
    }

}
