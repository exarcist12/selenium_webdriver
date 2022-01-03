
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TaskEleven {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
     driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void taskEleven() {
        driver.get("http://localhost/litecart/en/");
        WebElement createAccount = driver.findElement(By.cssSelector("div.left a[href*=create_account]"));
        createAccount.click();
        Date date = new Date();
        long dataa = date.getTime();
        String strLong = Long.toString(dataa);
        driver.findElement(By.cssSelector("input[name=tax_id]")).sendKeys(strLong);
        driver.findElement(By.cssSelector("input[name=company]")).sendKeys("company"+strLong);
        driver.findElement(By.cssSelector("input[name=firstname")).sendKeys("name"+strLong);
        driver.findElement(By.cssSelector("input[name=lastname")).sendKeys("lastname");
        driver.findElement(By.cssSelector("input[name=address1")).sendKeys("address1");
        driver.findElement(By.cssSelector("input[name=address2")).sendKeys("address2");
        driver.findElement(By.cssSelector("input[name=postcode")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name=city")).sendKeys("city");

/*        WebElement selectCountry = driver.findElement(By.cssSelector("select[name=country_code]"));
        Select selected = new Select(selectCountry);
        selected.selectByVisibleText("United States");
 */       WebElement selectCountry = driver.findElement(By.cssSelector("span.select2-selection__rendered"));
        selectCountry.click();
        WebElement selectCountry2 = driver.findElement(By.cssSelector("input.select2-search__field"));
        selectCountry2.sendKeys("United States" + Keys.ENTER);


        WebElement selectZone = driver.findElement(By.cssSelector("select[name=zone_code]"));
        Select selected2 = new Select(selectZone);
        selected2.selectByVisibleText("Arizona");
        String login = "name"+strLong+"@test.com";
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("+"+strLong);
        String password = "admin";
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        driver.findElement(By.cssSelector("a[href*=logout]")).click();
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        Assertions.assertTrue(isElementPresent(By.cssSelector("a[href*=logout]")));
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
