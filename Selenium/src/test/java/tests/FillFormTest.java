package tests;

import bases.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class FillFormTest extends BaseTest {
    protected static Logger log = LoggerFactory.getLogger(FillFormTest.class);
    @Test
    @DisplayName("Contact US form")
    void fillFormForAutomationPractice(){



        WebElement dropDownList = driver.findElement(By.id("id_contact"));
        dropDownList.click();
        log.info("Krok 2: kliknięcie listy rozwijanej");

        Select select = new Select(dropDownList);
        select.selectByVisibleText("Webmaster");
        log.info("Krok 3: wybranie pozycji z listy rozwijanej");

        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.sendKeys("aheczko87@gmail.com");
        log.info("Krok 4: wprowadzenie adresu e-mail.");

        WebElement order = driver.findElement(By.cssSelector("#id_order"));
        order.sendKeys("Selenium testowanie selenium");
        log.info("Krok 5: wprowadzenie tytułu wiadomości");

        WebElement textMsg = driver.findElement(By.cssSelector("#message"));
        textMsg.sendKeys("testowy tekst wiadomości e mail");
        log.info("Krok 6: wprowadzenie testowej wiadomości");

        File uploadFile = new File("c://opt//info.txt");
        //WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        WebElement fileInput = driver.findElement(By.cssSelector("#fileUpload"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        log.info("Krok 7: dodanie załącznika.");

        WebElement sendBtn = driver.findElement(By.cssSelector("#submitMessage > span"));
        sendBtn.click();
        log.info("Krok 8: wysłanie wiadomości.");

        String expectedMessage ="Your message has been successfully sent to our team.";
        WebElement succesMsg = driver.findElement(By.className("alert"));
        String actualMessage = succesMsg.getText();
        assertThat(actualMessage).contains(expectedMessage);
        log.info("Sprawdzenie asercji.");

    }
    //"Your message has been successfully sent to our team."


}
