package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class Task_10_ff {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,120);
    }

    public boolean isColorGrey(String str)
    {
        boolean a = false;
        String s = str.replaceAll("[ rgba()]","");
        int[] numArr = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
        if ((numArr[0]==numArr[1]) && (numArr[1]==numArr[2]) && (numArr[0]==numArr[2])) { a = true;}
        return a;
    }

    public boolean isColorRed(String str)
    {
        boolean a = false;
        String s = str.replaceAll("[ rgba()]","");
        int[] numArr = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
        if ((numArr[1]==0) && (numArr[2]==0)) { a = true;}
        return a;
    }

    public int priceToInt(String str)
    {
        String s = str.replaceAll("[ $]","");
        Integer i = Integer.valueOf(s);
        return i;
    }


    @Test
    public void myTask_10_ff() {
        driver.get("http://localhost:10000/litecart/en/");
        //Получаем значения на главной странице
        String name1 = driver.findElement(By.xpath("//div[@id='box-campaigns']//div[@class='name']")).getAttribute("textContent");
        WebElement regular_price1 = driver.findElement(By.xpath("//div[@id='box-campaigns']//*[@class='regular-price']"));
        String regular_price_value1 = regular_price1.getAttribute("textContent");
        String regular_price_decor1 = regular_price1.getCssValue("text-decoration-line");
        String regular_price_color1 = regular_price1.getCssValue("color");

        WebElement campaign_price1 = driver.findElement(By.xpath("//div[@id='box-campaigns']//*[@class='campaign-price']"));
        String campaign_price_value1 = campaign_price1.getAttribute("textContent");
        String campaign_price_decor1 = campaign_price1.getCssValue("font-weight");
        String campaign_price_color1 = campaign_price1.getCssValue("color");

        driver.findElement(By.xpath("//div[@id='box-campaigns']//div[@class='name']")).click();
        //Получаем значение на странице товара
        String name2 = driver.findElement(By.xpath("//div[@id='box-product']//*[@class='title']")).getAttribute("textContent");
        WebElement regular_price2 = driver.findElement(By.xpath("//div[@class='information']//*[@class='regular-price']"));
        String regular_price_value2 = regular_price2.getAttribute("textContent");
        String regular_price_decor2 = regular_price2.getCssValue("text-decoration-line");
        String regular_price_color2 = regular_price2.getCssValue("color");

        WebElement campaign_price2 =driver.findElement(By.xpath("//div[@class='information']//*[@class='campaign-price']"));
        String campaign_price_value2 = campaign_price2.getAttribute("textContent");
        String campaign_price_decor2 = campaign_price2.getCssValue("font-weight");
        String campaign_price_color2 = campaign_price2.getCssValue("color");


        //Проверяем совпадает ли текст товара на страницах
        Assert.assertEquals("Не совпадает текст товара!!!",name1,name2);
        //Проверяем совпадает ли обычная цена товара на страницах
        Assert.assertEquals("Не совпадает обычная цена товара!!!",regular_price_value1,regular_price_value2);
        //Проверяем сопадает ли акционная цена товара на страницах
        Assert.assertEquals("Не совпадает аукционная цена товара!!!",campaign_price_value1,campaign_price_value2);
        //Проверяем, что обычная цена зачеркнута  на обеих страницах
        Assert.assertEquals("Обычная цена не зачеркнута!!!",regular_price_decor1,"line-through");
        Assert.assertEquals("Обычная цена не зачеркнута!!!",regular_price_decor2,"line-through");
        //Проверяем, что акционная цена жирная на обеих страницах
        Assert.assertTrue("Акционная цена не жирная!!!",campaign_price_decor1.equals("900"));
        Assert.assertTrue("Акционная цена не жирная!!!",campaign_price_decor2.equals("700"));
        //Проверяем, что обычная цена  cерая на обеих страницах
        Assert.assertTrue("Обычная цена не серая на главной странице!!!",isColorGrey(regular_price_color1));
        Assert.assertTrue("Обычная цена не серая на странице товара!!!",isColorGrey(regular_price_color2));
        //Проверяем, что аукционная цена красная на обеих страницах
        Assert.assertTrue("Акционная цена не красная на главной странице!!!",isColorRed(campaign_price_color1));
        Assert.assertTrue("Акционная цена не красная на главной странице!!!",isColorRed(campaign_price_color2));
        //Проверяем, что аукционная цена< обычной на обеих страницах
        Assert.assertTrue("Аукционная цена больше обычной на главной странице!!!",
                priceToInt(campaign_price_value1)<priceToInt(regular_price_value1));
        Assert.assertTrue("Аукционная цена больше обычной на странице товара!!!",
                priceToInt(campaign_price_value2)<priceToInt(regular_price_value2));



    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
