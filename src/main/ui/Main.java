package ui;

import model.NBodySimulation;

public class Main {
    public static void main(String[] args) {
        float initialG = (float) 0.1;
        NBodySimulation simulation = new NBodySimulation(initialG);
        ImageViewer viewer = new ImageViewer(simulation);
        // viewer.start(); 
    }
}
