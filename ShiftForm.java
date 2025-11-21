package shiftmanager;

import javax.swing.*;
import java.sql.*;

public class ShiftForm extends JFrame {

    JTextField nameField, dateField, timeField;
    JButton saveBtn;

    public ShiftForm() {
        setTitle("Add Shift");
        setSize(350, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel p = new JPanel(null);

        JLabel l1 = new JLabel("Employee Name:");
        l1.setBounds(20, 20, 120, 25);
        p.add(l1);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 150, 25);
        p.add(nameField);

        JLabel l2 = new JLabel("Date (YYYY-MM-DD):");
        l2.setBounds(20, 70, 150, 25);
        p.add(l2);

        dateField = new JTextField();
        dateField.setBounds(150, 70, 150, 25);
        p.add(dateField);

        JLabel l3 = new JLabel("Time (HH:MM):");
        l3.setBounds(20, 120, 120, 25);
        p.add(l3);

        timeField = new JTextField();
        timeField.setBounds(150, 120, 150, 25);
        p.add(timeField);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(110, 180, 100, 30);
        p.add(saveBtn);

        saveBtn.addActionListener(e -> saveShift());

        add(p);
    }

    void saveShift() {
        try {
            Connection con = DBConnect.connect();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO shifts(name, date, time) VALUES (?,?,?)"
            );
            pst.setString(1, nameField.getText());
            pst.setString(2, dateField.getText());
            pst.setString(3, timeField.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "✅ Shift Added!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }
}

