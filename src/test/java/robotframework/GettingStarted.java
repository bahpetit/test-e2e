package robotframework;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class GettingStarted {
    private String url;
    WebDriver driver;
    JavascriptExecutor jse;
    String expectedResult = null;

    public static void main(String args[]) {
        GettingStarted gettingStarted = new GettingStarted("https://www.randstad.fr/");
        gettingStarted.openBrowser();
        gettingStarted.JobSearch("ingénieur", "Île-de-France");
        System.out.println("Level Studies");
        gettingStarted.checkLevelStudies();
        System.out.println("Level Studies");
        gettingStarted.checkSalary();
        System.out.println("Level Salary");
        gettingStarted.checkTypeSalary();
        System.out.println("Level Type");
        gettingStarted.closeBrowser();
    }

    public GettingStarted(String url) {
        this.url = url;
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        jse = (JavascriptExecutor) driver;
    }

    public void openBrowser() {
        driver.get(this.url);
        driver.manage().window().maximize();
    }

    public void closeBrowser(){
        driver.quit();
    }


    public void JobSearch(String job, String localisation)  {

        //Set le job
        if(isElementPresent(By.id("id_What"))){
            WebElement jobWebElement = driver.findElement(By.id("id_What"));
            jobWebElement.sendKeys(job);
        }

        if(isElementPresent(By.id("nlc"))){
            WebElement webElement = driver.findElement(By.id("nlc"));
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("arguments[0].value='"+localisation+"';", webElement);
        }

        //clik button rechercher
        clickWebElement("//div[@class='clearfix divEng search-home']/button");

        await().atMost(ONE_MINUTE)
                .pollInterval(ONE_SECOND)
                .with().pollDelay(TWO_SECONDS)
                .until(waitForLoad(By.id("s")));

        //Filtre par date
        Select drpDate = new Select(driver.findElement(By.id("s")));
        drpDate.selectByVisibleText("Date");

        //Obtenir le premier element
        clickWebElement("//div[@class='items-list jobs-list']/article[@class='item clearfix'][1]/a");

        //Obtenir les informations complemetaire
        WebElement infosWebElement = driver.findElement(By.xpath("//div[@class='rte']/p[last()]"));
        if(infosWebElement.getText() != null){
            expectedResult = infosWebElement.getText().toUpperCase();
        }
    }

    public void checkLevelStudies() {
        assertThat(expectedResult, is(not(nullValue())));
        assertThat(expectedResult, containsString("niveau d'études".toUpperCase()));
    }

    public void checkSalary() {
        assertThat(expectedResult, is(not(nullValue())));
        assertThat(expectedResult, containsString("salaire minimum".toUpperCase()));
    }

    public void checkTypeSalary() {
        assertThat(expectedResult, is(not(nullValue())));
        assertThat(expectedResult, containsString("type de salaire".toUpperCase()));
    }

    private Callable<Boolean> waitForLoad(final By xpath) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return isElementPresent(xpath);
            }
        };
    }

    private void clickWebElement(String xpath) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        jse.executeScript("arguments[0].click();", webElement);
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
