package pages;

import bases.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.FillFormTest;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
    private static Logger log = LoggerFactory.getLogger(FillFormTest.class);

    @FindBy(id = "id_contact")
    private WebElement subjectHeadingDropDownList;
    @FindBy(css ="#email")
    private WebElement adressEmail;
    @FindBy(css ="#id_order")
    private WebElement orderReference;
    @FindBy(css ="#message")
    private WebElement message;
    @FindBy(css ="#fileUpload")
    private WebElement fileUpload;
    @FindBy(css ="#submitMessage > span")
    private WebElement submitMessageBtn;
    @FindBy(className = "alert")
    private WebElement alertAfterSendMessage;



    public void clickDropDownListSubjectHeadingAndSelectValue()
    {
        subjectHeadingDropDownList.click();
        log.info("Krok 2: kliknięcie listy rozwijanej SubjectHeading");
        Select select = new Select(subjectHeadingDropDownList);
        select.selectByVisibleText("Webmaster");
        log.info("Krok 3: wybranie pozycji z listy rozwijanej");


    }
    public void sendTextToEmailAddress(String adress)
    {
        adressEmail.clear();
        adressEmail.sendKeys(adress);
        log.info("Krok 4: wprowadzenie adresu e-mail.");
    }
    public void sendTextToOrderReference(String txtReference)
    {
        orderReference.clear();
        orderReference.sendKeys(txtReference);
        log.info("Krok 5: wprowadzenie tytułu wiadomości");
    }
    public void sendTextToMessageEmail(String txtmessage)
    {
        message.clear();
        message.sendKeys(txtmessage);
        log.info("Krok 6: wprowadzenie testowej wiadomości");
    }

    public void choosenFileInModal(String filePath)
    {
        File uploadFile = new File(filePath);
        fileUpload.clear();
        fileUpload.sendKeys(uploadFile.getAbsolutePath());
        log.info("Krok 7: dodanie załącznika.");


    }
    public void clickTheButtonSubmit()
    {
        submitMessageBtn.click();
        log.info("Krok 8: wysłanie wiadomości.");
    }

    public void assertThatAfterSentMessageIsOk(String expectedMessage)
    {
        String actualMessage = alertAfterSendMessage.getText();
        assertThat(actualMessage).contains(expectedMessage);
        log.info("Sprawdzenie asercji.");

    }





}
