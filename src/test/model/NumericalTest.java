package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumericalTest {
    private Numerical problem;

    @BeforeEach
    public void setup() {
        problem = new Numerical(84,"What is 42 times two", 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(84, problem.getCorrectAnswer());
        assertEquals("What is 42 times two", problem.getQuestion());
        assertEquals(5, problem.getPoints());
        assertFalse(problem.getCompleted());
        assertEquals("Numerical",problem.getFormat());
    }

    @Test
    public void testSetHint() {
        problem.setHint("Remember the multiplication table!");
        assertEquals("Remember the multiplication table!",problem.getHint());
        problem.setHint("What does times two mean?");
        assertEquals("What does times two mean?", problem.getHint());
        problem.setHint("You're doing great!");
        assertEquals("You're doing great!", problem.getHint());
    }

    @Test
    public void testSetCompleted() {
        problem.setCompleted(true);
        assertTrue(problem.getCompleted());
        problem.setCompleted(false);
        assertFalse(problem.getCompleted());
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
