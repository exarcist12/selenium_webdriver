
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TaskTen {

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

        driver.get("http://localhost/litecart/en/");

        WebElement block = driver.findElement(By.id("box-campaigns"));
        WebElement main = block.findElement(By.cssSelector("li.product.column.shadow.hover-light"));
        WebElement mainName = main.findElement(By.cssSelector("div.name"));
        String mainNameText = mainName.getAttribute("textContent");
        WebElement mainRegularPrice = main.findElement(By.cssSelector("s.regular-price"));
        String mainRegularPriceText = mainRegularPrice.getAttribute("textContent");
        WebElement mainCampaignPrice = main.findElement(By.cssSelector("strong.campaign-price"));
        String mainCampaignPriceText = mainCampaignPrice.getAttribute("textContent");

        String mainRegularPriceColor = mainRegularPrice.getCssValue("color");
        String mainRegularPriceTextDecoration = mainRegularPrice.getCssValue("text-decoration-line");
        Assertions.assertTrue(mainRegularPriceTextDecoration.equals("line-through"));
        String[] numbers = mainRegularPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        Assertions.assertEquals(r,g);
        Assertions.assertEquals(r,b);


        String mainCampaignPriceColor =mainCampaignPrice.getCssValue("color");
        String mainCampaignPriceFontWeight = mainCampaignPrice.getCssValue("font-weight");
        Assertions.assertTrue(mainCampaignPriceFontWeight.equals("700"));
        String[] numbers2 = mainCampaignPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int r2 = Integer.parseInt(numbers2[0].trim());
        int g2 = Integer.parseInt(numbers2[1].trim());
        int b2 = Integer.parseInt(numbers2[2].trim());
        Assertions.assertEquals (b2,0);
        Assertions.assertEquals (g2,0);

        Dimension sizeRegular = mainRegularPrice.getSize();
        Dimension sizeCampaign = mainCampaignPrice.getSize();
        int sizeRegularHeight = sizeRegular.getHeight();
        int sizeRegularWidth = sizeRegular.getWidth();
        int sizeCampaignHeight = sizeCampaign.getHeight();
        int sizeCampaignWidth = sizeCampaign.getWidth();
        Assertions.assertTrue(sizeCampaignHeight>sizeRegularHeight);
        Assertions.assertTrue(sizeCampaignWidth>sizeRegularWidth);

        main.click();

        WebElement boxProduct = driver.findElement(By.id("box-product"));
        WebElement nameProduct = boxProduct.findElement(By.cssSelector("h1[itemprop=name]"));
        String nameProductText = nameProduct.getAttribute("textContent");
        Assertions.assertTrue(nameProductText.equals(mainNameText));
        WebElement productRegularPrice = driver.findElement(By.cssSelector("#box-product s.regular-price"));
        String productRegularPriceText = productRegularPrice.getAttribute("textContent");
        Assertions.assertTrue(productRegularPriceText.equals(mainRegularPriceText));
        WebElement productCampaignPrice = driver.findElement(By.cssSelector("#box-product strong.campaign-price"));
        String productCampaignPriceText = productCampaignPrice.getAttribute("textContent");
        Assertions.assertTrue(productCampaignPriceText.equals(mainCampaignPriceText));

        String productRegularPriceColor = productRegularPrice.getCssValue("color");
        String productRegularPriceTextDecoration = productRegularPrice.getCssValue("text-decoration-line");
        Assertions.assertTrue(productRegularPriceTextDecoration.equals("line-through"));
        String[] numbersProduct = productRegularPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int rProduct = Integer.parseInt(numbersProduct[0].trim());
        int gProduct = Integer.parseInt(numbersProduct[1].trim());
        int bProduct = Integer.parseInt(numbersProduct[2].trim());
        Assertions.assertEquals(rProduct,gProduct);
        Assertions.assertEquals(rProduct,bProduct);

        String productCampaignPriceColor =productCampaignPrice.getCssValue("color");
        String productCampaignPriceFontWeight = productCampaignPrice.getCssValue("font-weight");
        Assertions.assertTrue(productCampaignPriceFontWeight.equals("700"));
        String[] numbersProduct2 = productCampaignPriceColor.replace("rgba(", "").replace(")", "").split(",");
        int rProduct2 = Integer.parseInt(numbersProduct2[0].trim());
        int gProduct2 = Integer.parseInt(numbersProduct2[1].trim());
        int bProduct2 = Integer.parseInt(numbersProduct2[2].trim());
        Assertions.assertEquals (gProduct2,0);
        Assertions.assertEquals (bProduct2,0);

        Dimension sizeRegular2 = productRegularPrice.getSize();
        Dimension sizeCampaign2 = productCampaignPrice.getSize();
        int sizeRegularHeight2 = sizeRegular2.getHeight();
        int sizeRegularWidth2 = sizeRegular2.getWidth();
        int sizeCampaignHeight2 = sizeCampaign2.getHeight();
        int sizeCampaignWidth2 = sizeCampaign2.getWidth();
        Assertions.assertTrue(sizeCampaignHeight2>sizeRegularHeight2);
        Assertions.assertTrue(sizeCampaignWidth2>sizeRegularWidth2);


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