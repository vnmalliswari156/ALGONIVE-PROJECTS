package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Notification extends JFrame {

    public Notification() {
        setTitle("Notifications");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ✅ Background Panel with GridBagLayout
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/dashboard - Copy.jpeg").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(new GridBagLayout()); // ✅ Center everything
        setContentPane(bgPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing

        // ✅ Notification Icon
        ImageIcon icon = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/Notifications.jpeg");
        Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(scaled));

        gbc.gridx = 0;
        gbc.gridy = 0;
        bgPanel.add(lblIcon, gbc);

        // ✅ Title
        JLabel lblTitle = new JLabel("Employee Notifications");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);

        gbc.gridy = 1;
        bgPanel.add(lblTitle, gbc);

        // ✅ Rounded Notification Box
        RoundedPanel roundPanel = new RoundedPanel(25, new Color(255, 182, 193));
        roundPanel.setPreferredSize(new Dimension(450, 170));
        roundPanel.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setOpaque(false);
        area.setEditable(false);
        area.setText(
                "🔵 Shift changed on 2025-02-10\n\n" +
                "🔵 New company holiday added on 2025-02-14\n\n" +
                "🔵 Attendance rules updated\n\n" +
                "🔵 New employee training schedule available\n"
        );

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        roundPanel.add(scroll);

        gbc.gridy = 2;
        bgPanel.add(roundPanel, gbc);

        // ✅ Back Button (center bottom)
        JButton btnBack = new JButton("Back");
        gbc.gridy = 3;
        bgPanel.add(btnBack, gbc);

        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    // ✅ Rounded panel
    class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius;

        public RoundedPanel(int radius, Color bgColor) {
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setColor(backgroundColor);
            g2.fill(round);
        }
    }

    public static void main(String[] args) {
        new Notification();
    }
}
