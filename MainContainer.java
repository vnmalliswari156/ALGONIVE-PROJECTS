package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class MainContainer extends JFrame {

    private JPanel mainPanel;
    private JPanel topPanel;

    public static boolean isLoggedIn = false;

    public MainContainer() {
        setTitle("Shift Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(new Color(0, 51, 102));
        add(topPanel, BorderLayout.NORTH);

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        loadNavigationButtons();

        // ✅ Show Dashboard FIRST on startup
        showDashboard();

        setVisible(true);
    }

    public void loadNavigationButtons() {
        topPanel.removeAll();

        if (!isLoggedIn) {
            // Before login → show Dashboard, Register, Login
            JButton btnDashboard = createNavButton("Dashboard", "Dashboard.png");
            JButton btnRegister = createNavButton("Register", "register.png");
            JButton btnLogin = createNavButton("Login", "login.png");

            topPanel.add(btnDashboard);
            topPanel.add(btnRegister);
            topPanel.add(btnLogin);

            btnDashboard.addActionListener(e -> showDashboard());
            btnRegister.addActionListener(e -> showRegister());
            btnLogin.addActionListener(e -> showLogin());
        } else {
            // After login → show all other nav items
            JButton btnDashboard = createNavButton("Dashboard", "Dashboard.png");
            JButton btnAddShift = createNavButton("Add Shift", "Addshift.jpeg");
            JButton btnViewShift = createNavButton("View Shifts", "editshift.jpeg");
            JButton btnNotifications = createNavButton("Notifications", "Notifications.jpeg");
            JButton btnLogout = createNavButton("Logout", "logout.png");

            topPanel.add(btnDashboard);
            topPanel.add(btnAddShift);
            topPanel.add(btnViewShift);
            topPanel.add(btnNotifications);
            topPanel.add(btnLogout);

            btnDashboard.addActionListener(e -> showDashboard());
            btnAddShift.addActionListener(e -> showAddShift());
            btnViewShift.addActionListener(e -> showViewShifts());

            // Notifications Menu
            JPopupMenu notifMenu = new JPopupMenu();
            JMenuItem viewNotif = new JMenuItem("View Notifications");
            JMenuItem sendNotif = new JMenuItem("Send Notification");
            notifMenu.add(viewNotif);
            notifMenu.add(sendNotif);

            btnNotifications.addActionListener(e -> notifMenu.show(btnNotifications, 0, btnNotifications.getHeight()));

            viewNotif.addActionListener(e -> showViewNotifications());
            sendNotif.addActionListener(e -> showSendNotifications());

            // Logout
            btnLogout.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Logged out successfully!");
                isLoggedIn = false;
                loadNavigationButtons();
                showDashboard(); // ✅ After logout, go back to dashboard
            });
        }

        topPanel.revalidate();
        topPanel.repaint();
    }

    private JButton createNavButton(String text, String iconName) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 102, 204));
        btn.setFocusPainted(false);

        try {
            Image img = new ImageIcon("src/resource/" + iconName).getImage();
            img = img.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Icon Missing: " + iconName);
        }
        return btn;
    }

    // ====== Page Methods ======

    public void showLogin() {
        mainPanel.removeAll();
        mainPanel.add(new LoginForm(this), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void loginSuccess() {
        isLoggedIn = true;
        loadNavigationButtons();
        showDashboard();
    }

    public void showRegister() {
        mainPanel.removeAll();
        mainPanel.add(new RegisterForm(this), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showDashboard() {
        mainPanel.removeAll();
        JPanel dash = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel lbl = new JLabel("Welcome to SHIFT MANAGEMENT", SwingConstants.CENTER);
        lbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 42)); // ✅ Changed font style
        lbl.setForeground(Color.WHITE);
        lbl.setBounds(420, 260, 700, 60);
        dash.add(lbl);

        mainPanel.add(dash);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showAddShift() {
        mainPanel.removeAll();
        mainPanel.add(new AddShiftForm(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showViewShifts() {
        mainPanel.removeAll();
        mainPanel.add(new ViewShiftForm(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showViewNotifications() {
        mainPanel.removeAll();
        mainPanel.add(new ViewNotificationForm(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showSendNotifications() {
        // Admin login panel
        JPanel authPanel = new JPanel(null);
        authPanel.setBackground(new Color(0, 51, 102));

        JLabel lblUser = new JLabel("Admin Username:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(400, 200, 150, 25);
        authPanel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(550, 200, 200, 25);
        authPanel.add(txtUser);

        JLabel lblPass = new JLabel("Admin Password:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(400, 240, 150, 25);
        authPanel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(550, 240, 200, 25);
        authPanel.add(txtPass);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(500, 300, 120, 30);
        authPanel.add(btnLogin);

        mainPanel.removeAll();
        mainPanel.add(authPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            // ✅ Admin credentials
            if (user.equals("admin") && pass.equals("admin123")) {
                mainPanel.removeAll();
                mainPanel.add(new SendNotificationForm(), BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid admin credentials!");
            }
        });
    }

    public static void main(String[] args) {
        new MainContainer();
    }
}
