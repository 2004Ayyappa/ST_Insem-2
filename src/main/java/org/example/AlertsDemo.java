package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsDemo {
    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/alertsdemo.html");
        System.out.println(driver.findElement(By.id("output")).getText());
        Thread.sleep(2000L);
        driver.findElement(By.id("alertBox")).click();
        Thread.sleep(2000L);
        System.out.println(driver.switchTo().alert().getText());
        Thread.sleep(2000L);
        driver.switchTo().alert().accept();
        Thread.sleep(2000L);
        System.out.println(driver.findElement(By.id("output")).getText());
    }
}

