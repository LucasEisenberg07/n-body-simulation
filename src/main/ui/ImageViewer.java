package ui;

import javax.swing.*;
import java.io.Console;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.border.EmptyBorder;

import model.NBodySimulation;
import model.Planet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a new ImageView that can take in the data from an NBodySimulation, and inputs from the user, and produces images
// Some ideas on how to use swing taken from stackoverflow:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

public class ImageViewer extends JFrame implements ActionListener {
    private JLabel massLabel;
    private JLabel xposLabel;
    private JLabel yposLabel;
    private JLabel dxlabel;
    private JLabel dylabel;
    private JTextField massField;
    private JTextField xposField;
    private JTextField yposField;
    private JTextField dxfield;
    private JTextField dyfield;
    private JTextField gfield;
    private JPanel drawingPanel;
    private static final String JSON_STORE = "./data/NBodySimulation.json";
    private int width = 1300;
    private int height = 800;
    Console console = System.console();
    Reader consoleReader = console.reader();
    NBodySimulation simulation;
    DecimalFormat df;
    Scanner scanner;
    JsonReader jsonReader;
    JsonWriter jsonWriter;

    public ImageViewer(NBodySimulation simulation) {
        super("NBodySimulation");
        this.simulation = simulation;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout()); // Use BorderLayout for the main frame
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        // Initialize buttons and fields
        JButton tickButton = new JButton("Tick");
        JButton addPlanetButton = new JButton("Add a planet");
        JButton gbutton = new JButton("Change gravitational constant");
        tickButton.setActionCommand("runTick");
        tickButton.addActionListener(this);
        addPlanetButton.setActionCommand("addPlanet");
        addPlanetButton.addActionListener(this);
        gbutton.setActionCommand("changeG");
        gbutton.addActionListener(this);
        initializeLabelsAndFields();

        // Add buttons and fields to the frame
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(tickButton);
        drawLabelsAndFields(controlPanel);
        controlPanel.add(addPlanetButton);
        controlPanel.add(gfield);
        controlPanel.add(gbutton);
        add(controlPanel, BorderLayout.NORTH);

        // Initialize drawing panel
        drawingPanel = new JPanel();
        drawingPanel.setLayout(null); // Use null layout for absolute positioning
        add(drawingPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes labels and fields in the constructor
    private void initializeLabelsAndFields() {
        massField = new JTextField(5);
        xposField = new JTextField(5);
        yposField = new JTextField(5);
        dxfield = new JTextField(5);
        dyfield = new JTextField(5);
        gfield = new JTextField(5);
        massLabel = new JLabel("mass");
        xposLabel = new JLabel("x position");
        yposLabel = new JLabel("y position");
        dxlabel = new JLabel("x velocity");
        dylabel = new JLabel("y velocity");
    }

    // MODIFIES: this
    // EFFECTS: draws labels and fields in the constructor
    private void drawLabelsAndFields(JPanel panel) {
        panel.add(massLabel);
        panel.add(massField);
        panel.add(xposLabel);
        panel.add(xposField);
        panel.add(yposLabel);
        panel.add(yposField);
        panel.add(dxlabel);
        panel.add(dxfield);
        panel.add(dylabel);
        panel.add(dyfield);

    }

    // MODIFIES: this, simulation
    // EFFECTS: perform actions based on user input
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("runTick")) {
            simulation.tick(1);
            redraw();
        }
        if (e.getActionCommand().equals("changeG")) {
            simulation.setGravitationalConstant(Float.parseFloat(gfield.getText()));
        }
        if (e.getActionCommand().equals("addPlanet")) {
            simulation.addPlanetWithVelocity(Integer.parseInt(massField.getText()),
                    Float.parseFloat(xposField.getText()),
                    Float.parseFloat(yposField.getText()),
                    Float.parseFloat(dxfield.getText()),
                    Float.parseFloat(dyfield.getText()));
            redraw();
        }
    }

    // EFFECTS: draws the current planets onto the simulation
    private void redraw() {
        drawingPanel.removeAll();
        for (int i = 0; i < simulation.numPlanets(); i++) {
            Planet planet = simulation.getPlanet(i);
            drawPlanet(planet);
        }
        drawingPanel.revalidate();
        drawingPanel.repaint();
    }

    // EFFECTS: draws a given planet onto the simulation
    public void drawPlanet(Planet planet) {
        PlanetImage pimage = new PlanetImage();
        pimage.setColor(planet.getColor());
        pimage.setBounds((int) (planet.getXPos() + width / 2 - 100),
                (int) (planet.getYPos() + height / 2 - 100),
                20 * ((int) Math.round(Math.log10(planet.getMass())) + 1),
                20 * ((int) Math.round(Math.log10(planet.getMass())) + 1));
        drawingPanel.add(pimage);
        drawingPanel.revalidate();
        drawingPanel.repaint();
    }
}
