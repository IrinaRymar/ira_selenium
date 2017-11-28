package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.classMainPage;
import ru.stqa.training.selenium.classGoodPage;
import ru.stqa.training.selenium.classCartPage;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Application{

    private WebDriver driver;
    private WebDriverWait wait;
    private classMainPage _classMainPage;
    private classCartPage _classCartPage;
    private classGoodPage _classGoodPage;


    public Application() {
        driver = new ChromeDriver();
        _classMainPage = new classMainPage(driver);
        _classCartPage = new classCartPage(driver);
        _classGoodPage = new classGoodPage(driver);

        wait = new WebDriverWait(driver, 40);
    }

    public void quit()
    {        driver.quit();    }


    public void MainPage(){
        _classMainPage.open();
        _classMainPage.ChooseTheDuck().click();
    }

    public void GoodPage(int i)
    {
        if(_classGoodPage.isSizePresent()){
            _classGoodPage.selectSize("Small");
            }
        _classGoodPage.addDuck().click();
        _classGoodPage.checkQuantity(i);
    }

    public void CartPage()
    {
        _classCartPage.CartReview().click();
        _classCartPage.DeleteAllGoods();
    }



}

