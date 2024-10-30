package persistence;

import org.json.JSONObject;
// Citation: some code taken from JsonSerializationDemo 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Not currently used, but might be useful in the future for future persistence

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
