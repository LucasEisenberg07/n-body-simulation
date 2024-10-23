package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest{
    @BeforeEach 
    void setUp() {
        NBodySimulation NBS = new NBodySimulation(1);
        JsonWriter writer = new JsonWriter("./data/testNBodySimulationNoPlanets.json");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("File testNBodySimulationNoPlanets not created");
        }
        writer.write(NBS);
        writer.close();
        NBS = new NBodySimulation(1);
        NBS.addPlanet(100,0,0);
        NBS.addPlanetWithVelocity(10,10,0,0,5);
        writer = new JsonWriter("./data/testWriterMultiplePlanets.json");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail("File testWriterMultiplePlanets not created");
        }
        writer.write(NBS);
        writer.close();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            NBodySimulation NBS = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testNBodySimulationNoPlanets.json");
        try {
            NBodySimulation NBS = reader.read();
            assertEquals(1, NBS.getGravitationalConstant());
            assertEquals(0, NBS.numPlanets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterMultiplePlanets.json");
        try {
            Planet planet1 = new Planet(100,0,0,0,0);
            Planet planet2 = new Planet(10,10,0,0,5);
            NBodySimulation NBS = reader.read();
            NBS = reader.read();
            assertEquals(1, NBS.getGravitationalConstant());
            assertEquals(2, NBS.numPlanets());
            assertEquals(planet1, NBS.getPlanet(0));
            assertEquals(planet2, NBS.getPlanet(1));
            assertEquals(5, NBS.getPlanet(1).getDYPos());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}