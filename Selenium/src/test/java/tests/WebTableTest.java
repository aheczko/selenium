package tests;

import bases.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
public class WebTableTest extends BaseTest {

    protected static Logger log = LoggerFactory.getLogger(FillFormTest.class);
    @Test
    void webTableVeryfication()
    {
        //fluet wait - tylk ona
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("#countries tbody tr"));
        int NUmerOfRows = tableRows.size();
        log.info("Number of rows: " + NUmerOfRows);
        assertThat(NUmerOfRows).isEqualTo(197);

        //Policz która jest polska
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".hasVisited[type='checkbox']"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(checkBoxes.get(145)).perform();

        WebElement polandCheckBox = wait.until(ExpectedConditions.elementToBeClickable(checkBoxes.get(139)));
        log.info("Checkbox - before click -> Selected: " + polandCheckBox.isSelected());
        log.info("Checkbox - before click -> Displayed: " + polandCheckBox.isDisplayed());
        log.info("Checkbox - before click -> Enabled: " + polandCheckBox.isEnabled());

        polandCheckBox.click();
        log.info("Checkbox - before click -> Selected: " + polandCheckBox.isSelected());
        log.info("Checkbox - before click -> Displayed: " + polandCheckBox.isDisplayed());
        log.info("Checkbox - before click -> Enabled: " + polandCheckBox.isEnabled());
        log.info("Checkbox został kliknięty.");
        assertThat(polandCheckBox.isSelected()).isTrue();
        log.info("Asercja ok.");



    }

    @Test
    void WebTableCapitolOfTheCountry()
    {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("#countries tbody tr"));
        int NUmerOfRows = tableRows.size();
        int rowOfPoland=0;
        for (WebElement tableRow : tableRows) {
            if(tableRow.getText().contains("Poland"))
                break;
            rowOfPoland++;
        }
        WebElement capital = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (rowOfPoland + 1) + ") td:nth-child(3)"));
        log.info("Capitol of Poland:"+ capital.getText());
    }

    @Test
    void WebTableCapitolOfTheCountryBurkinaFaso()
    {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        List<WebElement> tableRows = driver.findElements(By.cssSelector("#countries tbody tr"));
        int NUmerOfRows = tableRows.size();
        int rowOfBurkinaFaso=0;
        for (WebElement tableRow : tableRows) {
            if(tableRow.getText().contains("Burkina Faso"))
                break;
            rowOfBurkinaFaso++;
        }
        WebElement capital = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (rowOfBurkinaFaso + 1) + ") td:nth-child(3)"));
        log.info("Capitol of BurkinaFaso:"+ capital.getText());
        assertThat(capital.getText()).isEqualTo("Ouagadougou");
    }

}
