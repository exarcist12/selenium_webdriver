
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TaskThirteen {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
   driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void taskEleven() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        for (int i = 1; i<4; i++) {
            WebElement utka = driver.findElement(By.cssSelector("div#box-most-popular li.product"));
            utka.click();
            if (isElementPresent(By.cssSelector("select"))){
                WebElement selectUtka = driver.findElement(By.cssSelector("select[name='options[Size]']"));
                Select selectUtka2 = new Select(selectUtka);
                selectUtka2.selectByVisibleText("Small");
            }
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            WebElement count = driver.findElement(By.cssSelector("span.quantity"));
            String a = Integer.toString (i);
            wait.until(ExpectedConditions.attributeContains(count, "textContent", a));
            WebElement home = driver.findElement(By.cssSelector("a[href='/litecart/']"));
            home.click();
        }
        driver.findElement(By.cssSelector("a[href*=checkout].link")).click();
        List<WebElement> itemsBefore = driver.findElements(By.cssSelector("td.item"));
        for (int i=0; i< itemsBefore.size(); i++) {
            List<WebElement> items = driver.findElements(By.cssSelector("td.item"));
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), items.size() - 1));
        }
        List<WebElement> itemsAfter = driver.findElements(By.cssSelector("td.item"));
        Assertions.assertTrue(itemsAfter.size() == 0);
        WebElement text = driver.findElement(By.cssSelector("div#checkout-cart-wrapper p em"));
        wait.until(ExpectedConditions.attributeContains(text, "textContent", "There are no items in your cart."));
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
