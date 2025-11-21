package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class NotificationForm {

    private JPanel panel;
    private JTable table;

    public NotificationForm() {

        // ===== FULLSCREEN BACKGROUND PANEL =====
        panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image bg = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception ex) {
                    System.out.println("Background image missing");
                }
            }
        };

        // ===== NOTIFICATION ICON =====
        JLabel iconLabel = new JLabel();
        try {
            Image img = new ImageIcon("src/resource/notification.png").getImage();
            img = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Icon missing: notification.png");
        }

        iconLabel.setBounds(600, 40, 100, 100);
        panel.add(iconLabel);

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("VIEW NOTIFICATIONS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(450, 140, 400, 50);
        panel.add(lblTitle);

        // ===== TABLE FOR NOTIFICATIONS =====
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(250, 220, 900, 350); // centered and large
        panel.add(sp);

        loadNotifications();
    }

    // ===== LOAD DATA FROM DATABASE =====
    private void loadNotifications() {
        try (Connection conn = ShiftDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT message AS 'Message', created_at AS 'Date' FROM notifications ORDER BY id DESC")) {

            ResultSet rs = stmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading notifications");
        }
    }

    // ===== THIS MAKES IT WORK INSIDE MAINCONTAINER =====
    public JPanel getContentPanel() {
        return panel;
    }
}
