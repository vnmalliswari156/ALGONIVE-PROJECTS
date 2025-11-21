package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPanel extends JPanel {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private MainContainer parent; // reference to MainContainer

    public LoginPanel(MainContainer parent) {
        this.parent = parent;
        setLayout(null);

        // Background image
        JLabel background;
        try {
            ImageIcon bgIcon = new ImageIcon("download.jpeg"); // project root
            Image img = bgIcon.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            background = new JLabel();
            background.setOpaque(true);
            background.setBackground(new Color(240, 220, 240));
        }
        background.setBounds(0, 0, 900, 600);
        background.setLayout(null);
        add(background);

        // Title
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(380, 50, 200, 50);
        background.add(lblTitle);

        // Email
        JLabel lblUser = new JLabel("Email:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(300, 150, 100, 30);
        background.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(400, 150, 250, 30);
        background.add(txtUser);

        // Password
        JLabel lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(300, 220, 100, 30);
        background.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(400, 220, 250, 30);
        background.add(txtPass);

        // Login button with icon
        JButton btnLogin;
        try {
            ImageIcon icon = new ImageIcon("login.png"); // project root
            Image iconImg = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            btnLogin = new JButton("Login", new ImageIcon(iconImg));
        } catch (Exception e) {
            btnLogin = new JButton("Login");
        }
        btnLogin.setBounds(400, 300, 250, 40);
        background.add(btnLogin);

        // Login action
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = String.valueOf(txtPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Email and Password!");
                return;
            }

            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(
                         "SELECT * FROM users WHERE BINARY email=? AND BINARY password=?")) {

                pst.setString(1, user);
                pst.setString(2, pass);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful! Welcome " + rs.getString("name"));
                    parent.showDashboard(rs.getString("name")); // switch to dashboard
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Email or Password!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        });
    }
}


    