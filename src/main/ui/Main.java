package ui;

import org.json.JSONObject;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

// Main class of the application
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        QuizUser main = new QuizPlayer("Brian",2020);
        main.begin(); // starts the quiz game
        // System.out.println("Test file system");
        // JSONObject json = new JSONObject();
        // json.put("hello","world");

        // JsonWriter jsonWriter = new JsonWriter("./data/test.json");
        // jsonWriter.openFile();
        // jsonWriter.saveToFile(json);
        // jsonWriter.closeFile();
    }
}
