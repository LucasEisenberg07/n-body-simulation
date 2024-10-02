package model;
import java.util.ArrayList;

public class NBodySimulation {

    ArrayList<Planet> planets;
    float G;
    public NBodySimulation() {
        planets = new ArrayList<Planet>();
        G = 1;
    }

    // MODIFIES: this
    // EFFECTS: finds the velocities of all planets and updates their position by one tick
    public void updatePlanets() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, and ypos to planets
    public void addPlanet(int mass, float xpos, float ypos) {
        // stub
    }

    // REQUIRES: 0 <= index <= length(planets)
    // MODIFIES: this
    // EFFECTS: remove a planet with given index in planets
    public void removePlanet(int index) {
        // stub
    }

    // REQUIRES: 0 <= index <= length(planets)
    // EFFECTS: get a planet with given index in planets
    public void getPlanet(int index) {
        // stub
    }


}