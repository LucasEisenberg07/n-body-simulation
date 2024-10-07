package model;
import java.util.ArrayList;

public class NBodySimulation {

    ArrayList<Planet> planets;
    float G;
    public NBodySimulation(float G) {
        planets = new ArrayList<Planet>();
        this.G = G;
    }

    // MODIFIES: this
    // EFFECTS: finds the velocities of all planets and updates their position by given number of ticks
    public void tick(int num) {
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
    public Planet getPlanet(int index) {
        Planet p = new Planet(0, 0, 0);
        return p;
    }

    public void setG(float G) {
        this.G = G;
    }

    public float getG() {
        return G;
    }

}