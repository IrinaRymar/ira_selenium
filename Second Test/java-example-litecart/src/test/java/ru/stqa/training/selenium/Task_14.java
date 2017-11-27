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
import org.openqa.selenium.support.ui.ExpectedCondition;


import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class Task_14 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    public ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows)
    {
        return new ExpectedCondition<String>()         {
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };

    }


    @Test
    public void myTask_14() {
        driver.get("http://localhost:10000/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


        driver.findElement(By.cssSelector("tr.row a[title=Edit]")).click();
        List<WebElement> link1 = driver.findElements(By.xpath("//form//a[@target='_blank']"));

        for(int i=0; i<link1.size(); i++)
        {
            List<WebElement> link = driver.findElements(By.xpath("//form//a[@target='_blank']"));
            //запомнинаем идентификатор текущего окна
            String mainWindow = driver.getWindowHandle();
            //запоминаем идентификаторы уже открытых окон
            Set<String> oldWindows = driver.getWindowHandles();
            //открываем новое окно
            link.get(i).click();
            // ожидание появления нового окна,
            // идентификатор которого отсутствует в списке oldWindows,
            // остаётся в качестве самостоятельного упражнения
            String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            //переключаемся в новое окно
            driver.switchTo().window(newWindow);
            //закрываем его
            driver.close();
            //возвращаемся в главное окно
            driver.switchTo().window(mainWindow);
            //driver.navigate().back();

        }



        //driver.navigate().back();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
