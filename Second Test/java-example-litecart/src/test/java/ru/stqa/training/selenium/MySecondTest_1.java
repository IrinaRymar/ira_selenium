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

public class MySecondTest_1 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    @Test
    public void mySecondTest_1() {
        driver.get("http://localhost:10000/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        List<WebElement> li_from_BoxAppsMenu = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));
        int kol_1 = li_from_BoxAppsMenu.size();

        for(int i=1; i<kol_1; i++)
        {
            driver.findElement(By.xpath(String.format("(//ul[@id='box-apps-menu']/li)[%s]",i))).click();
            Assert.assertTrue("Нет заголовка!!!", !driver.findElements(By.xpath("//h1[@style='margin-top: 0px;']")).isEmpty());

            List<WebElement> li__from_each = driver.findElements(By.xpath("//ul[@class='docs']/li"));
            int kol_2 = li__from_each.size();
            for(int j=1; j<kol_2; j++)
            {
                driver.findElement(By.xpath(String.format("(//ul[@class='docs']/li)[%s]",j))).click();
                Assert.assertTrue("Нет заголовка!!!", !driver.findElements(By.xpath("//h1[@style='margin-top: 0px;']")).isEmpty());
            }
        }



    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
