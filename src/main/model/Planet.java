package model;
import java.util.ArrayList;

// Creates a new planet that can store data about its position and velocity, and can update its position and velocity

public class Planet {
    float mass;
    double xpos;
    double ypos;
    double dxpos;
    double dypos;

    // REQUIRES: mass > 0 && -width ≤ xpos ≤ width && -height ≤ ypos ≤ height
    // EFFECTS: creates a planet with given mass, xpos, and ypos
    public Planet(float mass, float xpos, float ypos, float dx, float dy) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.dxpos = dx;
        this.dypos = dy;
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
    // REQUIRES: G != 0
    // EFFECTS: updates dxpos and dypos based on the positions of other planets
    public void updateVelocity(ArrayList<Planet> planets, float G) {
        double xAcceleration = 0;
        double yAcceleration = 0;
        for (Planet planet : planets) {
            if (planet != this) {
                double dx = planet.getXPos() - this.xpos;
                double dy = planet.getYPos() - this.ypos;
                double distance = Math.sqrt((dx*dx) + (dy*dy));
                double theta = Math.atan(dy/dx);
                double gravity = (this.mass*planet.getMass()*G)/(distance*distance);
                xAcceleration += (gravity*dx/distance)/this.mass;
                yAcceleration += (gravity*dy/distance)/this.mass;
            }
        }
        this.dxpos += xAcceleration;
        this.dypos += yAcceleration;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Planet other = (Planet) obj;
        if (Float.floatToIntBits(mass) != Float.floatToIntBits(other.mass))
            return false;
        if (Double.doubleToLongBits(xpos) != Double.doubleToLongBits(other.xpos))
            return false;
        if (Double.doubleToLongBits(ypos) != Double.doubleToLongBits(other.ypos))
            return false;
        return true;
    }
}
