

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSeventeen {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
       wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void taskSeventeen()  {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get(" http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> list = driver.findElements(By.xpath("//tr[contains(@class,'row') and .//td[@style='text-align: right;']]"));
        for (int i =1; i <= list.size(); i++){
            driver.findElement(By.xpath("//tr[contains(@class,'row') and .//td[@style='text-align: right;']]["+ i +"]/td[3]/a")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
            List<LogEntry> l  = driver.manage().logs().get("browser").getAll();
            assertEquals(l.size(), 0);
            driver.get(" http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
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