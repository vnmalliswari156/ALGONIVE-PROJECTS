package shiftmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminDashboard extends JFrame {

    JTable table;
    JButton addBtn;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(500,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addBtn = new JButton("Add Shift", new ImageIcon("src/icons/add.png"));
        addBtn.addActionListener(e -> new ShiftForm().setVisible(true));

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(addBtn, BorderLayout.SOUTH);

        loadShifts();
    }

    void loadShifts() {
        try {
            Connection con = DBConnect.connect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM shifts");
            table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
