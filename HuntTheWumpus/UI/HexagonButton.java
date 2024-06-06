package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonButton extends JButton {

    public HexagonButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        //setBackground(Color.DARK_GRAY);
        //setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.RED);
        } else {
            g.setColor(getBackground());
        }
        g.fillPolygon(createHexagon());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawPolygon(createHexagon());
    }

    @Override
    public boolean contains(int x, int y) {
        return createHexagon().contains(x, y);
    }

    private Polygon createHexagon() {
        int width = getWidth();
        int height = getHeight();
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (width / 2 + width / 2 * Math.cos(i * 2 * Math.PI / 6));
            yPoints[i] = (int) (height / 2 + height / 2 * Math.sin(i * 2 * Math.PI / 6));
        }

        return new Polygon(xPoints, yPoints, 6);
    }
}
