package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestNBodySimulation {
    Planet firstPlanet;
    Planet secondPlanet;
    Planet thirdPlanet;
    NBodySimulation testNBodySimulation;
    NBodySimulation testSimulation;
    ArrayList<Planet> planets;
    float G;
    @BeforeEach
    void runBefore() {
        G = 1;
        testSimulation = new NBodySimulation(1);
        firstPlanet = new Planet(10, 0, 0);
        secondPlanet = new Planet(100, 10, 0);
        thirdPlanet = new Planet(100, 0, 10);
        planets = new ArrayList<Planet>();
        planets.add(firstPlanet);
        planets.add(secondPlanet);
        planets.add(thirdPlanet);
        testSimulation.addPlanet(10, 0, 0);
        testSimulation.addPlanet(100, 10, 0);
        testSimulation.addPlanet(100, 0, 10);
    }

    @Test 
    void testConstructorAndAddPlanet() {
        assertEquals(G, testSimulation.getG());
        assertEquals(3, testSimulation.numPlanets());
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test
    void testTickOnce() {
        testSimulation.tick(1);
        firstPlanet.updateVelocity(planets, G);
        secondPlanet.updateVelocity(planets, G);
        thirdPlanet.updateVelocity(planets, G);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test
    void testTickMultipleTimes() {
        testSimulation.tick(3);
        firstPlanet.updateVelocity(planets, G);
        secondPlanet.updateVelocity(planets, G);
        thirdPlanet.updateVelocity(planets, G);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        firstPlanet.updateVelocity(planets, G);
        secondPlanet.updateVelocity(planets, G);
        thirdPlanet.updateVelocity(planets, G);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        firstPlanet.updateVelocity(planets, G);
        secondPlanet.updateVelocity(planets, G);
        thirdPlanet.updateVelocity(planets, G);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test
    void testGetAndRemovePlanets() {
        Planet p = testSimulation.getPlanet(0);
        assertEquals(firstPlanet, p);
        testSimulation.removePlanet(0);
        p = testSimulation.getPlanet(0);
        assertEquals(secondPlanet, p);
    }
}
