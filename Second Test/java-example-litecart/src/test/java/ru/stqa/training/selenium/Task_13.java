package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Task_13 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 120);
    }

    @Test
    public void myTask_13() {
        driver.get("http://localhost:10000/litecart/en/");
        for(int i=1; i<=3; i++) {
            driver.findElement(By.xpath("//div[@class='image-wrapper']")).click();
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
