
import org.junit.After;
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


public class TestCheckSticker {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void testCheckSticker() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> links = driver.findElements(By.className("product"));
        for (WebElement link : links) {
            List<WebElement> stickers = link.findElements(By.className("sticker"));
            Assertions.assertEquals(stickers.size(), 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}