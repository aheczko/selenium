package pages;

import bases.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.FillFormTest;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagePage extends BasePage {
    public  MessagePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    private static Logger log = LoggerFactory.getLogger(FillFormTest.class);
    @FindBy(className = "alert")
    private WebElement alertAfterSendMessage;

    public void assertThatAfterSentMessageIsOk(String expectedMessage)
    {
        String actualMessage = alertAfterSendMessage.getText();
        assertThat(actualMessage).contains(expectedMessage);
        log.info("Sprawdzenie asercji.");

    }
}
