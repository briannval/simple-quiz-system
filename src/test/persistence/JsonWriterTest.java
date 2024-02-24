package persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFileName() {
        try {
            JsonWriter writer = new JsonWriter("./data/<>??::::test.json");
            writer.openFile();
            fail("Expected IOException");
        } catch (IOException e) {
            // pass test
        }
    }
}

