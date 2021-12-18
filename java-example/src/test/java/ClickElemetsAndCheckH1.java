
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ClickElemetsAndCheckH1 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void clickElemetsAndCheckH1() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> mainElements = driver.findElements(By.xpath("(//li[@id='app-'])"));
        for (int i=1; i<=mainElements.size(); i++) {
            driver.findElement(By.xpath("(//li[@id='app-'])["+i+"]")).click();
            Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
            List<WebElement> inElements = driver.findElements(By.xpath("(//ul[@class='docs']//span[@class='name'])"));
            if (inElements.size()>0) {
                for (int j=1; j<= inElements.size(); j++){
                    driver.findElement(By.xpath("(//ul[@class='docs']//span[@class='name'])["+j+"]")).click();
                    Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
                }
            }
        }
   }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

}