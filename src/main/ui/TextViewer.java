package ui;


import java.io.Console;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.Scanner;

import model.*;

// Creates a new TextViewer that can take in the data from an NBodySimulation, and inputs from the user, and prints text
public class TextViewer {
    Console console = System.console();
    Reader consoleReader = console.reader();
    NBodySimulation simulation;
    DecimalFormat df;
    Scanner scanner;

    public TextViewer(NBodySimulation simulation) {
        this.simulation = simulation;
        df = new DecimalFormat("#.###");
    }

    
    // MODIFIES: this
    // EFFECTS: starts and runs the simulation
    public void start() {
        while (true) {
            scanner = new Scanner(consoleReader);
            printPlanets();
            System.out.println("Enter command: (\"h\" for help) ");
            String command = scanner.nextLine();
            runCommand(command);
        }
    }

    // EFFECTS: prints all current planets
    public void printPlanets() {
        if (simulation.numPlanets() != 0) {
            System.out.println("Current planets:");
        }
        for (int i = 0; i < simulation.numPlanets(); i++) {
            Planet planet = simulation.getPlanet(i);
            System.out.println("Planet #" + i 
                                            + ": mass: "
                                            + String.valueOf(df.format(planet.getMass())) + ", xpos: " 
                                            + String.valueOf(df.format(planet.getXPos())) + ", ypos: " 
                                            + String.valueOf(df.format(planet.getYPos())) + ", x velocity: " 
                                            + String.valueOf(df.format(planet.getDXPos())) + ", y velocity: " 
                                            + String.valueOf(df.format(planet.getDYPos())));
        }
    }

    // MODIFIES: this
    // EFFECTS: runs given command
    public void runCommand(String command) {
        if (command.equals("h")) {
            runHelp();
        } else if (command.equals("a")) {
            addPlanet();
        } else if (command.equals("r")) {
            removePlanet();
        } else if (command.equals("g")) {
            changeG();
        } else if (command.equals("t")) {
            runTicks();
        } else if (command.equals("awv")) {
            addPlanetWithVelocity(); 
        } else if (command.equals("vt")) {
            viewRunTicks(); 
        } else {
            System.out.println("Error, command: \"" + command + "\" not recognized, please try again");
        }
    }

    // EFFECTS: prints out a list of commands
    public void runHelp() {
        System.out.println("Valid commands:");
        System.out.println("\"a\" add planet");
        System.out.println("\"awv\" add planet with velocity");
        System.out.println("\"r\" remove planet");
        System.out.println("\"t\" run given number of ticks");
        System.out.println("\"vt\" run given number of ticks, and view the planets each tick");
        System.out.println("\"g\" to change the gravitational constant");
    }

    // MODIFIES: this
    // EFFECTS: runs a given number of ticks of the simulation
    public void runTicks() {
        System.out.println("How many ticks do you want to run?");
        Integer ticks = expectPositiveInteger(scanner.nextLine());
        simulation.tick(ticks);
    }

    // MODIFIES: this
    // EFFECTS: runs a given number of ticks of the simulation, and prints out the planets each tick
    public void viewRunTicks() {
        System.out.println("How many ticks do you want to run and view?");
        Integer ticks = expectPositiveInteger(scanner.nextLine());
        for (int i = 0; i < ticks; i++) {
            simulation.tick(1);
            System.out.println("Tick number: " + (i + 1));
            for (int p = 0; p < simulation.numPlanets(); p++) {
                Planet planet = simulation.getPlanet(p);
                System.out.println("Planet #" + p 
                                                + ": mass: " 
                                                + String.valueOf(df.format(planet.getMass())) + ", xpos: " 
                                                + String.valueOf(df.format(planet.getXPos())) + ", ypos: "
                                                + String.valueOf(df.format(planet.getYPos())) + ", x velocity: "
                                                + String.valueOf(df.format(planet.getDXPos())) + ", y velocity: "
                                                + String.valueOf(df.format(planet.getDYPos())));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a given planet
    public void addPlanet() {
        System.out.println("What is the mass of this new planet? Please input an integer.");
        Integer mass = expectInteger(scanner.nextLine());
        System.out.println("What is the xpos of this new planet? Please input a float.");
        Float xpos = expectFloat(scanner.nextLine());
        System.out.println("What is the ypos of this new planet? Please input a float.");
        Float ypos = expectFloat(scanner.nextLine());
        for (int i = 0; i < simulation.numPlanets(); i++) {
            Planet planet = simulation.getPlanet(i);
            if (planet.getXPos() == xpos && planet.getYPos() == ypos) {
                System.out.println("Error, this planet would be at the same location as planet #" + i);
                start();
            }
        }
        simulation.addPlanet(mass, xpos, ypos);
    }

    // MODIFIES: this
    // EFFECTS: adds a given planet with velocity
    public void addPlanetWithVelocity() {
        System.out.println("What is the mass of this new planet? Please input an integer.");
        Integer mass = expectInteger(scanner.nextLine());
        System.out.println("What is the xpos of this new planet? Please input a float.");
        Float xpos = expectFloat(scanner.nextLine());
        System.out.println("What is the ypos of this new planet? Please input a float.");
        Float ypos = expectFloat(scanner.nextLine());
        System.out.println("What is the x velocity of this new planet? Please input a float.");
        Float dx = expectFloat(scanner.nextLine());
        System.out.println("What is the y velocity of this new planet? Please input a float.");
        Float dy = expectFloat(scanner.nextLine());
        for (int i = 0; i < simulation.numPlanets(); i++) {
            Planet planet = simulation.getPlanet(i);
            if (planet.getXPos() == xpos && planet.getYPos() == ypos) {
                System.out.println("Error, this planet would be at the same location as planet #" + i);
                start();
            }
        }
        simulation.addPlanetWithVelocity(mass, xpos, ypos, dx, dy);
    }

    // MODIFIES: this
    // EFFECTS: removes a given planet
    public void removePlanet() {
        System.out.println("What is index of the planet you want to remove? Please input a positive integer.");
        Integer index = expectPositiveInteger(scanner.nextLine());
        if (index > simulation.numPlanets()) {
            System.out.println("Error, " + index + " is out of range");
            start();
        }
        simulation.removePlanet(index);
    }

    // MODIFIES: this
    // EFFECTS: changes G to given value
    public void changeG() {
        System.out.println("Current G: " + df.format(simulation.getGravitationalConstant()) 
                                            + " what do you want the new G to be?");
        Float g = expectFloat(scanner.nextLine());
        simulation.setGravitationalConstant(g);
    }

    // EFFECTS: checks if given string is an integer
    public Integer expectInteger(String str) {
        try {
            Integer val = Integer.parseInt(str);
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Error, inputted string is not an integer");
            start();
            Integer val = 1;
            return val;
        }
    }
    // EFFECTS: checks if given string is a float

    public Float expectFloat(String str) {
        try {
            Float val = Float.parseFloat(str);
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Error, inputted string is not a float");
            start();
            Float val = (float) 1;
            return val;
        }
    }

    // EFFECTS: checks if given string is a positive integer
    public Integer expectPositiveInteger(String str) {
        try {
            Integer val = Integer.parseInt(str);
            if (val < 0) {
                System.out.println("Error, inputted string is not a postive integer");
                start();
            }
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Error, inputted string is not a postive integer");
            start();
            Integer val = 1;
            return val;
        }
    }
} 
