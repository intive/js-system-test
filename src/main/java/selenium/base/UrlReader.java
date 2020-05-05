package selenium.base;

import java.io.IOException;
import java.util.Properties;

public enum UrlReader {
    INSTANCE;

    private String appUrl;

    UrlReader() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        appUrl = props.getProperty("appUrl");
    }

    public String getAppUrl() {
        return appUrl;
    }
}