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
import java.util.List;
import java.util.Collections;

public class Task_9_1 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    boolean isListsort(WebDriver driver,By locator)
    {
        List<WebElement> list_countries = driver.findElements(locator);

        List<String> myList = new ArrayList<>();
        List<String> myList_sort = new ArrayList<>();
        for(int i=0; i<list_countries.size(); i++)
        {
            myList.add(i,list_countries.get(i).getText());
            myList_sort.add(i,list_countries.get(i).getText());
        }
        Collections.sort(myList_sort);

        String[] myArray = {}; // конвертируем myList в массив
        myArray = myList.toArray(new String[myList.size()]);

        String[] myArray1 = {}; // конвертируем myList_sort в массив
        myArray1 = myList_sort.toArray(new String[myList_sort.size()]);

        return Arrays.equals(myArray,myArray1);
    }

    boolean isListSortValue(WebDriver driver,By locator)
    {
        List<WebElement> list_countries = driver.findElements(locator);

        List<String> myList = new ArrayList<>();
        List<String> myList_sort = new ArrayList<>();
        for(int i=0; i<list_countries.size(); i++)
        {
            myList.add(i,list_countries.get(i).getAttribute("value"));
            myList_sort.add(i,list_countries.get(i).getAttribute("value"));
        }
        Collections.sort(myList_sort);

        String[] myArray = {}; // конвертируем myList в массив
        myArray = myList.toArray(new String[myList.size()]);

        String[] myArray1 = {}; // конвертируем myList_sort в массив
        myArray1 = myList_sort.toArray(new String[myList_sort.size()]);

        return Arrays.equals(myArray,myArray1);
    }

    @Test
    public void mySecondTest() {

        driver.get("http://localhost:10000/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        boolean alf= isListsort(driver,By.cssSelector("tr.row a:not([title=Edit])"));
        Assert.assertEquals("Не в алфавитном порядке!!!",true,alf);

        /*-------------------------------------------------------------------------------------------------------*/

        List<WebElement> list_countries_with_zone1 = driver.findElements(By.xpath("//tr[@class='row']"));
        List<String> myList_many_zone = new ArrayList<String>();

        for(int i=0; i<list_countries_with_zone1.size(); i++)

        {
            List<WebElement> list_countries_with_zone = driver.findElements(By.xpath("//tr[@class='row']"));
            if (!((list_countries_with_zone.get(i).findElement(By.xpath("./td[6]")).getAttribute("textContent")).equals("0")))
            {
                myList_many_zone.add(list_countries_with_zone.get(i).findElement(By.xpath("./td[5]")).getText());//для проверки
                list_countries_with_zone.get(i).findElement(By.xpath("./td[5]/a")).click();

                boolean alf1= isListSortValue(driver,By.cssSelector("input[type='hidden'][name$='[name]']"));
                Assert.assertEquals("Не в алфавитном порядке!!!",true,alf1);

                driver.navigate().back();

            }
        }




    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
