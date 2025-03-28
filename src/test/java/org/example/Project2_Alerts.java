package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class Project2_Alerts {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void testSimpleAlert() {
        WebElement simpleAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        simpleAlertButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "I am a JS Alert", "Alert text does not match");
        alert.accept();
    }

    @Test
    public void testConfirmAlert() {
        WebElement confirmAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        confirmAlertButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "I am a JS Confirm", "Alert text does not match");
        alert.accept();
    }

    @Test
    public void testPromptAlert() {
        WebElement promptAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        promptAlertButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals(alert.getText(), "I am a JS prompt", "Alert text does not match");

        alert.sendKeys("Test input");
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
