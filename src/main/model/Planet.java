package model;
import java.util.ArrayList;

public class Planet {
    float mass;
    double xpos;
    double ypos;
    double dxpos;
    double dypos;
    int width = 500;
    int height = 500;

    // REQUIRES: mass > 0 && -width ≤ xpos ≤ width && -height ≤ ypos ≤ height
    // EFFECTS: creates a planet with given mass, xpos, and ypos
    public Planet(float mass, float xpos, float ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.dxpos = 0;
        this.dypos = 0;
        this.mass = mass;
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

    // MODIFIES: this
    // REQUIRES: G > 0, ArrayList<Planet> planets does not contain planet
    // EFFECTS: updates dxpos and dypos based on the positions of other planets
    public void updateVelocity(ArrayList<Planet> planets, float G) {
        double currentXVelocity = 0;
        double currentYVelocity = 0;
        for (Planet planet : planets) {
            double dx = planet.getXPos() - this.xpos;
            double dy = planet.getYPos() - this.ypos;
            double distance = Math.sqrt((dx*dx) + (dy*dy));
            double theta = Math.atan(dy/dx);
            double gravity = (this.mass*planet.getMass()*G)/(distance*distance);
            currentXVelocity += (gravity*dy/distance);
            currentYVelocity += (gravity*dx/distance);
        }
        this.dxpos = currentXVelocity;
        this.dypos = currentYVelocity;
    }
    
}