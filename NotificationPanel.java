package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class NotificationPanel extends JPanel {

    public NotificationPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JTextArea box = new JTextArea("🔔 No new notifications.");
        box.setFont(new Font("Arial", Font.PLAIN, 18));
        box.setEditable(false);

        add(box, BorderLayout.CENTER);
    }
}

