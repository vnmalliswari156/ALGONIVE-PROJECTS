package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainForm extends JFrame {

    private JButton btnAddShift, btnViewShift, btnLogout;

    public MainForm() {
        setTitle("Shift Manager Dashboard");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(148, 0, 211));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        CardPanel card = new CardPanel();
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(450, 400));
        card.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Load dashboard icon safely
        JLabel iconLabel = new JLabel();
        URL iconURL = getClass().getResource("/icons/dashboard.png");
        if(iconURL != null){
            ImageIcon icon = new ImageIcon(iconURL);
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        }
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        card.add(iconLabel, c);

        // Title
        JLabel titleLabel = new JLabel("Shift Manager Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridy = 1;
        card.add(titleLabel, c);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        // Add Shift button
        c.gridy = 2;
        c.gridwidth = 2;
        btnAddShift = new JButton("Add Shift");
        btnAddShift.setFont(buttonFont);
        btnAddShift.setPreferredSize(new Dimension(200, 50));
        card.add(btnAddShift, c);

        // View Shifts button
        c.gridy = 3;
        btnViewShift = new JButton("View Shifts");
        btnViewShift.setFont(buttonFont);
        btnViewShift.setPreferredSize(new Dimension(200, 50));
        card.add(btnViewShift, c);

        // Logout button
        c.gridy = 4;
        btnLogout = new JButton("Logout");
        btnLogout.setFont(buttonFont);
        btnLogout.setPreferredSize(new Dimension(200, 50));
        card.add(btnLogout, c);

        add(card);

        // Button actions
        btnAddShift.addActionListener(e -> new AddShiftForm().setVisible(true));
        btnViewShift.addActionListener(e -> new ViewShiftForm().setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
