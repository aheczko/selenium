package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FillFormTest {
    private String appurl="http://www.automationpractice.pl/";
    private String browsername ="chrome";
    //deklaracja sterownika do przeglądarki
    private WebDriver driver;
    private final boolean headlessBrowser = false; //true - bez widocznej przeglądarki  false - przeglądarka jest widoczna.


    @Test
    @DisplayName("Contact US form")
    void fillFormForAutomationPractice(){
        driver = getDriver();
        driver.get(appurl);


        driver.quit();
    }

    private WebDriver getDriver()
    {
        switch (this.browsername){
            case "edge"-> {
                EdgeOptions options = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    options.addArguments("--headless");
                }
                driver = new EdgeDriver(options);
            }
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("start-maximized");
                if (this.headlessBrowser) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }
            default -> throw new UnsupportedOperationException("Unsupported browser selected.");
        }
        return  driver;
    }
}
