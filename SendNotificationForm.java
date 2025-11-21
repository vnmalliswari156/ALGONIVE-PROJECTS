package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SendNotificationForm extends JPanel {

    private JTextField txtEmployee;
    private JTextArea txtMessage;
    private JButton btnSend;
    private Image bgImage;
    private Image iconImage;

    public SendNotificationForm() {
        setLayout(new BorderLayout());

        bgImage = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();
        iconImage = new ImageIcon("src/resource/Notifications.jpeg")
                .getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        add(bgPanel, BorderLayout.CENTER);

        JLabel lblIcon = new JLabel(new ImageIcon(iconImage));
        lblIcon.setBounds(450, 30, 120, 120);
        bgPanel.add(lblIcon);

        JLabel lblTitle = new JLabel("Send Notification");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(400, 160, 400, 40);
        bgPanel.add(lblTitle);

        JLabel lblEmployee = new JLabel("Employee Name (optional):");
        lblEmployee.setForeground(Color.WHITE);
        lblEmployee.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmployee.setBounds(250, 220, 250, 25);
        bgPanel.add(lblEmployee);

        txtEmployee = new JTextField();
        txtEmployee.setBounds(520, 220, 300, 30);
        bgPanel.add(txtEmployee);

        JLabel lblMessage = new JLabel("Notification Message:");
        lblMessage.setForeground(Color.WHITE);
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        lblMessage.setBounds(250, 270, 250, 25);
        bgPanel.add(lblMessage);

        txtMessage = new JTextArea();
        txtMessage.setLineWrap(true);
        txtMessage.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtMessage);
        scroll.setBounds(250, 300, 570, 200);
        bgPanel.add(scroll);

        btnSend = new JButton("Send Notification");
        btnSend.setBounds(450, 520, 200, 40);
        btnSend.setBackground(new Color(50, 125, 255));
        btnSend.setForeground(Color.WHITE);
        btnSend.setFont(new Font("Arial", Font.BOLD, 18));
        bgPanel.add(btnSend);

        btnSend.addActionListener(e -> sendNotification());
    }

    private void sendNotification() {
        String employee = txtEmployee.getText().trim();
        String message = txtMessage.getText().trim();

        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Message cannot be empty!");
            return;
        }

        try {
            Connection con = DBConnect.getConnection();
            String sql;
            if (employee.isEmpty()) {
                sql = "INSERT INTO notifications (message) VALUES (?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, message);
                pst.executeUpdate();
                pst.close();
            } else {
                sql = "INSERT INTO notifications (employee, message) VALUES (?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, employee);
                pst.setString(2, message);
                pst.executeUpdate();
                pst.close();
            }
            con.close();

            JOptionPane.showMessageDialog(this, "✅ Notification sent successfully!");
            txtEmployee.setText("");
            txtMessage.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
