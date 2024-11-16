package model;

import java.awt.Color;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Creates a new NBodySimulation that can create a list of planets, run ticks, and change the data stored in the planets
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class NBodySimulation {

    ArrayList<Planet> planets;
    float gravitationalConstant;

    public NBodySimulation(float gravitationalConstant) {
        planets = new ArrayList<Planet>();
        this.gravitationalConstant = gravitationalConstant;
    }

    // MODIFIES: this
    // EFFECTS: finds the velocities of all planets and updates their position by given number of ticks
    public void tick(int num) {
        for (int i = 0; i < num; i++) {
            for (Planet planet : planets) {
                planet.updateVelocity(planets, gravitationalConstant);
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

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, ypos, dxpos, dypos, and color to planets
    public void addPlanetWithVelocity(int mass, float xpos, float ypos, float dxpos, float dypos, Color color) {
        Planet p = new Planet(mass, xpos, ypos, dxpos, dypos);
        p.setColor(color);
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

    // EFFECTS: converts this into Json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gravitationalConstant", gravitationalConstant);
        json.put("planets", planetsToJson());
        return json;
    }

    // EFFECTS: converts planets into Json
    private JSONArray planetsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Planet p : planets) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    public void setGravitationalConstant(float gravitationalConstant) {
        this.gravitationalConstant = gravitationalConstant;
    }

    public float getGravitationalConstant() {
        return gravitationalConstant;
    }

}