package ui;

import java.awt.*;

// not currently implimented, would create an image of the velocity of the planets to be displayed on screen

public class PlanetVelocityImage extends Image {
    // MODIFIES: this
    // EFFECTS: draws image onto panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    // MODIFIES: g
    // EFFECTS: draws image onto graphics
    private void draw(Graphics g) {
        g.setColor(super.color);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
