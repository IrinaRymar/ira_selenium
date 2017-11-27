package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task_12 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 120);
    }

    @Test
    public void mySecondTest_1() {
        driver.get("http://localhost:10000/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.xpath("(//ul[@id='box-apps-menu']//*[@class='name'])[2]")).click();
        driver.findElement(By.xpath("(//div/a[@class='button'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='status'])[1]")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("Motley Duck");
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("RD020");
        driver.findElement(By.xpath("(//input[@name='categories[]'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='product_groups[]'])[3]"));
        driver.findElement(By.xpath("(//input[@name='quantity'])")).sendKeys("1");

        File file = new File("duck.png");
        String path = file.getAbsolutePath();
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(path);


        driver.findElement(By.xpath("//button[@name='save']")).click();



    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
