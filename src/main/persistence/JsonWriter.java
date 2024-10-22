package persistence;
import model.*;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(NBodySimulation NBS) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // stub
    }
}
