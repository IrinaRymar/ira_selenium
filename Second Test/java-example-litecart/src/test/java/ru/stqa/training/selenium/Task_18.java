package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Task_18 {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start(){
        if(tlApp.get() != null){
            app  = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(()->{app.quit(); app = null;}));
    }

    @Test
    public void myTask_18() {
        for (int i=1; i<=3; i++) {
            app.MainPage();
            app.GoodPage(i);
        }
        app.CartPage();
    }
}
