package ui;

import java.awt.*;

import javax.swing.JPanel;

// creates a new image that can be added to the screen

public abstract class Image extends JPanel {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: draws image onto panel
    protected void paintComponent(Graphics g) {

    }

    // MODIFIES: g
    // EFFECTS: draws image onto graphics
    protected abstract void draw(Graphics g);
}
