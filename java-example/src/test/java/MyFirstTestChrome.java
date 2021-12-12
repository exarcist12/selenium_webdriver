
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTestChrome {


    private WebDriver chromeDriver;
    private WebDriverWait wait;

    @Before
    public void start(){
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 10);
    }


    @Test
    public void myFirstTest() {
        chromeDriver.get("http://www.google.com");
        chromeDriver.findElement(By.name("q")).sendKeys("webdriver");
        chromeDriver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @After
    public void stop() {
        chromeDriver.quit();
        chromeDriver = null;
    }

}