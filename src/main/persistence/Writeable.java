package persistence;

import org.json.JSONObject;

// Represents an interface for classes that should be able to
// convert itself into a JSON format
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writeable {
    JSONObject toJson();
}
