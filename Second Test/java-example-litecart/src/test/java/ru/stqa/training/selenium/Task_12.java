package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
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
        String name = "Motley Duck"+System.currentTimeMillis();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys("RD020");
        driver.findElement(By.xpath("(//input[@name='categories[]'])[1]")).click();
        driver.findElement(By.xpath("(//input[@name='categories[]'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='product_groups[]'])[3]")).click();
        driver.findElement(By.xpath("(//input[@name='quantity'])")).clear();
        driver.findElement(By.xpath("(//input[@name='quantity'])")).sendKeys("1");

        File file = new File("duck.png");
        String path = file.getAbsolutePath();
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(path);

        WebElement calendar1 = driver.findElement(By.xpath("//*[@name='date_valid_from']"));

        new Actions(driver)
          .moveToElement(calendar1)
                .click()
                .sendKeys("30")
                .sendKeys("11")
                .sendKeys("2017")
                .perform();

        WebElement calendar2 = driver.findElement(By.xpath("//*[@name='date_valid_to']"));
        new Actions(driver)
                .moveToElement(calendar2)
                .click()
                .sendKeys("31")
                .sendKeys("12")
                .sendKeys("2017")
                .perform();


        //Переходим к заполнению второй страницы
        driver.findElement(By.xpath("//*[@href='#tab-information']")).click();
        wait.until(textToBe(By.xpath("//div[@id='tab-information']//tr//td//strong"),"Manufacturer"));

        Select select = new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        select.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//*[@name='keywords']")).sendKeys("Duck");
        driver.findElement(By.xpath("//*[@name='short_description[en]']")).sendKeys("Beautiful duck");
        WebElement editor = driver.findElement(By.xpath("//*[@class='trumbowyg-editor']"));
        WebElement bold = driver.findElement(By.xpath("//*[@id='trumbowyg-bold']"));
        new Actions(driver)
                .moveToElement(editor)
                .click()
                .sendKeys("Duck is the best gift for your children.")
                .sendKeys(Keys.ENTER)
                .sendKeys("It' very soft toy.")
                .perform();
        driver.findElement(By.xpath("//*[@name='head_title[en]']")).sendKeys("Duck");
        driver.findElement(By.xpath("//*[@name='meta_description[en]']")).sendKeys("Duck");
        //Переходим к третьей странице
        driver.findElement(By.xpath("//*[@href='#tab-prices']")).click();
        wait.until(textToBe(By.xpath("//div[@id='tab-prices']/h2[1]"),"Prices"));
        WebElement purchase_price = driver.findElement(By.xpath("//input[@name='purchase_price']"));
        purchase_price.clear();
        purchase_price.sendKeys("50");
        Select select2 = new Select(driver.findElement(By.xpath("//select[@name='purchase_price_currency_code']")));
        select2.selectByVisibleText("US Dollars");
        WebElement prices_usd = driver.findElement(By.xpath("//input[@name='prices[USD]']"));
        prices_usd.clear();
        prices_usd.sendKeys("100");
        WebElement prices_eur = driver.findElement(By.xpath("//input[@name='prices[EUR]']"));
        prices_eur.clear();
        prices_eur.sendKeys("90");


        driver.findElement(By.xpath("//button[@name='save']")).click();
        boolean a = false;
        List<WebElement> goods1 = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));
        for(int i=0; i<goods1.size(); i++)
            if ((goods1.get(i).getText()).equals(name)) a=true;
        Assert.assertEquals("Товар не добавился!!!",true,a);




    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
