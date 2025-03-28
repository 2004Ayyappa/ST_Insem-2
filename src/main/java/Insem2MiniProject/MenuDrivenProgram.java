package Insem2MiniProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class MenuDrivenProgram {

    static WebDriver driver;
    static String githubUrl = "https://github.com/login";  // GitHub Login URL
    static String amazonUrl = "https://www.amazon.in/";    // Amazon URL

    public static void main(String[] args) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:\n1. GitHub Login\n2. Static Locators (Amazon)\n3. Dynamic Locators (Amazon)\n4. HTML Controls\n5. Alerts\n6. Window & Screenshot\n7. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    driver.get(githubUrl);
                    if (validateGitHubLogin("2200032199@kluniversity.in", "2004@Ayyappa")) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 2:
                    driver.get(amazonUrl);
                    handleStaticLocatorsAmazon();
                    break;
                case 3:
                    driver.get(amazonUrl);
                    handleDynamicLocators();
                    break;
                case 4:
                    handleHtmlControls();
                    break;
                case 5:
                    handleAlerts();
                    break;
                case 6:
                    handleWindowsAndScreenshot();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    driver.quit();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ✅ GitHub Login Implementation
    public static boolean validateGitHubLogin(String username, String password) {
        try {
            driver.findElement(By.id("login_field")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.name("commit")).submit();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.urlContains("github.com"));
            return driver.getCurrentUrl().contains("github.com");
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    // ✅ Static Locators (Amazon Elements)
    public static void handleStaticLocatorsAmazon() {
        try {
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox")); // ✅ Static Locator (Search Box)
            searchBox.sendKeys("Laptop");

            WebElement searchButton = driver.findElement(By.id("nav-search-submit-button")); // ✅ Static Locator (Search Button)
            searchButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement amazonLogo = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-logo-sprites")));
            amazonLogo.click();

            System.out.println("Static locators on Amazon executed successfully.");
        } catch (Exception e) {
            System.out.println("Static locators error: " + e.getMessage());
        }
    }

    // 🚀 Dynamic Locators (Amazon Elements)
    public static void handleDynamicLocators() {
        try {
            WebElement dynamicCss = driver.findElement(By.cssSelector("div[id^='nav-cart']")); // ✅ Dynamic Locator (Cart)
            dynamicCss.click();

            WebElement dynamicXpath = driver.findElement(By.xpath("//div[contains(@id, 'nav-cart')]")); // ✅ Dynamic Locator
            dynamicXpath.click();

            System.out.println("Dynamic locators executed successfully.");
        } catch (Exception e) {
            System.out.println("Dynamic locators error: " + e.getMessage());
        }
    }

    // ✅ Handling HTML Controls on Flipkart
    public static void handleHtmlControls() {
        try {
            driver.get("https://www.flipkart.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // ✅ Close login popup if it appears
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'✕')]")));
                closeBtn.click();
                System.out.println("Login popup closed.");
            } catch (Exception e) {
                System.out.println("Login popup not displayed.");
            }

            // ✅ Step 1: Search for a product (Laptop)
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
            searchBox.sendKeys("Laptop");
            searchBox.sendKeys(Keys.RETURN);

            // ✅ Step 2: Wait for search results to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._1YokD2 ._1AtVbE")));

            // ✅ Step 3: Scroll and Select a Brand Checkbox (e.g., HP)
            WebElement brandCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='HP']")));

            // Scroll into view before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandCheckbox);
            brandCheckbox.click();
            System.out.println("Selected HP brand filter.");

            // ✅ Step 4: Click on the First Product
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("._1fQZEK")));

            // Scroll into view before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            firstProduct.click();

            // ✅ Step 5: Switch to New Tab and Add to Cart
            for (String tab : driver.getWindowHandles()) {
                driver.switchTo().window(tab);
            }

            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to Cart']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
            System.out.println("Added product to cart successfully.");

        } catch (Exception e) {
            System.out.println("Error handling HTML controls on Flipkart: " + e.getMessage());
        }
    }

    // ✅ Handling Alerts
    public static void handleAlerts() {
        try {
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
            Alert alert = driver.switchTo().alert();
            System.out.println("Simple Alert: " + alert.getText());
            alert.accept();

            System.out.println("All alerts handled successfully.");
        } catch (Exception e) {
            System.out.println("Error handling alerts: " + e.getMessage());
        }
    }

    // ✅ Handling Windows & Screenshot
    public static void handleWindowsAndScreenshot() {
        try {
            takeScreenshot("amazon_home");
            System.out.println("Screenshot captured successfully.");
        } catch (Exception e) {
            System.out.println("Error in screenshot handling: " + e.getMessage());
        }
    }

    // ✅ Screenshot Method (Fixed Directory Issue)
    public static void takeScreenshot(String fileName) {
        try {
            driver.get("")
            File screenshotsDir = new File("./screenshots/");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotsDir, fileName + ".png");
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
