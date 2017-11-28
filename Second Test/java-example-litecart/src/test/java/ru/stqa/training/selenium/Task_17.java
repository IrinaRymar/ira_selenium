package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task_17 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    @Test
    public void mySecondTest() {

        driver.get("http://localhost:10000/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List <WebElement> goods1 = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));

        for(int i=3; i<goods1.size(); i++)
        {
            List <WebElement> goods = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));
            goods.get(i).click();
            driver.manage().logs().get("browser").forEach(l->System.out.println(l));

            driver.navigate().back();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

