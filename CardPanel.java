package shiftmanager;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private int arcWidth = 30;
    private int arcHeight = 30;
    private Color shadowColor = new Color(0, 0, 0, 100);
    private int shadowOffset = 5;

    public CardPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw shadow
        g2.setColor(shadowColor);
        g2.fillRoundRect(shadowOffset, shadowOffset, getWidth() - shadowOffset, getHeight() - shadowOffset, arcWidth, arcHeight);

        // Draw main panel
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - shadowOffset, getHeight() - shadowOffset, arcWidth, arcHeight);

        g2.dispose();
        super.paintComponent(g);
    }
}
