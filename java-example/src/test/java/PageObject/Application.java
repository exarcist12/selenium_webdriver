package PageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Application {

    private WebDriverWait wait;
    private WebDriver driver;
    private  MainPage mainPage;
    private  ProductPage productPage;
    private  BasketPage basketPage;

    public Application () {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
       basketPage = new BasketPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void removeProductFromBasket() {
        basketPage.open();
        List<WebElement> itemsBefore = basketPage.getItems();
        for (int i=0; i< itemsBefore.size(); i++) {
            List<WebElement> items = basketPage.getItems();
            basketPage.remove();
            basketPage.waitChutChut(items.size());
        }
    }

    public void addProductInBasket() {
        mainPage.open();
        for (int i = 1; i<4; i++) {
            mainPage.utka().click();
            if (isElementPresent(By.cssSelector("select"))){
                productPage.selectSize("Small");
            }
            productPage.add();
            String a = Integer.toString (i);
            wait.until(ExpectedConditions.attributeContains(productPage.count(), "textContent", a));
            mainPage.open();
        }
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

    public void checkText() {
        List<WebElement> itemsAfter = basketPage.getItems();
        Assertions.assertTrue(itemsAfter.size() == 0);
        wait.until(ExpectedConditions.attributeContains(basketPage.getText(), "textContent", "There are no items in your cart."));
    }


}
