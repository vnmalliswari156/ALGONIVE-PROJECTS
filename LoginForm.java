package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JPanel {

    private MainContainer parent;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm(MainContainer parent) {
        this.parent = parent;
        initUI();
    }

    private void initUI() {
        setLayout(null);

        // Background image panel
        Image bgImg = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, 1400, 800);
        add(bgPanel);

        // Login icon
        ImageIcon icon = new ImageIcon("src/resource/Login.jpeg");
        JLabel loginIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        loginIcon.setBounds(600, 50, 150, 150);
        bgPanel.add(loginIcon);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
        lblEmail.setBounds(480, 250, 120, 30);
        bgPanel.add(lblEmail);

        usernameField = new JTextField();
        usernameField.setBounds(600, 250, 220, 30);
        bgPanel.add(usernameField);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        lblPass.setBounds(480, 300, 120, 30);
        bgPanel.add(lblPass);

        passwordField = new JPasswordField();
        passwordField.setBounds(600, 300, 220, 30);
        bgPanel.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(540, 370, 180, 42);
        btnLogin.setBackground(new Color(50, 125, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        bgPanel.add(btnLogin);

        // ✅ Login action
        btnLogin.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Email and Password!");
                return;
            }

            try {
                Connection con = DBConnect.getConnection();
                String sql = "SELECT * FROM users WHERE email=? AND password=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, pass);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "✅ Login Successful!");
                    parent.loginSuccess();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Invalid Email or Password!");
                }

                pst.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Login Error: " + ex.getMessage());
            }
        });

        setPreferredSize(new Dimension(1400, 800));
    }
}
