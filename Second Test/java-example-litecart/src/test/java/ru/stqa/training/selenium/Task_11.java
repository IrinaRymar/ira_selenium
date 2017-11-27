package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Task_11 {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,120);
    }

    public String createRandomString() {
        String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int STR_LENGTH = 5; // длина генерируемой строки
        Random random = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < STR_LENGTH; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    @Test
    public void mySecondTest(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:10000/litecart/en/");
        driver.findElement(By.xpath("//td/a")).click();
        driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys("Karimova");
        driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys("Elina");
        driver.findElement(By.xpath("//*[@name='address1']")).sendKeys("14 Broadway");
        driver.findElement(By.xpath("//*[@name='postcode']")).sendKeys("55555");
        driver.findElement(By.xpath("//*[@name='city']")).sendKeys("Baltimore");

        WebElement country = driver.findElement(By.xpath("//select[@name='country_code']"));
        //String name = country.getAttribute("textContent"); //для проверки
        Select select_country = new Select(country);
        select_country.selectByVisibleText("United States");

        WebElement state = driver.findElement(By.xpath("//tr[5]/td[2]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.opacity=1",state);

        WebElement state1 = driver.findElement(By.xpath("//select[@name='zone_code']"));
        jse.executeScript("arguments[0].style.opacity=1",state1);
        String name = state1.getAttribute("textContent"); //для проверки
        Select select_zone = new Select(state1);
        select_zone.selectByIndex(6);

        String email = createRandomString()+"@"+createRandomString();
        driver.findElement(By.xpath("//*[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//*[@name='phone']")).sendKeys("12312323");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("KaRee92");
        driver.findElement(By.xpath("//*[@name='confirmed_password']")).sendKeys("KaRee92");

        driver.findElement(By.xpath("//*[@name='create_account']")).click();

        driver.findElement(By.xpath("(//ul[@class='list-vertical']/li[4]/a)[1]")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("KaRee92");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        driver.findElement(By.xpath("(//ul[@class='list-vertical']/li[4]/a)[1]")).click();

    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
