package model;

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
    float gravitationalConstant;

    @BeforeEach
    void runBefore() {
        gravitationalConstant = 1;
        testSimulation = new NBodySimulation(1);
        firstPlanet = new Planet(10, 0, 0, 0, 0);
        secondPlanet = new Planet(100, 100, 0, 0, 0);
        thirdPlanet = new Planet(100, 0, 100, 0, 0);
        planets = new ArrayList<Planet>();
        planets.add(firstPlanet);
        planets.add(secondPlanet);
        planets.add(thirdPlanet);
        testSimulation.addPlanet(10, 0, 0);
        testSimulation.addPlanet(100, 100, 0);
        testSimulation.addPlanet(100, 0, 100);
    }

    @Test 
    void testConstructor() {
        assertEquals(gravitationalConstant, testSimulation.getGravitationalConstant());
        assertEquals(3, testSimulation.numPlanets());
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test 
    void testAddPlanets() {
        testSimulation.addPlanet(10, 100, 5);
        testSimulation.addPlanetWithVelocity(10, 10, 5, 100, 10);

        Planet normalPlanet = new Planet(10, 100, 5, 0, 0);
        Planet speedyPlanet = new Planet(10, 10, 5, 0, 0);
        speedyPlanet.setDXPos(100);
        speedyPlanet.setDYPos(10);
        assertEquals(normalPlanet, testSimulation.getPlanet(3));
        assertEquals(speedyPlanet, testSimulation.getPlanet(4));
    }

    @Test
    void testTickOnce() {
        testSimulation.tick(1);
        firstPlanet.updateVelocity(planets, gravitationalConstant);
        secondPlanet.updateVelocity(planets, gravitationalConstant);
        thirdPlanet.updateVelocity(planets, gravitationalConstant);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test
    void testCollision() {
        gravitationalConstant = 1;
        testSimulation = new NBodySimulation(1);
        firstPlanet = new Planet(200, 0, 0, 0, 0);
        testSimulation.addPlanet(100, 10, 0);
        testSimulation.addPlanet(100, -10, 0);
        testSimulation.tick(1);
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
    }

    @Test
    void testTickMultipleTimes() {
        testSimulation.tick(3);
        firstPlanet.updateVelocity(planets, gravitationalConstant);
        secondPlanet.updateVelocity(planets, gravitationalConstant);
        thirdPlanet.updateVelocity(planets, gravitationalConstant);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        firstPlanet.updateVelocity(planets, gravitationalConstant);
        secondPlanet.updateVelocity(planets, gravitationalConstant);
        thirdPlanet.updateVelocity(planets, gravitationalConstant);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        firstPlanet.updateVelocity(planets, gravitationalConstant);
        secondPlanet.updateVelocity(planets, gravitationalConstant);
        thirdPlanet.updateVelocity(planets, gravitationalConstant);
        firstPlanet.updatePos();
        secondPlanet.updatePos();
        thirdPlanet.updatePos();
        assertEquals(firstPlanet, testSimulation.getPlanet(0));
        assertEquals(secondPlanet, testSimulation.getPlanet(1));
        assertEquals(thirdPlanet, testSimulation.getPlanet(2));
    }

    @Test
    void testGetAndRemovePlanets() {
        testSimulation.setGravitationalConstant(5);
        assertEquals(testSimulation.getGravitationalConstant(), 5);
        Planet p = testSimulation.getPlanet(0);
        assertEquals(firstPlanet, p);
        testSimulation.removePlanet(0);
        p = testSimulation.getPlanet(0);
        assertEquals(secondPlanet, p);
    }
}
