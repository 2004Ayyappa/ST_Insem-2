package page_object_model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("login_field");
    private By passwordField = By.id("password");
    private By loginButton = By.name("commit");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public void goToLoginPage() {
        driver.get("https://github.com/login");
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccess() {
        return driver.getTitle().contains("GitHub");
    }
}
