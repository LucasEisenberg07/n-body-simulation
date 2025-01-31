package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlanet {
    Planet testPlanet;
    Planet otherPlanet;
    Planet anotherPlanet;
    Planet whiteHole;
    ArrayList<Planet> testPlanets;
    float gravitationalConstant;

    @BeforeEach
    void runBefore() {
        testPlanet = new Planet(10, 0, 0, 0, 0);
        otherPlanet = new Planet(100, 10, 0, 0, 0);
        anotherPlanet = new Planet(100, 0, 10, 0, 0);
        whiteHole = new Planet(-100, 0, 10, 0, 0);
        gravitationalConstant = 1;
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
        Color color = new Color(0, 0, 100);
        testPlanet.setColor(color);
        assertEquals(color, testPlanet.getColor());
        color = new Color(255, 255, 255);
        assertEquals(color, whiteHole.getColor());
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
        testPlanet.updateVelocity(testPlanets, gravitationalConstant);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals((float) 1, testPlanet.getDXPos());
        assertEquals((float) 1, testPlanet.getDYPos());
    }

    @Test 
    void testUpdateVelocityMultipleTimes() {
        testPlanet.updateVelocity(testPlanets, gravitationalConstant);
        otherPlanet.setMass(1000);
        otherPlanet.setXPos(100);
        otherPlanet.setYPos(100);
        double distance = Math.sqrt((100 * 100) + (100 * 100));
        double gravity = (1000) / (distance * distance);
        double xvelocity = 1 + gravity * 1000 / distance / 10;
        double yvelocity = (gravity * 1000 / distance) / 10 + 2;
        testPlanet.updateVelocity(testPlanets, gravitationalConstant);
        assertEquals((float) 0, testPlanet.getXPos());
        assertEquals((float) 0, testPlanet.getYPos());
        assertEquals(xvelocity, testPlanet.getDXPos());
        assertEquals(yvelocity, testPlanet.getDYPos());
    }

    @Test 
    void testEqualsAndHashCode() {
        Planet samePlanet = new Planet(10, 0, 0, 0, 0);
        Planet almostSamePlanet = new Planet(10, 0, 1, 0, 0);
        Planet differentPlanet = new Planet(10, 1, 0, 0, 0);
        Planet heavyPlanet = new Planet(100, 1, 0, 0, 0);
        Planet notAPlanet = null;
        NBodySimulation notEvenCloseToBeingAPlanet = new NBodySimulation(gravitationalConstant);
        assertTrue(!samePlanet.equals(differentPlanet));
        assertTrue(!samePlanet.equals(almostSamePlanet));
        assertTrue(!samePlanet.equals(notAPlanet));
        assertTrue(!samePlanet.equals(notEvenCloseToBeingAPlanet));
        assertTrue(!samePlanet.equals(heavyPlanet));
        assertTrue(samePlanet.equals(samePlanet));
        assertEquals(samePlanet.hashCode(), testPlanet.hashCode());
        assertNotEquals(differentPlanet.hashCode(), testPlanet.hashCode());
    }
}
