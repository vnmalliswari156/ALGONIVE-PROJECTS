package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class ViewShiftPanel extends JPanel {

    public ViewShiftPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        String[] column = {"ID", "Employee", "Shift Time"};
        Object[][] data = {
                {"1", "John", "09:00 - 05:00"},
                {"2", "Sara", "10:00 - 06:00"}
        };

        JTable table = new JTable(data, column);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

	public Component getContentPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}



