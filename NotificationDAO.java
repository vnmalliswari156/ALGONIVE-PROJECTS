package shiftmanager;

import java.sql.*;
import java.util.ArrayList;

public class NotificationDAO {

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shiftmanager",
                "root",
                "root"
        );
    }

    // ✅ Save Notification
    public static boolean addNotification(String title, String message) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO notifications(title, message) VALUES(?, ?)"
             )) {

            ps.setString(1, title);
            ps.setString(2, message);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // ✅ Load Notifications
    public static ArrayList<String> getNotifications() {
        ArrayList<String> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT title, message, date FROM notifications ORDER BY id DESC"
             );
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String formatted = "🟣 Title: " + rs.getString("title")
                        + "\n📌 Message: " + rs.getString("message")
                        + "\n📅 Date: " + rs.getString("date")
                        + "\n------------------------\n";
                list.add(formatted);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}

