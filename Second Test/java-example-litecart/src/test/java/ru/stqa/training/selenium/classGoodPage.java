package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class classGoodPage extends Page{

    public classGoodPage(WebDriver driver) {super(driver);}

    public boolean isSizePresent() {return driver.findElements(By.xpath("//td[@class='options']")).size()>0;}

    public void selectSize(String size_duck) {
        WebElement size = driver.findElement(By.xpath("//select[@name='options[Size]']"));
        Select select_size = new Select(size);
        select_size.selectByValue(size_duck);}

    public WebElement addDuck() {return driver.findElement(By.xpath("//button[@name='add_cart_product']"));}

    public void checkQuantity(int i) {
        WebElement quantity = driver.findElement(By.xpath("//span[@class='quantity']"));
        String value = String.valueOf(i);
        wait.until(textToBe(By.xpath("//span[@class='quantity']"),value));
    }


}
