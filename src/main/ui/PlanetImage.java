package ui;

import java.awt.*;

import javax.swing.JPanel;

public class PlanetImage extends JPanel {
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
