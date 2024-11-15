package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;

// Creates a new planet that can store data about its position and velocity, and can update its position and velocity
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Planet {
    float mass;
    double xpos;
    double ypos;
    double dxpos;
    double dypos;
    Color color;

    // REQUIRES: mass > 0 && -width ≤ xpos ≤ width && -height ≤ ypos ≤ height
    // EFFECTS: creates a planet with given mass, xpos, and ypos
    public Planet(float mass, float xpos, float ypos, float dx, float dy) {
        Random random = new Random();
        this.xpos = xpos;
        this.ypos = ypos;
        this.dxpos = dx;
        this.dypos = dy;
        this.mass = mass;
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(255));
    }

    // MODIFIES: this
    // EFFECTS: moves a planet's xpos and ypos by dxpos and dypos
    public void updatePos() {
        this.xpos += dxpos;
        this.ypos += dypos;
    }

    public double getXPos() {
        return xpos;
    }

    public void setXPos(float xpos) {
        this.xpos = xpos;
    }

    public double getYPos() {
        return ypos;
    }

    public void setYPos(float ypos) {
        this.ypos = ypos;
    }

    public double getDXPos() {
        return dxpos;
    }

    public void setDXPos(float dxpos) {
        this.dxpos = dxpos;
    }

    public double getDYPos() {
        return dypos;
    }

    public void setDYPos(float dypos) {
        this.dypos = dypos;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // MODIFIES: this
    // REQUIRES: G != 0
    // EFFECTS: updates dxpos and dypos based on the positions of other planets
    public void updateVelocity(ArrayList<Planet> planets, float g) {
        double xacceleration = 0;
        double yacceleration = 0;
        for (Planet planet : planets) {
            if (planet != this) {
                double dx = planet.getXPos() - this.xpos;
                double dy = planet.getYPos() - this.ypos;
                double distance = Math.sqrt((dx * dx) + (dy * dy));
                double gravity = (this.mass * planet.getMass() * g) / (distance * distance);
                xacceleration += (gravity * dx / distance) / this.mass;
                yacceleration += (gravity * dy / distance) / this.mass;
            }
        }
        this.dxpos += xacceleration;
        this.dypos += yacceleration;
    }

    //EFFECTS: converts this into Json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mass", mass);
        json.put("xpos", xpos);
        json.put("ypos", ypos);
        json.put("dxpos", dxpos);
        json.put("dypos", dypos);
        return json;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(mass);
        long temp;
        temp = Double.doubleToLongBits(xpos);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ypos);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Planet other = (Planet) obj;
        if (Float.floatToIntBits(mass) != Float.floatToIntBits(other.mass)) {
            return false;
        }
        if (Double.doubleToLongBits(xpos) != Double.doubleToLongBits(other.xpos)) {
            return false;
        }
        if (Double.doubleToLongBits(ypos) != Double.doubleToLongBits(other.ypos)) {
            return false;
        }
        return true;
    }
}
