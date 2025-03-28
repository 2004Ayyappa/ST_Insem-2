package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class LocatorsHighlight {
    public static void main(String[] args) {

        WebDriver driver;
        System.out.println("welcome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.github.com/login");
        System.out.println(driver.getTitle());
        driver.findElement(By.id("login_field")).sendKeys("ancd@kluniversity.in");
        highlight(driver,driver.findElement(By.id("login_field")));
        driver.findElement(By.name("password")).sendKeys("welcome");
        highlight(driver,driver.findElement(By.name("password")));
        driver.findElement(By.className("header-logo"));
        highlight(driver,driver.findElement(By.className("header-logo")));
        //driver.findElement(By.linkText("Forgot password?")).click();
        //highlight(driver,driver.findElement(By.linkText("Forgot password?")));
        driver.findElement(By.partialLinkText("Create an account")).click();
        driver.findElement(By.tagName("h1"));
        highlight(driver,driver.findElement(By.tagName("h1")));
    }
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}