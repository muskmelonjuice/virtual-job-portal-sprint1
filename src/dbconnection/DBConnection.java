package dbconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "system";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Temporary main method for testing
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}