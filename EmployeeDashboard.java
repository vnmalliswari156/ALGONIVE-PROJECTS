package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeDashboard extends JFrame {

    JButton btnViewShifts, btnNotifications, btnLogout;

    public EmployeeDashboard() {
        setTitle("Employee Dashboard");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ✅ Gradient Background Panel
        JPanel bg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(46, 134, 222),     // Blue
                        0, getHeight(), new Color(52, 231, 228)  // Greenish Blue
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(null);

        // ✅ Title
        JLabel title = new JLabel("Employee Dashboard");
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 30, 400, 40);
        bg.add(title);

        // ✅ Buttons with Icons
        btnViewShifts = new JButton(" View Shifts", new ImageIcon("src/shiftmanager/icons/view.png"));
        btnNotifications = new JButton(" Notifications", new ImageIcon("src/shiftmanager/icons/notify.png"));
        btnLogout = new JButton(" Logout", new ImageIcon("src/shiftmanager/icons/logout.png"));

        styleButton(btnViewShifts);
        styleButton(btnNotifications);
        styleButton(btnLogout);

        btnViewShifts.setBounds(230, 120, 240, 45);
        btnNotifications.setBounds(230, 190, 240, 45);
        btnLogout.setBounds(230, 260, 240, 45);

        // ✅ Button Actions
        btnViewShifts.addActionListener(e -> new ViewShiftForm().setVisible(true));
        btnNotifications.addActionListener(e -> new NotificationForm().setVisible(true));
        btnLogout.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });

        bg.add(btnViewShifts);
        bg.add(btnNotifications);
        bg.add(btnLogout);

        add(bg);
    }

    // ✅ Style Buttons (transparent glossy button effect)
    private void styleButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.DARK_GRAY);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(230, 230, 230));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }
        });
    }
}
