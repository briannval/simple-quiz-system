package persistence;

import model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private JsonReader jsonReader;

    @Test
    public void testNonexistentFile() {
        this.jsonReader = new JsonReader("./data/reader-test/nonExistentFile.json");
        try {
            this.jsonReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderMultipleQuestions() {
        this.jsonReader = new JsonReader("./data/reader-test/testReaderMultipleQuestions.json");
        try {
            List<Quiz> quizzes = this.jsonReader.read();

            assertEquals(1, quizzes.size());
            // testing number of quizzes

            assertEquals(3, quizzes.get(0).getAllQuestions().size());
            // testing number of questions

            assertEquals("Test Reader Multiple", quizzes.get(0).getName());
            // testing quiz name

            assertEquals(3, quizzes.get(0).getStars());
            // testing number of stars

            assertEquals("2+2",quizzes.get(0).getOneQuestion(0).getQuestion());
            assertEquals("this is not a test",quizzes.get(0).getOneQuestion(1).getQuestion());
            assertEquals("What is this course?",quizzes.get(0).getOneQuestion(2).getQuestion());
            // testing questions

            assertEquals(4, quizzes.get(0).getOneQuestion(0).getPoints());
            assertEquals(5, quizzes.get(0).getOneQuestion(1).getPoints());
            assertEquals(5, quizzes.get(0).getOneQuestion(2).getPoints());
            // testing points

            assertTrue(quizzes.get(0).getOneQuestion(0).checkAnswer(4));
            assertTrue(quizzes.get(0).getOneQuestion(1).checkAnswer(true));
            assertTrue(quizzes.get(0).getOneQuestion(2).checkAnswer('A'));
            // testing answers

        } catch (IOException e) {
            fail("IOException not expected");
        }
    }

    @Test
    public void testReaderAllQuestions() {
        this.jsonReader = new JsonReader("./data/reader-test/testReaderAllQuestions.json");
        try {
            List<Quiz> quizzes = this.jsonReader.read();

            assertEquals(3, quizzes.size());
            // testing number of quizzes

            assertEquals("Test Reader 1", quizzes.get(0).getName());
            assertEquals("Test Reader 2", quizzes.get(1).getName());
            assertEquals("Test Reader 3", quizzes.get(2).getName());
            // testing quiz names

            assertEquals(3, quizzes.get(0).getStars());
            assertEquals(1, quizzes.get(1).getStars());
            assertEquals(3, quizzes.get(2).getStars());
            // testing quiz stars

            assertEquals(1, quizzes.get(0).getAllQuestions().size());
            assertEquals(quizzes.get(1).getAllQuestions().size(), quizzes.get(0).getAllQuestions().size());
            assertEquals(quizzes.get(0).getAllQuestions().size(), quizzes.get(2).getAllQuestions().size());
            // testing number of questions

            assertFalse(quizzes.get(0).getOneQuestion(0).checkAnswer(5));
            assertTrue(quizzes.get(1).getOneQuestion(0).checkAnswer('A'));
            assertFalse(quizzes.get(2).getOneQuestion(0).checkAnswer(false));
            // checking answers

            assertEquals(quizzes.get(0).getOneQuestion(0).getQuestion(), "2+2");
            assertEquals(quizzes.get(1).getOneQuestion(0).getPoints(), 5);
            assertEquals(quizzes.get(2).getOneQuestion(0).getFormat(), "True False");
            // checking questions
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }
    }
