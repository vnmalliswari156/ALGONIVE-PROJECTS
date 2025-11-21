package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends JPanel {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;

    private MainContainer parent;

    public RegisterForm(MainContainer parent) {
        this.parent = parent;
        createUI();
    }

    public RegisterForm() {
        createUI();
    }

    private void createUI() {

        // ✅ Background fills the full screen
        setLayout(new BorderLayout());

        Image bg = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(new GridBagLayout()); // ✅ Centers everything
        add(bgPanel, BorderLayout.CENTER);

        // ✅ Center container panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(0, 0, 0, 150)); // Transparent black for neat look
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension(400, 500));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ✅ Register Icon + Heading
        ImageIcon icon = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/reg.jpeg");
        JLabel regIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        regIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ✅ Input Fields & Labels neatly aligned
        JLabel lblName = createLabel("Full Name:");
        nameField = createTextField();

        JLabel lblEmail = createLabel("Email:");
        emailField = createTextField();

        JLabel lblPass = createLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 35));

        // ✅ Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setPreferredSize(new Dimension(300, 40));
        btnRegister.setMaximumSize(new Dimension(300, 40));
        btnRegister.setBackground(new Color(46, 204, 113));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegister.addActionListener(e -> registerUser());

        // ✅ Login Link
        JButton btnLoginLink = new JButton("Already have an account? Login");
        btnLoginLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLoginLink.setMaximumSize(new Dimension(300, 30));
        btnLoginLink.addActionListener(e -> {
            if (parent != null) parent.showLogin();
            else JOptionPane.showMessageDialog(this, "Run from MainContainer");
        });

        // ✅ Add items to form panel
        formPanel.add(regIcon);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(25));

        formPanel.add(lblName);
        formPanel.add(nameField);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(lblEmail);
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(lblPass);
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(25));

        formPanel.add(btnRegister);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(btnLoginLink);

        bgPanel.add(formPanel); // ✅ Center it
    }

    // ✅ Helper to create labels
    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    // ✅ Helper to create input fields
    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(300, 35));
        return tf;
    }

    // ✅ Database save
    private void registerUser() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)");

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, pass);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Registration Successful!");

            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");

            pst.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error Saving User!");
        }
    }

    public JPanel getContentPanel() {
        return this;
    }

    // ✅ Standalone run (full screen)
    public static void main(String[] args) {
        JFrame f = new JFrame("Register");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.add(new RegisterForm());
        f.setVisible(true);
    }
}
