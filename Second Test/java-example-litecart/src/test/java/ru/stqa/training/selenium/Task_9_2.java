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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task_9_2 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    boolean isListSort(WebDriver driver,By locator)
    {
        List<WebElement> list_countries = driver.findElements(locator);

        List<String> myList = new ArrayList<>();
        List<String> myList_sort = new ArrayList<>();
        for(int i=0; i<list_countries.size(); i++)
        {
            myList.add(i,list_countries.get(i).getAttribute("textContent"));
            myList_sort.add(i,list_countries.get(i).getAttribute("textContent"));
        }
        Collections.sort(myList_sort);

        String[] myArray = {}; // конвертируем myList в массив
        myArray = myList.toArray(new String[myList.size()]);

        String[] myArray1 = {}; // конвертируем myList_sort в массив
        myArray1 = myList_sort.toArray(new String[myList_sort.size()]);

        return Arrays.equals(myArray,myArray1);
    }

    @Test
    public void myTest_9_2() {

        driver.get("http://localhost:10000/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> country_with_geozone1 = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));
        for(int i=0; i<country_with_geozone1.size(); i++)
        {
            List<WebElement> country_with_geozone = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));
            country_with_geozone.get(i).click();

            boolean alf = isListSort(driver,By.cssSelector("select:not([class=select2-hidden-accessible])[name^=zones] [selected=selected]"));
            Assert.assertEquals("Не в алфавитном порядке!!!",true,alf);

            driver.navigate().back();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
