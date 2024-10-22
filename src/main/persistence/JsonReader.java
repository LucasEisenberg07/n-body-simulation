package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads NBodySimulation from JSON data stored in file
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads NBodySimulation from file and returns it;
    // throws IOException if an error occurs reading data from file
    public NBodySimulation read() throws IOException {
        return null; //stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; //stub
    }

    // EFFECTS: parses NBodySimulation from JSON object and returns it
    private NBodySimulation parseNBodySimulation(JSONObject jsonObject) {
        return null; //stub
    }

    // MODIFIES: NBodySimulation
    // EFFECTS: parses planets from JSON object and adds them to NBodySimulation
    private void addPlanets(NBodySimulation NBS, JSONObject jsonObject) {
        //stub
    }

    // MODIFIES: NBodySimulation
    // EFFECTS: parses planet from JSON object and adds it to NBodySimulation
    private void addPlanet(NBodySimulation NBS, JSONObject jsonObject) {
        //stub
    }
}
