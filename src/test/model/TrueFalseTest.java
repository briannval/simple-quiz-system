package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrueFalseTest {
    public TrueFalse firstProblem;
    public TrueFalse secondProblem;

    @BeforeEach
    public void setup() {
        firstProblem = new TrueFalse("Gravity was discovered by Einstein",false,3);
        secondProblem = new TrueFalse("Java is a robust language",true,3);
    }

    @Test
    public void testConstructor() {
        assertFalse(firstProblem.getCorrectAnswer());
        assertTrue(secondProblem.getCorrectAnswer());
        assertEquals("Gravity was discovered by Einstein",firstProblem.getQuestion());
        assertEquals("Java is a robust language",secondProblem.getQuestion());
        assertEquals(firstProblem.getPoints(),secondProblem.getPoints());
        assertEquals(firstProblem.getPoints(),3);
        assertEquals(secondProblem.getPoints(),3);
        assertFalse(firstProblem.getCompleted());
        assertFalse(secondProblem.getCompleted());
        assertEquals("True False",firstProblem.getFormat());
        assertEquals("True False",secondProblem.getFormat());
    }

    @Test
    public void testSetCompleted() {
        firstProblem.setCompleted(true);
        assertTrue(firstProblem.getCompleted());
        firstProblem.setCompleted(false);
        assertFalse(firstProblem.getCompleted());
        secondProblem.setCompleted(true);
        assertTrue(secondProblem.getCompleted());
        secondProblem.setCompleted(false);
        assertFalse(secondProblem.getCompleted());
    }

    @Test
    public void testCheckAnswer() {
        assertFalse(firstProblem.checkAnswer(true));
        assertTrue(firstProblem.checkAnswer(false));
        assertTrue(secondProblem.checkAnswer(true));
        assertFalse(secondProblem.checkAnswer(false));
    }

    @Test
    public void testGetAnswerStatement() {
        firstProblem.setExplanation("he did not witness the apple");
        secondProblem.setExplanation("strong type checking and exception handling");
        assertEquals("This statement is incorrect because he did not witness the apple",
                firstProblem.getAnswerStatement());
        assertEquals("This statement is correct because strong type checking and exception handling",
                secondProblem.getAnswerStatement());
    }

}
