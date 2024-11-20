package ui;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.border.EmptyBorder;

import model.NBodySimulation;
import model.Planet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates a new ImageViewer that can take in an NBodySimulation, and inputs from the user, and produces images
// Some ideas on how to use Swing taken from stackoverflow:
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
    private JButton tickButton;
    private JButton addPlanetButton;
    private JButton gbutton;
    private JButton saveButton;
    private JButton loadButton;
    private static final String JSON_STORE = "./data/NBodySimulation.json";
    private int width = 1500;
    private int height = 1000;
    private NBodySimulation simulation;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public ImageViewer(NBodySimulation simulation) {
        super("NBodySimulation");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.simulation = simulation;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout()); // Use BorderLayout for the main frame
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        initializeControlPanel();
        // Initialize drawing panel
        drawingPanel = new JPanel();
        drawingPanel.setLayout(null);
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

    // MODIFIES: this
    // EFFECTS: initializes the buttons and fields on the control panel
    private void initializeControlPanel() {
        initializeLabelsAndFields();
        tickButton = new JButton("Tick");
        addPlanetButton = new JButton("Add a planet");
        gbutton = new JButton("Change gravitational constant");
        saveButton = new JButton("save");
        loadButton = new JButton("load");
        tickButton.setActionCommand("runTick");
        tickButton.addActionListener(this);
        addPlanetButton.setActionCommand("addPlanet");
        addPlanetButton.addActionListener(this);
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
        gbutton.setActionCommand("changeG");
        gbutton.addActionListener(this);
        addButtonsAndFields();
    }

    // MODIFIES: this
    // EFFECTS: add buttons, fields, and labels to the frame
    private void addButtonsAndFields() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(tickButton);
        drawLabelsAndFields(controlPanel);
        controlPanel.add(addPlanetButton);
        controlPanel.add(gfield);
        controlPanel.add(gbutton);
        controlPanel.add(saveButton);
        controlPanel.add(loadButton);
        add(controlPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this, simulation
    // EFFECTS: perform actions based on user input
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("runTick")) {
            simulation.tick(1);
            redraw();
        }
        if (e.getActionCommand().equals("save")) {
            saveNBS();
        }
        if (e.getActionCommand().equals("load")) {
            loadNBS();
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
        int size = 20 * ((int) Math.round(Math.log10(Math.abs(planet.getMass()))) + 1);
        pimage.setBounds((int) (planet.getXPos() + width / 2 - size / 2 - 40),
                (int) (planet.getYPos() + height / 2 - size / 2 - 80),
                size,
                size);
        drawingPanel.add(pimage);
        drawingPanel.revalidate();
        drawingPanel.repaint();
    }

    // EFFECTS: saves NBodySimulation to file
    private void saveNBS() {
        try {
            jsonWriter.open();
            jsonWriter.write(simulation);
            jsonWriter.close();
            System.out.println("Saved NBodySimulation to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads NBodySimulation from file
    private void loadNBS() {
        try {
            simulation = jsonReader.read();
            System.out.println("Loaded NBodySimulation from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
