package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void add() {
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
    }

    public WebElement count() {
        return driver.findElement(By.cssSelector("span.quantity"));
    }

    public void selectSize(String size) {
        wait.until((WebDriver d) -> d.findElement(By.cssSelector(String.format("select[name='options[Size]']", size))));
        new Select(driver.findElement(By.name("options[Size]"))).selectByValue(size);
    }
}
