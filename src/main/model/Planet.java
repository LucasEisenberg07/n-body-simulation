package model;
import java.util.ArrayList;

public class Planet {
    float mass;
    float xpos;
    float ypos;
    float dxpos;
    float dypos;
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
    public float getXPos() {
        return xpos;
    }
    public float getYPos() {
        return ypos;
    }
    public float getDXPos() {
        return dxpos;
    }
    public float getDYPos() {
        return dypos;
    }
    public float getMass() {
        return mass;
    }
    // MODIFIES: this
    // REQUIRES: G > 0
    // EFFECTS: updates dxpos and dypos based on the positions of other planets

    public void updateVelocities(ArrayList<Planet> planets, float G) {
        float currentXVelocity = 0;
        float currentYVelocity = 0;
        for (Planet planet : planets) {
            float dx = this.xpos - planet.getXPos();
            float dy = this.ypos - planet.getYPos();
            currentXVelocity += (this.mass*planet.getMass()*G)/(dx*dx);
            currentYVelocity += (this.mass*planet.getMass()*G)/(dy*dy);
        }
        this.dxpos = currentXVelocity;
        this.dypos = currentYVelocity;
    }
    
}
