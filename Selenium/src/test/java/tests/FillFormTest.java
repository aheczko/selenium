package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(appurl);
        //Krok1: Click Contac US
        WebElement contactLink = driver.findElement(By.cssSelector("#contact-link > a"));
        contactLink.click();
        WebElement dropDownList = driver.findElement(By.id("id_contact"));
        dropDownList.click();
        Select select = new Select(dropDownList);
        select.selectByVisibleText("Webmaster");
        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.sendKeys("aheczko87@gmail.com");
        WebElement order = driver.findElement(By.cssSelector("#id_order"));
        order.sendKeys("Selenium testowanie selenium");
        WebElement textMsg = driver.findElement(By.cssSelector("#message"));
        textMsg.sendKeys("testowy tekst wiadomości e mail");
        File uploadFile = new File("c://opt//info.txt");
        //WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        WebElement fileInput = driver.findElement(By.cssSelector("#fileUpload"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        WebElement sendBtn = driver.findElement(By.cssSelector("#submitMessage > span"));
        sendBtn.click();



        //Krok 8: assert
        String expectedMessage ="Your message has been successfully sent to our team.";
        WebElement succesMsg = driver.findElement(By.className("alert"));
        String actualMessage = succesMsg.getText();
        assertThat(actualMessage).contains(expectedMessage);




        driver.quit();
    }
    //"Your message has been successfully sent to our team."

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
