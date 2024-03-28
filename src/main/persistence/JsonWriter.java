package persistence;

import model.Event;
import model.EventLog;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a JsonWriter to save file from the local storage
// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private final String filePath;
    private PrintWriter writer;

    /*
     * REQUIRES: filePath should be an existing json file in the data directory
     * EFFECTS: initializes a JsonWriter object and
     *          sets the target filePath
     */
    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    /*
     * EFFECTS: initializes a new PrintWriter object with the target file specified
     */
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(new File(this.filePath));
    }

    /*
     * EFFECTS: closes the writer, done upon writing to the json file
     */
    public void closeFile() {
        writer.close();
    }

    /*
     * MODIFIES: jsonArray should be a non-empty array of quizzes,
     *           in which have been converted to a JSON format
     * EFFECTS: prints and writes to the file specified by filePath
     */
    public void saveToFile(JSONArray json) {
        writer.print(json.toString(4));
        EventLog el = EventLog.getInstance();
        el.logEvent(new Event("Data saved to " + this.filePath));
    }
}
