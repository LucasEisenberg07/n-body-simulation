package ui;

import java.awt.*;

// creates an image of a planet to be added onto the screen

public class PlanetImage extends Image {
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
