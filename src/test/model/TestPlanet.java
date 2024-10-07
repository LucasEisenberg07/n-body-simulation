package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlanet {
    Planet testPlanet;
    Planet otherPlanet;
    Planet anotherPlanet;
    ArrayList<Planet> testPlanets;
    float G;
    @BeforeEach
    void runBefore() {
        testPlanet = new Planet(10, 0, 0, 0, 0);
        otherPlanet = new Planet(100, 10, 0, 0, 0);
        anotherPlanet = new Planet(100, 0, 10, 0, 0);
        G = 1;
        testPlanets = new ArrayList<Planet>();
        testPlanets.add(otherPlanet);
        testPlanets.add(anotherPlanet);
    }

    @Test
    void testConstructor() {
        assertEquals((float) 10, testPlanet.getMass());
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals((float) 0, testPlanet.getDXPos());
        assertEquals((float) 0, testPlanet.getDYPos());
    }

    @Test
    void testUpdatePos() {
        testPlanet.setDXPos(10);
        testPlanet.setDYPos(10);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals((float) 10, testPlanet.getDXPos());
        assertEquals((float) 10, testPlanet.getDYPos());
        testPlanet.updatePos();
        assertEquals((float) 10, testPlanet.getXPos());
        assertEquals((float) 10, testPlanet.getYPos());
        assertEquals((float) 10, testPlanet.getDXPos());
        assertEquals((float) 10, testPlanet.getDYPos());
    }

    @Test
    void testUpdatePosMultipleTimes() {
        testPlanet.setDXPos(20);
        testPlanet.setDYPos(10);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals((float) 20, testPlanet.getDXPos());
        assertEquals((float) 10, testPlanet.getDYPos());
        testPlanet.updatePos();
        assertEquals((float) 20, testPlanet.getXPos());
        assertEquals((float) 10, testPlanet.getYPos());
        assertEquals((float) 20, testPlanet.getDXPos());
        assertEquals((float) 10, testPlanet.getDYPos());
        testPlanet.updatePos();
        assertEquals((float) 40, testPlanet.getXPos());
        assertEquals((float) 20, testPlanet.getYPos());
        assertEquals((float) 20, testPlanet.getDXPos());
        assertEquals((float) 10, testPlanet.getDYPos());
    }
    
    @Test 
    void testUpdateVelocity() {
        testPlanet.updateVelocity(testPlanets, G);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals((float) 1, testPlanet.getDXPos());
        assertEquals((float) 1, testPlanet.getDYPos());
    }

    @Test 
    void testUpdateVelocityMultipleTimes() {
        testPlanet.updateVelocity(testPlanets, G);
        otherPlanet.setMass(1000);
        otherPlanet.setXPos(100);
        otherPlanet.setYPos(100);
        double distance = Math.sqrt((100*100) + (100*100));
        double gravity = (1000)/(distance*distance);
        double xvelocity = 1 + gravity*1000/distance/10;
        double yvelocity = (gravity*1000/distance)/10 + 2;
        testPlanet.updateVelocity(testPlanets, G);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals(xvelocity, testPlanet.getDXPos());
        assertEquals(yvelocity, testPlanet.getDYPos());
    }

    @Test 
    void testEqualsAndHashCode() {
        Planet samePlanet = new Planet(10, 0, 0, 0, 0);
        Planet differentPlanet = new Planet(10, (float) 0.1, 0, 0, 0);
        assertEquals(samePlanet, testPlanet);
        assertEquals(samePlanet.hashCode(), testPlanet.hashCode());
        assertNotEquals(differentPlanet.hashCode(), testPlanet.hashCode());
        
    }
}
