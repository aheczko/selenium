package tests;
import bases.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ContactUsPage;
import pages.MainPage;
import pages.MessagePage;

public class FillFormPlusPageObjectPatternTest extends BaseTest {
    @Test
    @DisplayName("Contact US form")
    public void fillFormForAutomationPractice()
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickOnContactLink();

        ContactUsPage contactPage = new ContactUsPage(driver);
        contactPage.clickDropDownListSubjectHeadingAndSelectValue();
        contactPage.sendTextToEmailAddress("aheczko87@gmail.com");
        contactPage.sendTextToOrderReference("Selenium testowanie selenium");
        contactPage.sendTextToMessageEmail("Testowy tekst wiadomości e mail");
        contactPage.choosenFileInModal("c://opt//info.txt");
        contactPage.clickTheButtonSubmit();

        MessagePage msgPage = new MessagePage(driver);
        msgPage.assertThatAfterSentMessageIsOk("Your message has been successfully sent to our team.");


    }
}
