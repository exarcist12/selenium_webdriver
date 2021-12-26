

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskTwelve {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
//       wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void taskTwelve() throws InterruptedException {
        Date date = new Date();
        long dataa = date.getTime();
        String strLong = Long.toString(dataa);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("#box-apps-menu a[href*='?app=catalog&doc=catalog']")).click();
        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();
        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();
        String nameProduct = "newUtka"+strLong;
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(nameProduct);
        driver.findElement(By.cssSelector("input[name=code]")).sendKeys("newUtka");
        driver.findElement(By.cssSelector("input[name='categories[]'][value='1']")).click();
        driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-2']")).click();
        WebElement input = driver.findElement(By.cssSelector("input[type=file]"));
        String filePath = "./src/test/resources/img.png";
        input.sendKeys(new File(filePath).getAbsolutePath());
        setDatepicker(driver, "input[name=date_valid_from]", "26/12/2021");
        setDatepicker(driver, "input[name=date_valid_to]", "26/12/2022");
        driver.findElement(By.cssSelector("a[href*=information]")).click();
        Thread.sleep(2000);
        WebElement manufacturer = driver.findElement(By.cssSelector("select[name=manufacturer_id]"));
        Select selected = new Select(manufacturer);
        selected.selectByVisibleText("ACME Corp.");
        WebElement supplier = driver.findElement(By.cssSelector("select[name=supplier_id]"));
        Select selected2 = new Select(supplier);
        selected2.selectByVisibleText("-- Select --");
        driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys("keywords");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("short_description");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("short_description");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("text");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("text");
        driver.findElement(By.cssSelector("a[href*=tab-prices]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("15");
        WebElement currency = driver.findElement(By.cssSelector("select[name=purchase_price_currency_code"));
        Select selected3 = new Select(currency);
        selected3.selectByVisibleText("US Dollars");
        driver.findElement(By.cssSelector("input[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("15");
        driver.findElement(By.cssSelector("input[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("15");
        driver.findElement(By.cssSelector("button[name='save']")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("a[href*=edit_product]:not(.button)"));
 //       List<String> values = driver.findElements(By.cssSelector("a[href*=edit_product]:not(.button)"));
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getAttribute("textContent"));
        }

        Assertions.assertTrue(names.contains(nameProduct));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void setDatepicker(WebDriver driver, String cssSelector, String date){
        new WebDriverWait(driver, 10).until(d->driver.findElement(By.cssSelector(cssSelector)).isDisplayed());
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(date);
        driver.findElement(By.cssSelector("body")).click();
    }


}