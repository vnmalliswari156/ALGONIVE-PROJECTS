package shiftmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewNotificationPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    public ViewNotificationPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("🔔 View Notifications", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setPreferredSize(new Dimension(100, 80));

        model = new DefaultTableModel(new String[]{"ID", "Title", "Message", "Date"}, 0);
        table = new JTable(model);
        table.setRowHeight(28);
        JScrollPane sp = new JScrollPane(table);

        add(lblTitle, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);

        loadNotifications();
    }

    public void loadNotifications() {

        model.setRowCount(0);

        try (Connection conn = ShiftDAO.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM notifications ORDER BY id DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("message"),
                        rs.getString("date")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading notifications!");
        }
    }
}

