
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TaskFourteen {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void сheckAlphabetSort() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a[title=Edit]")).click();
        String windowBeforeOpen = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        List<WebElement> links = driver.findElements(By.cssSelector("i.fa-external-link"));
        for (int i = 1; i<= links.size(); i ++) {
            driver.findElement(By.xpath("(//i[@class='fa fa-external-link'])["+ i +"]")).click();
            String newWindowAfterOpen = wait.until(anyWindowOtherThan(oldWindows));
//        Set<String> newWindows = driver.getWindowHandles();
//        newWindows.removeAll(oldWindows);
//        String windowAfterOpen = newWindows.iterator().next();;
            driver.switchTo().window(newWindowAfterOpen);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
            driver.close();
            driver.switchTo().window(windowBeforeOpen);

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

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {

            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();

                handles.removeAll(oldWindows);
                return handles.size()>0? handles.iterator().next() :null;
            }
        };
    }

}