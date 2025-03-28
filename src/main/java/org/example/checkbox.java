package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class checkbox {

        public static void main(String[] args) {
            WebDriver driver;
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(" https://the-internet.herokuapp.com/checkboxes");
            driver.manage().window().maximize();
            WebElement checkBoxSelected = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
            boolean isSelected = checkBoxSelected.isSelected();
            // performing click operation if element is not selected
            if(isSelected == false) {
                checkBoxSelected.click();
            }


            WebElement checkBoxDisplayed = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
            boolean isDisplayed = checkBoxDisplayed.isDisplayed();
            // performing click operation if element is displayed
            if (isDisplayed == true) {
                checkBoxDisplayed.click();
            }
            /**
             * Validate isEnabled and click
             */
            WebElement checkBoxEnabled = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
            boolean isEnabled = checkBoxEnabled.isEnabled();

            // performing click operation if element is enabled
            if (isEnabled == true) {
                checkBoxEnabled.click();
            }

        }

    }

