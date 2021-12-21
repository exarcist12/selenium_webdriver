
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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


public class CheckAlphabetSort {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void сheckAlphabetSort() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> mainElements = driver.findElements(By.cssSelector("tr.row td:not([style='text-align: right;']) a"));
        List<String> countries = new ArrayList<>();
        List<String> countriesBefore = new ArrayList<>();
        for (WebElement element : mainElements) {
            String country = element.getAttribute("text");
            countries.add(country);
            countriesBefore.add(country);
        }
        Collections.sort(countries);

        Assert.assertTrue(countries.equals(countriesBefore));
        List<String> countZones = new ArrayList<>();
        List<WebElement> allCountries = driver.findElements(By.xpath("//tr[@class='row']"));
        for (WebElement strana : allCountries) {
            WebElement allCountrieTd = strana.findElement(By.xpath(".//td[6]"));
            String textContent = allCountrieTd.getAttribute("textContent");
            countZones.add(textContent);
        }

        for (int i =1; i <countZones.size(); i++) {
            String a = "0";
            String text = countZones.get(i);
            if (!text.equals(a)) {
                WebElement countryclick = driver.findElement(By.xpath("//tr[@class='row']["+ (i+1) +"]//td[5]//a"));
                String texts  = countryclick.getAttribute("textContent");
                countryclick.click();
                List<WebElement> zoneElements = driver.findElements(By.xpath("//form//tbody//tr//td[3]//input[@type='hidden']"));
                List<String> zonesAfter = new ArrayList<>();
                List<String> zonesBefore = new ArrayList<>();
                for (WebElement zoneElement : zoneElements) {
                    String  zona = zoneElement.getAttribute("defaultValue");
                    zonesAfter.add(zona);
                    zonesBefore.add(zona);
                }
                Collections.sort(zonesAfter);
                Assert.assertTrue(zonesAfter.equals(zonesBefore));
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
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