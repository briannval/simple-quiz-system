package persistence;

import model.*;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonWriterTest {

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Quiz> quizzes;
    private List<Question> questions;
    private String path;

    @BeforeEach
    public void setup() {
        this.quizzes = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    // helper write method for paths
    public void helper() {
        try {
            jsonWriter = new JsonWriter(path);
            jsonWriter.openFile();

            JSONArray jsonArray = new JSONArray();
            for (Quiz quiz : quizzes) {
                jsonArray.put(quiz.toJson());
            }
            jsonWriter.saveToFile(jsonArray);

            jsonWriter.closeFile();
        } catch (FileNotFoundException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testWriterMultipleChoice() {
        // creating the quiz
        List<String> firstChoices = new ArrayList<>(Arrays.asList("Java","Python","Assembly","C#"));
        Question firstProblem = new MultipleChoice('C',"Which one is low-level language?",5,firstChoices);
        List<String> secondChoices = new ArrayList<>(Arrays.asList("abc","def","ghi","jkl"));
        Question secondProblem = new MultipleChoice('A', "What are the first three letters?", 5, secondChoices);
        this.questions.add(firstProblem);
        this.questions.add(secondProblem);
        quizzes.add(new Quiz(5, this.questions, "Test Multiple Choice"));

        // adding JSONWriter
        this.path = "./data/writer-test/testWriterMultipleChoice.json";
        helper();

        // testing with JSONReader
        jsonReader = new JsonReader(this.path);
        try {
            List<Quiz> res = jsonReader.read();
            assertEquals(1, res.size());
            assertEquals(res.get(0).getOneQuestion(0).getPoints(), res.get(0).getOneQuestion(1).getPoints());
            assertFalse(res.get(0).getOneQuestion(0).checkAnswer('D'));
            assertTrue(res.get(0).getOneQuestion(1).checkAnswer('A'));
            assertEquals("Which one is low-level language?", res.get(0).getOneQuestion(0).getQuestion());
            assertEquals("What are the first three letters?", res.get(0).getOneQuestion(1).getQuestion());
            assertEquals(5, res.get(0).getStars());
            assertEquals("Test Multiple Choice", res.get(0).getName());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testWriterTrueFalse() {
        // creating the quiz
        Question firstProblem = new TrueFalse("Gravity was discovered by Einstein",false,3);
        Question secondProblem = new TrueFalse("Java is a robust language",true,3);
        quizzes.add(new Quiz(25, Arrays.asList(firstProblem, secondProblem), "Test True False"));

        // adding JSONWriter
        this.path = "./data/writer-test/testWriterTrueFalse.json";
        helper();

        // testing with JSONReader
        jsonReader = new JsonReader(this.path);
        try {
            List<Quiz> res = jsonReader.read();
            assertEquals(1, res.size());
            assertEquals(res.get(0).getOneQuestion(0).getPoints(), res.get(0).getOneQuestion(1).getPoints());
            assertFalse(res.get(0).getOneQuestion(0).checkAnswer(true));
            assertFalse(res.get(0).getOneQuestion(1).checkAnswer(false));
            assertEquals(25, res.get(0).getStars());
            assertEquals("Test True False", res.get(0).getName());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testWriterNumerical() {
        // creating the quiz
        Question firstProblem = new Numerical(84,"What is 42 times two", 5);
        Question secondProblem = new Numerical(2, "What is 1+1?", 5);
        quizzes.add(new Quiz(10, Arrays.asList(firstProblem, secondProblem), "Test Numerical"));

        // adding JSONWriter
        this.path = "./data/writer-test/testWriterNumerical.json";
        helper();

        // testing with JSONReader
        jsonReader = new JsonReader(this.path);
        try {
            List<Quiz> res = jsonReader.read();
            assertEquals(1, res.size());
            assertEquals(res.get(0).getOneQuestion(0).getPoints(), res.get(0).getOneQuestion(1).getPoints());
            assertTrue(res.get(0).getOneQuestion(0).checkAnswer(84));
            assertTrue(res.get(0).getOneQuestion(1).checkAnswer(2));
            assertEquals(10, res.get(0).getStars());
            assertEquals("Test Numerical", res.get(0).getName());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

}

