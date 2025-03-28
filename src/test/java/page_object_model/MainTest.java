package page_object_model;


import org.testng.annotations.Test;
import page_object_model.pages.LoginPage;

import java.sql.SQLException;

import static org.testng.Assert.assertTrue;

public class MainTest extends BaseTest {

    @Test
    public void testGitHubLoginWithDB() throws SQLException {
        String[] credentials = DatabaseUtil.getUserCredentials("ayyappa");
        String username = credentials[0];
        String password = credentials[1];

        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        assertTrue(loginPage.isLoginSuccess(), "Login failed!");
    }
}
