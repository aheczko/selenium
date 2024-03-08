package bases;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    private String browsername ="chrome";
    private final boolean headlessBrowser = false;
    protected String appurl="http://www.automationpractice.pl/";
    protected  String appurl2="https://cosmocode.io/automation-practice-webtable/";

    //Klasa otrzymała swój loger
    protected static Logger log = LoggerFactory.getLogger(BaseTest.class);
    @BeforeEach
    public void setUp(){
        driver = getDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(appurl2);
        log.debug("Otwarcie przeglądarki.");
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
        log.debug("Zamknięcie przeglądarki.");
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
    protected String getCapitolForCountry(String country) {
        int row = 0;
        List<WebElement> table = driver.findElements(By.cssSelector("#countries > tbody > tr"));
        for (WebElement e : table) {
            if (e.getText().contains(country)) {
                break;
            }
            row++;
        }
        WebElement capital = driver.findElement(By.cssSelector("#countries > tbody > tr:nth-child(" + (row + 1) + ") > td:nth-child(3)"));
        return capital.getText();
    }

}
