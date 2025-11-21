package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(String userName) {

        setTitle("Dashboard - " + userName);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Panel
        JPanel bgPanel = new JPanel() {
            Image bg = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/dashboard - Copy.jpeg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // ✅ Using GridBagLayout to center perfectly
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // Icon
        ImageIcon icon = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/Dashboard.png");
        Image scaledIcon = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(scaledIcon));

        // ✅ Updated Stylish Font
        JLabel lblWelcome = new JLabel("Welcome to Employee Shift Management " + userName + "!");
        lblWelcome.setFont(new Font("Segoe UI Black", Font.BOLD, 32)); // ✅ Stylish Font
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components vertically centered
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(lblIcon);
        centerPanel.add(Box.createVerticalStrut(20)); // gap
        centerPanel.add(lblWelcome);

        bgPanel.add(centerPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard(" ").setVisible(true);
    }

    public Component getContentPanel() {
        return null;
    }
}
