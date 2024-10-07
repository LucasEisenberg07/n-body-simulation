package model;
import java.util.ArrayList;

// Creates a new NBodySimulation that can create a list of planets, run ticks on the planets, and manipulate the data stored in the planets

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
        for (int i = 0; i < num; i++) {
            for (Planet planet : planets) {
                planet.updateVelocity(planets, G);
            }
            for (Planet planet : planets) {
                planet.updatePos();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, and ypos to planets
    public void addPlanet(int mass, float xpos, float ypos) {
        Planet p = new Planet(mass, xpos, ypos, 0, 0);
        planets.add(p);
    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, ypos, dxpos, and dypos to planets
    public void addPlanetWithVelocity(int mass, float xpos, float ypos, float dxpos, float dypos) {
        Planet p = new Planet(mass, xpos, ypos, dxpos, dypos);
        planets.add(p);
    }

    // REQUIRES: 0 <= index <= planets.size()
    // MODIFIES: this
    // EFFECTS: remove a planet with given index in planets
    public void removePlanet(int index) {
        ArrayList<Planet> newPlanets = new ArrayList<Planet>();
        Planet planetToRemove = planets.get(index);
        for (Planet planet : planets) {
            if (planet != planetToRemove) {
                newPlanets.add(planet);
            }
        }
        planets = newPlanets;
    }

    // REQUIRES: 0 <= index <= planets.size()
    // EFFECTS: get a planet with given index in planets
    public Planet getPlanet(int index) {
        return planets.get(index);
    }

    // EFFECTS: returns the number of planets in planets
    public int numPlanets() {
        return planets.size();
    }

    public void setG(float G) {
        this.G = G;
    }

    public float getG() {
        return G;
    }

}