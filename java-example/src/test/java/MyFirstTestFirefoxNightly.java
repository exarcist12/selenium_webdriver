
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTestFirefoxNightly {


    private WebDriver firefoxDriver;
    private WebDriverWait wait;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        firefoxDriver = new FirefoxDriver(options);
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