package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class LinkText {
    public static void main(String[] args) throws IOException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://amazon.com");
        WebElement linktext = driver.findElement(By.xpath("//a[text()=\"Today's Deals\"]"));
        WebElement partiallinktext=driver.findElement(By.xpath("//a[contains(text(), 'Deals')]"));
    }
}