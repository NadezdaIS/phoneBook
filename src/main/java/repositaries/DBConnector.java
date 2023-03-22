package repositaries;
import java.sql.*;
import java.sql.Connection;

public class DBConnector {
    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bankdb", "root", "Planas4!");
            //System.out.println("Connected to the DB");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS contacts (id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), phone VARCHAR(20), email VARCHAR(255))");
            return conn;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() {
    }
}

