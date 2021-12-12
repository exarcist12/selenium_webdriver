
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTestFirefox {


    private WebDriver firefoxDriver;
    private WebDriverWait wait;

    @Before
    public void start(){
        firefoxDriver = new FirefoxDriver();
        wait = new WebDriverWait(firefoxDriver, 10);
    }


    @Test
    public void myFirstTest() {
        firefoxDriver.get("http://www.google.com");
        firefoxDriver.findElement(By.name("q")).sendKeys("webdriver");
        firefoxDriver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop() {
        firefoxDriver.quit();
        firefoxDriver = null;
    }

}