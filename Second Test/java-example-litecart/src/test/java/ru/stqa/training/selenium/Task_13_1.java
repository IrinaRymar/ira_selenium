package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Task_13_1 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 120);
    }

    boolean isSizePresent(WebDriver driver, By locator)
    {
        return driver.findElements(locator).size()>0;
    }

    @Test
    public void myTask_13() {
        driver.get("http://localhost:10000/litecart/en/");
        for(int i=1; i<=3; i++) {
            driver.findElement(By.xpath("//div[@class='image-wrapper']")).click();
            boolean a = isSizePresent(driver,By.xpath("//td[@class='options']"));
            if (a==true)
            {
                WebElement size = driver.findElement(By.xpath("//select[@name='options[Size]']"));
                Select select_size = new Select(size);
                select_size.selectByValue("Small");
            }

            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            WebElement quantity = driver.findElement(By.xpath("//span[@class='quantity']"));
            String value = String.valueOf(i);
            wait.until(textToBe(By.xpath("//span[@class='quantity']"),value));
            driver.navigate().back();
        }
        driver.findElement(By.xpath("//a[@class='link']")).click();


        List<WebElement> duck = driver.findElements(By.xpath("//td[@class='item']"));

        for(int i=0; i<duck.size(); i++) {
            WebElement el = driver.findElement(By.xpath("//div[@id='box-checkout-summary']"));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(el));

        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
