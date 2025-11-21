package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class AddShiftPanel extends JPanel {

    public AddShiftPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lbl = new JLabel("Add New Shift");
        lbl.setBounds(320, 30, 300, 30);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(new Color(26, 35, 126));
        add(lbl);

        JLabel nameLbl = new JLabel("Employee Name:");
        nameLbl.setBounds(250, 120, 150, 25);
        add(nameLbl);

        JTextField nameField = new JTextField();
        nameField.setBounds(400, 120, 200, 25);
        add(nameField);

        JLabel timeLbl = new JLabel("Shift Time:");
        timeLbl.setBounds(250, 170, 150, 25);
        add(timeLbl);

        JTextField timeField = new JTextField();
        timeField.setBounds(400, 170, 200, 25);
        add(timeField);

        JButton saveBtn = new JButton("Save Shift");
        saveBtn.setBounds(350, 240, 120, 35);
        saveBtn.setBackground(new Color(48, 63, 159));
        saveBtn.setForeground(Color.WHITE);
        add(saveBtn);
    }
}


