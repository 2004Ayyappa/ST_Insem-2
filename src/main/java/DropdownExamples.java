package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownExamples {
    public static void main(String[] args) throws Exception {

        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
        Thread.sleep(2000);
        //Store this web element instance into course
//SINGLE SELECTION DROP DOWN
        WebElement courseElement = driver.findElement(By.id("course"));
        Select courseNameDropdown = new Select(courseElement);
        List<WebElement> courseNameDropDownOptions = courseNameDropdown.getOptions();
        for (WebElement option : courseNameDropDownOptions) {
            System.out.println(option.getText());
        }
        courseNameDropdown.selectByIndex(1);
        Thread.sleep(3000);
        courseNameDropdown.selectByVisibleText("Java");
        Thread.sleep(2000);
        courseNameDropdown.selectByValue("python");
        String selectedText = courseNameDropdown.getFirstSelectedOption().getText();
        System.out.println("selected visible text" + selectedText);
//MULTIPLE SELECTION DROP DOWN EXAMPLE
        //WebElement ideElement = driver.findElement(By.id("ide"));
        // Select ideDropDown = new Select(ideElement);
        // List<WebElement> ideDropDownOptions = ideDropDown.getOptions();
        // for (WebElement option : ideDropDownOptions) {
        //      System.out.println(option.getText());
        // }
        // ideDropDown.selectByIndex(0);
        // ideDropDown.selectByValue("ij");
        // ideDropDown.selectByVisibleText("NetBeans");
        // ideDropDown.deselectByVisibleText("IntelliJ IDEA");
        // List<WebElement> selectedOptions = ideDropDown.getAllSelectedOptions();
        //  for (WebElement selectedOption : selectedOptions) {
        //      System.out.println("selected visible text---" + selectedOption.getText());

        // }

    }
}