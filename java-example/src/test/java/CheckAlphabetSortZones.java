
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CheckAlphabetSortZones {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void checkAlphabetSortZones() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> listCountries = driver.findElements(By.xpath("//a[@title='Edit']"));
        for (int i=1; i<=listCountries.size(); i++){
            driver.findElement(By.xpath("(//a[@title='Edit'])["+ i +"]")).click();
            List<WebElement> nameZones = driver.findElements(By.cssSelector("[name*=zone_code] option[selected=selected]"));
            List<String> zonesAfter = new ArrayList<>();
            for (WebElement zone : nameZones) {
                String zoneNow = zone.getAttribute("textContent");
                zonesAfter.add(zoneNow);
            }
            List<String> zonesBefore = zonesAfter;
            Collections.sort(zonesAfter);

            Assert.assertTrue(zonesAfter.equals(zonesBefore));
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
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