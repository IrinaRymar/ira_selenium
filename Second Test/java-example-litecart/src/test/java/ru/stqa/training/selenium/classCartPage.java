package ru.stqa.training.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class classCartPage extends Page {

    public classCartPage(WebDriver driver) {super(driver);}

    public WebElement CartReview() {return driver.findElement(By.xpath("//a[@class='link']"));}

    public void DeleteAllGoods() {
        List<WebElement> duck = driver.findElements(By.xpath("//td[@class='item']"));
        for (int i = 0; i < duck.size(); i++) {
            WebElement el = driver.findElement(By.xpath("//div[@id='box-checkout-summary']"));
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(el));
        }
    }
}
