package pages;

import bases.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.FillFormTest;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver)

    {
        PageFactory.initElements(driver, this);
    }
    private static Logger log = LoggerFactory.getLogger(FillFormTest.class);

    @FindBy(css = "#contact-link > a")
    private WebElement contactLink;

    public void ClickOnContactLink()
    {
        contactLink.click();
        log.info("Krok 1: kliknięcie Contact US");
    }

}
