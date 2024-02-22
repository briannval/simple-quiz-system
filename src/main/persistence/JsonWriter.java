package persistence;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private PrintWriter writer;
    private String filePath;

    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(new File(this.filePath));
    }

    public void closeFile() {
        writer.close();
    }

    public void saveToFile(JSONObject json) {
        writer.print(json.toString(4));
    }

    public void flushStream() {
        writer.flush();
    }
}
