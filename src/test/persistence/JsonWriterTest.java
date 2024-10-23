package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest{
    @Test
    void testWriterInvalidFile() {
        try {
            NBodySimulation NBS = new NBodySimulation(1);
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
            NBodySimulation NBS = new NBodySimulation(1);
            JsonWriter writer = new JsonWriter("./data/testNBodySimulationNoPlanets.json");
            writer.open();
            writer.write(NBS);
            writer.close();

            JsonReader reader = new JsonReader("./data/testNBodySimulationNoPlanets.json");
            NBS = reader.read();
            assertEquals(1, NBS.getGravitationalConstant());
            assertEquals(0, NBS.numPlanets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultiplePlanets() {
        try {
            NBodySimulation NBS = new NBodySimulation(1);
            Planet planet1 = new Planet(100,0,0,0,0);
            Planet planet2 = new Planet(10,10,0,0,5);
            NBS.addPlanet(100,0,0);
            NBS.addPlanetWithVelocity(10,10,0,0,5);
            JsonWriter writer = new JsonWriter("./data/testWriterMultiplePlanets.json");
            writer.open();
            writer.write(NBS);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMultiplePlanets.json");
            NBS = reader.read();
            assertEquals(1, NBS.getGravitationalConstant());
            assertEquals(2, NBS.numPlanets());
            assertEquals(planet1, NBS.getPlanet(0));
            assertEquals(planet2, NBS.getPlanet(1));
            assertEquals(5, NBS.getPlanet(1).getDYPos());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}