package Insem2MiniProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class MenuDrivenProgram {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("a) Open the browser");
            System.out.println("b) Open the given URL");
            System.out.println("c) Open GitHub URL and validate credentials");
            System.out.println("d) Open GitHub URL and handle invalid credentials");
            System.out.println("e) Implement all locators (static)");
            System.out.println("f) Implement CSS Selector (dynamic)");
            System.out.println("g) Implement XPath (dynamic)");
            System.out.println("h) Develop HTML controls (checkbox, radio, dropdowns)");
            System.out.println("i) Implement alerts (simple, confirm, prompt)");
            System.out.println("j) Handle window and take a screenshot after GitHub push");
            System.out.println("k) Exit");
System.out.println("Enter your choice from a to k: ");
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "a":
                    driver.manage().window().maximize();
                    System.out.println("Browser opened.");
                    break;

                case "b":
                    System.out.print("Enter URL: ");
                    String url = "https://github.com/login";
                    driver.get(url);
                    System.out.println("Opened URL: " + url);
                    break;

                case "c":
                    System.out.print("Enter GitHub username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter GitHub password: ");
                    String password = scanner.nextLine();
                    boolean valid = validateGitHubLogin(username, password);
                    System.out.println("Login valid: " + valid);
                    break;

                case "d":
                    System.out.print("Enter GitHub username: ");
                    String invalidUsername = scanner.nextLine();
                    System.out.print("Enter GitHub password: ");
                    String invalidPassword = scanner.nextLine();
                    handleInvalidCredentials("https://github.com/login", invalidUsername, invalidPassword);
                    break;

                case "e":
                    handleStaticLocators();
                    break;

                case "f":
                    handleDynamicCssSelector();
                    break;

                case "g":
                    handleDynamicXPath();
                    break;

                case "h":
                    handleHtmlControls();
                    break;

                case "i":
                    handleAlerts();
                    break;

                case "j":
                    handleWindowAndScreenshot();
                    break;

                case "k":
                    System.out.println("Exiting the program.");
                    driver.quit();
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static boolean validateGitHubLogin(String username, String password) {

        try {

            driver.get("https://github.com/login");
            driver.findElement(By.id("login_field")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.name("commit")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("github.com"),
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.flash-error"))
            ));

            if (driver.getCurrentUrl().equals("https://github.com/") || driver.getPageSource().contains("Sign out")) {
                System.out.println("login success");
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error validating GitHub login: " + e.getMessage());
        }
        return false;

    }

    public static void handleInvalidCredentials(String url, String username, String password) {
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Check if user is already logged in by checking the URL or presence of logout button
            if (driver.getCurrentUrl().equals("https://github.com/") || driver.getPageSource().contains("Sign out")) {
                System.out.println("Already logged in. Logout first to test invalid credentials.");
                return;
            }

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));

            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            signInButton.click();

            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.flash-error"))
            );

            System.out.println("Invalid credentials. Error message: " + errorMsg.getText());

        } catch (TimeoutException e) {
            System.out.println("Timeout: No error message appeared or elements not found in time.");
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error handling invalid credentials: " + e.getMessage());
        }
    }


    public static void handleStaticLocators() {
        try {
            // Example for static locators (adjust for your needs)
            driver.get("https://www.amazon.in");
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("Laptop");
            driver.findElement(By.id("nav-search-submit-button")).click();
            System.out.println("Static locators handled.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void handleDynamicCssSelector() {
        try {
            // Example for dynamic CSS selector
            driver.get("https://www.amazon.in");
            WebElement deals = driver.findElement(By.cssSelector("a[data-csa-c-slot-id='nav_cs_0']"));
            deals.click();
            System.out.println("Dynamic CSS Selector handled.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void handleDynamicXPath() {
        try {
            driver.get("https://www.amazon.in");
            WebElement mobiles = driver.findElement(By.xpath("//a[contains(text(),'Mobiles')]"));
            mobiles.click();
            System.out.println("Dynamic XPath handled.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void handleHtmlControls() {
        try {
            // Open local HTML file with form elements or a test site like https://demoqa.com/automation-practice-form
            driver.get("https://demoqa.com/automation-practice-form");
            System.out.println("HTML controls opened (manual inspection recommended).");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void handleAlerts() {
        try {
            driver.get("https://demoqa.com/alerts");

            driver.findElement(By.id("alertButton")).click();
            Alert alert = driver.switchTo().alert();
            System.out.println("Simple Alert Text: " + alert.getText());
            alert.accept();

            driver.findElement(By.id("confirmButton")).click();
            Alert confirm = driver.switchTo().alert();
            System.out.println("Confirm Alert Text: " + confirm.getText());
            confirm.dismiss();

            driver.findElement(By.id("promtButton")).click();
            Alert prompt = driver.switchTo().alert();
            prompt.sendKeys("Hello!");
            prompt.accept();

            System.out.println("Alerts handled.");
        } catch (Exception e) {
            System.out.println("Error handling alerts: " + e.getMessage());
        }
    }

    public static void handleWindowAndScreenshot() {
        try {
            driver.get("https://github.com/2004Ayyappa/ST_Insem-2");
            takeScreenshot("GitHub_Screenshot");
            System.out.println("Screenshot taken and saved.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void takeScreenshot(String fileName) {
        try {
            File screenshotsDir = new File("./screenshots/");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotsDir, fileName + ".png");
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
