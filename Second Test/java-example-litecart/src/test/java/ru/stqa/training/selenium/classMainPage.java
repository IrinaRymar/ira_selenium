package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class classMainPage extends Page {

    public classMainPage(WebDriver driver){super(driver);}

    public void open() {driver.get("http://localhost:10000/litecart/en/");}
    public WebElement ChooseTheDuck() {return driver.findElement(By.xpath("//div[@class='image-wrapper']")); }

}
