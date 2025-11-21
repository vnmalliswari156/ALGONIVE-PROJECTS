package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(String userName) {
        setLayout(null);

        // Background image
        JLabel background;
        try {
            ImageIcon bgIcon = new ImageIcon("dashboard.jpeg"); // project root
            Image img = bgIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            background = new JLabel();
            background.setOpaque(true);
            background.setBackground(Color.LIGHT_GRAY);
        }
        background.setBounds(0, 0, 900, 600);
        background.setLayout(null);
        add(background);

        // Welcome label
        JLabel lblWelcome = new JLabel("Welcome, " + userName + "!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(250, 250, 400, 50);
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(lblWelcome);
    }
}

