package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test methods for the Numerical class
public class NumericalTest {
    private Numerical problem;
    private Numerical secondProblem;

    @BeforeEach
    public void setup() {
        problem = new Numerical(84,"What is 42 times two", 5);
        secondProblem = new Numerical(2, "What is 1+1?", 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(84, problem.getCorrectAnswer());
        assertEquals("What is 42 times two", problem.getQuestion());
        assertEquals(5, problem.getPoints());
        assertFalse(problem.getCompleted());
        assertEquals("Numerical",problem.getFormat());
        assertEquals(2, secondProblem.getCorrectAnswer());
        assertEquals("What is 1+1?", secondProblem.getQuestion());
        assertEquals(5, secondProblem.getPoints());
        assertFalse(problem.getCompleted());
    }

    @Test
    public void testSetCompleted() {
        problem.setCompleted(true);
        assertTrue(problem.getCompleted());
        problem.setCompleted(false);
        assertFalse(problem.getCompleted());
        secondProblem.setCompleted(true);
        assertTrue(secondProblem.getCompleted());
        secondProblem.setCompleted(false);
        assertFalse(secondProblem.getCompleted());
    }

    @Test
    public void testCheckAnswer() {
        assertFalse(problem.checkAnswer(83)); // 1 less
        assertTrue(problem.checkAnswer(84)); // equal
        assertFalse(problem.checkAnswer(85)); // 1 more
        assertFalse(problem.checkAnswer(1));
        assertFalse(problem.checkAnswer(99));
    }
}
