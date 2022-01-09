package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends Page {
    public BasketPage(WebDriver driver) {
        super(driver);
    }


    public void open() {
        driver.findElement(By.cssSelector("a[href*=checkout].link")).click();
    }

    public List<WebElement> getItems(){
         List<WebElement> items = driver.findElements(By.cssSelector("td.item"));
         return items;
    }

    public void remove(){
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
    }

    public void waitChutChut(int count) {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), count - 1));
    }

    public WebElement getText(){
        return driver.findElement(By.cssSelector("div#checkout-cart-wrapper p em"));
    }
}