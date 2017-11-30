package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task_14_2 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    public ExpectedCondition<String> thereIsHandleNotIncludeIn(Set<String> oldWindows)
    {
        return new ExpectedCondition<String>()         {
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                Set<String> result = new HashSet<String>();
                for(String i:handles) {
                    if(!oldWindows.contains(i))
                    {
                        result.add(i);
                    }
                }
                return result.size()>0 ? result.iterator().next():null;

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
            String newWindow = wait.until(thereIsHandleNotIncludeIn(oldWindows));
            //переключаемся в новое окно
            driver.switchTo().window(newWindow);
            //закрываем его
            driver.close();
            //возвращаемся в главное окно
            driver.switchTo().window(mainWindow);
        }




    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
