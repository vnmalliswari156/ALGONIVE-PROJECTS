package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class AddShiftForm extends JPanel {

    JTextField txtEmployee, txtDate, txtStart, txtEnd;

    public AddShiftForm() {

        setLayout(new BorderLayout()); 

        JPanel bgPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/dashboard - Copy.jpeg").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        add(bgPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(null);
        formPanel.setOpaque(false);
        formPanel.setBounds(0, 0, 900, 600);
        bgPanel.add(formPanel);

        ImageIcon icon = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/formicon.png");
        Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(scaled));
        lblIcon.setBounds(610, 80, 150, 150);
        formPanel.add(lblIcon);

        JLabel lblTitle = new JLabel("Add New Shift");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(580, 230, 250, 40);
        formPanel.add(lblTitle);

        int leftX = 520;
        int boxWidth = 280;

        JLabel lbl1 = new JLabel("Employee Name:");
        lbl1.setForeground(Color.WHITE);
        lbl1.setBounds(leftX, 300, 150, 25);
        formPanel.add(lbl1);

        txtEmployee = new JTextField();
        txtEmployee.setBounds(leftX + 160, 300, boxWidth, 28);
        formPanel.add(txtEmployee);

        JLabel lbl2 = new JLabel("Date (YYYY-MM-DD):");
        lbl2.setForeground(Color.WHITE);
        lbl2.setBounds(leftX, 340, 150, 25);
        formPanel.add(lbl2);

        txtDate = new JTextField();
        txtDate.setBounds(leftX + 160, 340, boxWidth, 28);
        formPanel.add(txtDate);

        JLabel lbl3 = new JLabel("Start Time:");
        lbl3.setForeground(Color.WHITE);
        lbl3.setBounds(leftX, 380, 150, 25);
        formPanel.add(lbl3);

        txtStart = new JTextField();
        txtStart.setBounds(leftX + 160, 380, boxWidth, 28);
        formPanel.add(txtStart);

        JLabel lbl4 = new JLabel("End Time:");
        lbl4.setForeground(Color.WHITE);
        lbl4.setBounds(leftX, 420, 150, 25);
        formPanel.add(lbl4);

        txtEnd = new JTextField();
        txtEnd.setBounds(leftX + 160, 420, boxWidth, 28);
        formPanel.add(txtEnd);

        ImageIcon saveIcon = new ImageIcon("C:/Users/Chinna/eclipse-workspace/ShiftManager/src/resource/Addshift.jpeg");
        Image saveImg = saveIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        JButton btnSave = new JButton("Save Shift", new ImageIcon(saveImg));
        btnSave.setBounds(620, 470, 180, 40);
        btnSave.setBackground(new Color(50, 125, 255));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(btnSave);

        btnSave.addActionListener(e -> onAddShift());
    }

    // ✅ UPDATED METHOD USING ShiftDAO
    private void onAddShift() {
        String emp = txtEmployee.getText().trim();
        String date = txtDate.getText().trim();
        String start = txtStart.getText().trim();
        String end = txtEnd.getText().trim();

        if (emp.isEmpty() || date.isEmpty() || start.isEmpty() || end.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Please fill all fields!");
            return;
        }

        ShiftDAO dao = new ShiftDAO();
        boolean ok = dao.addShift(emp, date, start, end);

        if (ok) {
            JOptionPane.showMessageDialog(this, "✅ Shift Saved Successfully!");

            // clear the form
            txtEmployee.setText("");
            txtDate.setText("");
            txtStart.setText("");
            txtEnd.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Failed to Save Shift!");
        }
    }

    public JPanel getContentPanel() {
        return this;
    }
}
