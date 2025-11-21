package shiftmanager;

import java.sql.*;

public class UserDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/shiftdb", "root", "root");
    }

    // ✅ Register new user
    public boolean register(String name, String email, String password) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    // ✅ Authenticate login
    public String authenticate(String email, String password) {
        String sql = "SELECT name FROM users WHERE email=? AND password=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("name");
            return null;

        } catch (SQLException e) {
            System.out.println("Error authenticating: " + e.getMessage());
            return null;
        }
    }
}
