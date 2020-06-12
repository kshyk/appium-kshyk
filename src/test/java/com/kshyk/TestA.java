package com.kshyk;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.DriverUrl;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.github.bonigarcia.seljup.BrowserType.ANDROID;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestA {
    private static final String KEY = System.getenv("TESTINGBOT_KEY");
    private static final String SECRET = System.getenv("TESTINGBOT_SECRET");
    @DriverUrl
    private static final String URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";

    @RegisterExtension
    static SeleniumExtension seleniumExtension = new SeleniumExtension();

    @BeforeAll
    static void setup() {
        var extensionConfig = seleniumExtension.getConfig();
        extensionConfig.setVnc(true);
        extensionConfig.setRecording(true);
    }

    @Test
    public void testAndroid(
            @DockerBrowser(type = ANDROID, version = "8.1", deviceName = "Nexus S") RemoteWebDriver driver) {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertTrue(driver.getTitle().contains("JUnit 5 extension for Selenium"));
    }
}