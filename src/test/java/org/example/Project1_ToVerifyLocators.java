package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.LocatorsHighlight.highlight;


public class Project1_ToVerifyLocators {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://frontend-git-main-swami-ayyappas-projects.vercel.app/"); // Replace with the deployed React app URL
        System.out.println("Browser setup completed and navigated to the Student Extracurricular Activities Management page\n");
    }

    @Test
    public void testLogoLocator() {
        WebElement logo = driver.findElement(By.cssSelector("img[alt='Logo']"));
        highlight(driver,driver.findElement(By.cssSelector("img[alt='Logo']")));
        System.out.println("Found logo with source: " + logo.getAttribute("src") + "\n");

    }

    @Test
    public void testHeroSectionHeading() {
        WebElement heading = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section/div/h1"));
        highlight(driver,driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/section/div/h1")));
        System.out.println("Found hero section heading: " + heading.getText() + "\n");
    }

    @Test
    public void testStudentLoginButton() {
        WebElement studentLoginButton = driver.findElement(By.cssSelector("a[href='/user-login']"));
        highlight(driver,driver.findElement(By.cssSelector("a[href='/user-login']")));
        System.out.println("Found Student Login button with text: " + studentLoginButton.getText() + "\n");
    }

    @Test
    public void testAdminLoginButton() {
        WebElement adminLoginButton = driver.findElement(By.cssSelector("a[href='/admin-login']"));
        highlight(driver,driver.findElement(By.cssSelector("a[href='/admin-login']")));
        System.out.println("Found Admin Login button with text: " + adminLoginButton.getText() + "\n");
    }

    @Test
    public void testNavLinks() {
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        highlight(driver,driver.findElement(By.linkText("Home")));
        System.out.println("Found navigation link: " + homeLink.getText() + "\n");

        WebElement loginDropdown = driver.findElement(By.cssSelector(".dropdown > a.nav-link"));
        highlight(driver,driver.findElement(By.cssSelector(".dropdown > a.nav-link")));
        System.out.println("Found navigation dropdown link: " + loginDropdown.getText() + "\n");

    }


}
