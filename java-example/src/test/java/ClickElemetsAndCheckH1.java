
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        Assertions.assertTrue(isElementPresent(By.id("box-widgets-wrapper")));
        driver.findElement(By.className("fa-adjust")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-th")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-flag")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-money")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-user")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-globe")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-comments")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-cube")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-shopping-cart")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-file-text")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-pie-chart")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-cogs")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-picture-o")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-university")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-book")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-star")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
        driver.findElement(By.className("fa-plug")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("h1")));
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

}