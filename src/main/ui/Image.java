package ui;

import java.awt.*;

import javax.swing.JPanel;

public abstract class Image extends JPanel {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: draws image onto panel
    protected void paintComponent(Graphics g){}

    // MODIFIES: g
    // EFFECTS: draws image onto graphics
    private void draw(Graphics g){}
}
