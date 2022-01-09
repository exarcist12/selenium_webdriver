package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page{
    public MainPage (WebDriver driver) {
        super (driver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    public WebElement utka() {
        return driver.findElement(By.cssSelector("div#box-most-popular li.product"));
    }


}
