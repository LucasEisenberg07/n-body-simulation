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
        Planet p = new Planet(mass, xpos, ypos);
        planets.add(p);
    }

    // REQUIRES: 0 <= index <= length(planets)
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

    // REQUIRES: 0 <= index <= length(planets)
    // EFFECTS: get a planet with given index in planets
    public Planet getPlanet(int index) {
        return planets.get(index);
    }

    public void setG(float G) {
        this.G = G;
    }

    public float getG() {
        return G;
    }

}