package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.sql.*;

public class Database {
    public static void main(String[] args) throws SQLException {
        String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/st", "root", "admin123");
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("select * from gitcredentials where name='ayyappa'");
        while (rs.next()) {
            WebDriver driver;
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
            driver.get("https://www.github.com/login");
            driver.findElement(By.id("login_field")).sendKeys(rs.getString("username"));
            driver.findElement(By.id("password")).sendKeys(rs.getString("password"));
            driver.findElement(By.name("commit")).click();
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("password"));


        }
    }

}