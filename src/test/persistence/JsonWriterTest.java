package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            NBodySimulation simulation = new NBodySimulation(1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNoPlanets() {
        try {
            NBodySimulation simulation = new NBodySimulation(1);
            JsonWriter writer = new JsonWriter("./data/testNBodySimulationNoPlanets.json");
            writer.open();
            writer.write(simulation);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNBodySimulationNoPlanets.json");
            simulation = reader.read();
            assertEquals(1, simulation.getGravitationalConstant());
            assertEquals(0, simulation.numPlanets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultiplePlanets() {
        try {
            NBodySimulation simulation = new NBodySimulation(1);
            Planet planet1 = new Planet(100,0,0,0,0);
            Planet planet2 = new Planet(10,10,0,0,5);
            simulation.addPlanet(100,0,0);
            simulation.addPlanetWithVelocity(10,10,0,0,5, new Color(0,255,0));
            JsonWriter writer = new JsonWriter("./data/testWriterMultiplePlanets.json");
            writer.open();
            writer.write(simulation);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMultiplePlanets.json");
            simulation = reader.read();
            assertEquals(1, simulation.getGravitationalConstant());
            assertEquals(2, simulation.numPlanets());
            assertEquals(planet1, simulation.getPlanet(0));
            assertEquals(planet2, simulation.getPlanet(1));
            assertEquals(5, simulation.getPlanet(1).getDYPos());
            assertEquals(255, simulation.getPlanet(1).getColor().getGreen());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}