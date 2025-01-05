package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

// Creates a new NBodySimulation that can create a list of planets, run ticks, and change the data stored in the planets
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class NBodySimulation {

    ArrayList<Planet> planets;
    float gravitationalConstant;

    public NBodySimulation(float gravitationalConstant) {
        planets = new ArrayList<Planet>() {final void checkForComodification() {}};
        this.gravitationalConstant = gravitationalConstant;
    }

    // MODIFIES: this
    // EFFECTS: finds the velocities of all planets and updates their position by
    // given number of ticks
    public void tick(int num) {
        for (int i = 0; i < num; i++) {
            checkForCollisions();
            EventLog.getInstance().logEvent(new Event("Planets ticked"));
            for (Planet planet : planets) {
                planet.updateVelocity(planets, gravitationalConstant);
            }
            for (Planet planet : planets) {
                planet.updatePos();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks and executes any collisions
    private void checkForCollisions() {
        for (int i1 = 0; i1 < planets.size(); i1++) {
            for (int i2 = 0; i2 < planets.size(); i2++) { 
                Planet planet1 = planets.get(i1);
                Planet planet2 = planets.get(i2);
                if (planet1 != planet2) {
                    if (doTheyCollide(planet1, planet2)) {
                        EventLog.getInstance().logEvent(new Event("Boom! Collision!"));
                        planets.remove(planet1);
                        planets.remove(planet2);
                        Planet newPlanet = makeNewPlanet(planet1, planet2);
                        int red = getRed(planet1, planet2);
                        int green = getGreen(planet1, planet2);
                        int blue = getBlue(planet1, planet2);
                        newPlanet.setColor(new Color(red, green, blue));
                        if (newPlanet.getMass() != 0) {
                            planets.add(newPlanet);
                        }
                    }
                }
            }
        }
    }

    // EFFECTS: returns true if two planets are colliding
    private Boolean doTheyCollide(Planet planet1, Planet planet2) {
        int size1 = 20 * ((int) Math.round(Math.log10(Math.abs(planet1.getMass()))) + 1);
        int size2 = 20 * ((int) Math.round(Math.log10(Math.abs(planet2.getMass()))) + 1);
        double dx = Math.abs(planet1.getXPos() - planet2.getXPos());
        double dy = Math.abs(planet1.getYPos() - planet2.getYPos());
        double differenceBetween = Math.sqrt(dx * dx + dy * dy);
        return differenceBetween < (size1 + size2) / 2;
    }

    // EFFECTS: returns the new red value of the planets after colliding
    private int getRed(Planet planet1, Planet planet2) {
        return (int) Math.round((planet1.getColor().getRed()
                * planet1.getMass()
                + planet2.getColor().getRed() * planet2.getMass())
                / ((planet1.getMass() + planet2.getMass())));
    }

    // EFFECTS: returns the new green value of the planets after colliding
    private int getGreen(Planet planet1, Planet planet2) {
        return (int) Math.round((planet1.getColor().getGreen()
                * planet1.getMass()
                + planet2.getColor().getGreen() * planet2.getMass())
                / ((planet1.getMass() + planet2.getMass())));
    }

    // EFFECTS: returns the new blue value of the planets after colliding
    private int getBlue(Planet planet1, Planet planet2) {
        return (int) Math.round((planet1.getColor().getBlue()
                * planet1.getMass()
                + planet2.getColor().getBlue() * planet2.getMass())
                / ((planet1.getMass() + planet2.getMass())));
    }

    // EFFECTS: makes a new planet corresponding to the collision of planet1 and planet2
    private Planet makeNewPlanet(Planet planet1, Planet planet2) {
        return new Planet((float) (planet1.getMass()
                + planet2.getMass()),
                (float) ((((planet1.getXPos() * planet1.getMass()
                        + planet2.getXPos() * planet2.getMass()))
                        / (planet1.getMass() + planet2.getMass()))),
                (float) ((((planet1.getYPos() * planet1.getMass()
                        + planet2.getYPos() * planet2.getMass()))
                        / (planet1.getMass() + planet2.getMass()))),
                (float) ((planet1.getMass() * planet1.getDXPos()
                        + planet2.getMass() * planet2.getDXPos())
                        / (planet1.getMass() + planet2.getMass())),
                (float) ((planet1.getMass() * planet1.getDYPos()
                        + planet2.getMass() * planet2.getDYPos())
                        / (planet1.getMass() + planet2.getMass())));
    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, and ypos to planets
    public void addPlanet(int mass, float xpos, float ypos) {
        Planet p = new Planet(mass, xpos, ypos, 0, 0);
        planets.add(p);
        EventLog.getInstance().logEvent(new Event("A planet was added"));

    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, ypos, dxpos, and dypos to
    // planets
    public void addPlanetWithVelocity(int mass, float xpos, float ypos, float dxpos, float dypos) {
        Planet p = new Planet(mass, xpos, ypos, dxpos, dypos);
        planets.add(p);
        EventLog.getInstance().logEvent(new Event("A planet was added"));
    }

    // MODIFIES: this
    // EFFECTS: adds a new planet with given mass, xpos, ypos, dxpos, dypos, and
    // color to planets
    public void addPlanetWithVelocity(int mass, float xpos, float ypos, float dxpos, float dypos, Color color) {
        Planet p = new Planet(mass, xpos, ypos, dxpos, dypos);
        p.setColor(color);
        planets.add(p);
        EventLog.getInstance().logEvent(new Event("A planet was added"));
    }

    // REQUIRES: 0 <= index <= planets.size()
    // MODIFIES: this
    // EFFECTS: remove a planet with given index in planets
    public void removePlanet(int index) {
        ArrayList<Planet> newPlanets = new ArrayList<Planet>();
        Planet planetToRemove = planets.get(index);
        EventLog.getInstance().logEvent(new Event("A planet was removed"));
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

    // EFFECTS: loads the given planets onto the simulation
    public void loadPlanets(List<Planet> planets) {
        EventLog.getInstance().logEvent(new Event("Program loaded"));
        this.planets = (ArrayList) planets;
    }

    // EFFECTS: converts this into Json
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("Program saved"));
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
        EventLog.getInstance().logEvent(new Event("Gravitational constant changed"));
        this.gravitationalConstant = gravitationalConstant;
    }

    public float getGravitationalConstant() {
        return gravitationalConstant;
    }

}