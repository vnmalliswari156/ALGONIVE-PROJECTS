package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewNotificationForm extends JPanel {

    private JTextArea textArea;
    private Image bgImage;
    private Image iconImage;

    public ViewNotificationForm() {

        setLayout(new BorderLayout());

        try {
            bgImage = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
        } catch (Exception e) {
            System.out.println("⚠ Background image not found");
        }

        try {
            iconImage = new ImageIcon("src/resource/Notifications.jpeg")
                    .getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("⚠ Notification icon not found");
        }

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        add(bgPanel);

        JLabel lblIcon = new JLabel(new ImageIcon(iconImage));
        lblIcon.setBounds(620, 20, 150, 150);
        bgPanel.add(lblIcon);

        JLabel lblTitle = new JLabel("View Notifications");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(580, 170, 400, 50);
        bgPanel.add(lblTitle);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBounds(300, 240, 900, 400);
        bgPanel.add(scroll);

        loadNotifications();
    }

    public void loadNotifications() {
        textArea.setText("");

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT employee, message, created_at FROM notifications ORDER BY id DESC");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String emp = rs.getString("employee");
                String msg = rs.getString("message");
                String time = rs.getString("created_at");

                textArea.append(
                        "👤 Employee: " + (emp == null ? "All" : emp) + "\n" +
                        "📌 Message: " + msg + "\n" +
                        "⏰ Time: " + time + "\n" +
                        "---------------------------------------------------\n"
                );
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("View Notifications");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setContentPane(new ViewNotificationForm());
        f.setVisible(true);
    }
}

