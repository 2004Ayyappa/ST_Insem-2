package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class StaticLocator {
    public static void main(String[] args) throws IOException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://github.com/login");
driver.findElement(By.id("login_field")).sendKeys("2200032199@kluniversity.in");
driver.findElement(By.id("password")).sendKeys("123");
driver.findElement(By.name("commit")).submit();
        TakesScreenshot ts=(TakesScreenshot)driver;
        File file=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("./Screenshots/Image2.png"));

    }

}
