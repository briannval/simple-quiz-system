package persistence;

import org.json.JSONArray;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private final String filePath;
    private PrintWriter writer;

    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(this.filePath);
    }

    public void closeFile() {
        writer.close();
    }

    public void saveToFile(JSONArray json) {
        writer.print(json.toString(4));
    }
}
