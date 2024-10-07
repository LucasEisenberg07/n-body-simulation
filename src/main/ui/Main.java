package ui;

import model.NBodySimulation;
import model.Planet;
import ui.TextViewer;

public class Main {
    public static void main(String[] args) {
        int initialG = 1;
        NBodySimulation simulation = new NBodySimulation(initialG);
        TextViewer viewer = new TextViewer(simulation);
        viewer.start(); 
    }
}
