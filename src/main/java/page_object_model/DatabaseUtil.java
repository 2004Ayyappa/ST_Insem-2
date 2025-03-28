package page_object_model;

import java.sql.*;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/st";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static String[] getUserCredentials(String name) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM gitcredentials WHERE name='" + name + "'");

        if (rs.next()) {
            return new String[]{rs.getString("username"), rs.getString("password")};
        }

        throw new SQLException("User not found!");
    }
}
