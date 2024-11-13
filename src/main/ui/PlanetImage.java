package ui;

import java.awt.*;

import javax.swing.JPanel;

public class PlanetImage extends JPanel{
    private Double xpos = 0.;
    private Double ypos = 0.;
    private Double dx = 0.;
    private Double dy = 0.;

    protected void setConstants(Double mass, Double xpos, Double ypos, Double dx, Double dy) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.dx = dx;
        this.dy = dy;
        setSize((int) Math.round(Math.log10(mass)), (int) Math.round(Math.log10(mass)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
