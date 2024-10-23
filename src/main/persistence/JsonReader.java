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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNBodySimulation(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses NBodySimulation from JSON object and returns it
    private NBodySimulation parseNBodySimulation(JSONObject jsonObject) {
        Float gravitationalConstant = jsonObject.getFloat("gravitationalConstant");
        NBodySimulation simulation = new NBodySimulation(gravitationalConstant);
        addPlanets(simulation, jsonObject);
        return simulation;
    }

    // MODIFIES: NBodySimulation
    // EFFECTS: parses planets from JSON object and adds them to NBodySimulation
    private void addPlanets(NBodySimulation simulation, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("planets");
        for (Object json : jsonArray) {
            JSONObject nextPlanet = (JSONObject) json;
            addPlanet(simulation, nextPlanet);
        }
    }

    // MODIFIES: NBodySimulation
    // EFFECTS: parses planet from JSON object and adds it to NBodySimulation
    private void addPlanet(NBodySimulation simulation, JSONObject jsonObject) {
        Float xpos = jsonObject.getFloat("xpos");
        Float ypos = jsonObject.getFloat("ypos");
        Float dxpos = jsonObject.getFloat("dxpos");
        Float dypos = jsonObject.getFloat("dypos");
        int mass = jsonObject.getInt("mass");
        simulation.addPlanetWithVelocity(mass, xpos, ypos, dxpos, dypos);
    }
}
