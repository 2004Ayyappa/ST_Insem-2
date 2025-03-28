package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsTest {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver path
        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to a webpage with alerts
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Test simple alert
        WebElement simpleAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        simpleAlertButton.click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Simple Alert text: " + alert.getText());

        // Test confirmation alert
        WebElement confirmAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        confirmAlertButton.click();
        alert = driver.switchTo().alert();
        System.out.println("Confirm Alert text: " + alert.getText());

        confirmAlertButton.click();



        // Test prompt alert
        WebElement promptAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        promptAlertButton.click();
        alert = driver.switchTo().alert();
        System.out.println("Prompt Alert text: " + alert.getText());
        alert.sendKeys("Test input");


        // Close the browser
        driver.quit();
    }
}