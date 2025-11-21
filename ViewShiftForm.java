package shiftmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewShiftForm extends JPanel {

    public ViewShiftForm() {

        setLayout(new BorderLayout());

        // ===== Background Panel =====
        JPanel background = new JPanel() {
            Image bgImg = new ImageIcon("src/resource/dashboard - Copy.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new BorderLayout());

        // ===== Top Panel =====
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 

        ImageIcon icon = new ImageIcon("src/resource/editshift.jpeg");
        Image scaledIcon = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblIcon = new JLabel(new ImageIcon(scaledIcon));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(lblIcon);

        JLabel lblTitle = new JLabel("View Shifts", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        topPanel.add(lblTitle);

        background.add(topPanel, BorderLayout.NORTH);

        // ===== Table =====
        String[] columns = {"Employee Name", "Date", "Start Time", "End Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(25);

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT employee, date, start, end FROM shifts ORDER BY date DESC");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("employee"),
                        rs.getString("date"),
                        rs.getString("start"),
                        rs.getString("end")
                });
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading shifts: " + ex.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(scrollPane);

        background.add(centerPanel, BorderLayout.CENTER);

        add(background, BorderLayout.CENTER);
    }
}

