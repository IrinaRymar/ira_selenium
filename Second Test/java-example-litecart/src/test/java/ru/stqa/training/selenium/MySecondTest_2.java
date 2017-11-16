package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MySecondTest_2 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    @Test
    public void mySecondTest() {

        driver.get("http://localhost:10000/litecart/en/");
        List<WebElement> ducks = driver.findElements(By.xpath("//div[@class='image-wrapper']"));


        for (int i = 0; i < ducks.size(); i++) {
            List<WebElement> stickers = ducks.get(i).findElements(By.xpath("./*[starts-with(@class,'sticker')]"));
            Assert.assertTrue("Qantity of stickers !=1", stickers.size() == 1);
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
