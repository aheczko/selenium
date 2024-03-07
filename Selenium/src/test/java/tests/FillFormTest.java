package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FillFormTest {
    private String appurl="http://www.automationpractice.pl/";
    private String browsername ="Chrome";
    //deklaracja sterownika do przeglądarki
    private WebDriver driver;
    @Test
    @DisplayName("Contact US form")
    void fillFormForAutomationPractice(){

    }

    private WebDriver getDriver()
    {
        switch (this.browsername){
            case "edge"-> {driver = new EdgeDriver();
            }
            case "chrome" -> {driver = new ChromeDriver();
            }
            case "firefox" -> {driver = new FirefoxDriver();
            }
            default -> System.out.println("Unsupported driver for web browser");
        }
        return  driver;
    }
}
