package model;

import model.MultipleChoice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiceTest {
    MultipleChoice problem;
    List<String> choices;

    @BeforeEach
    public void setup() {
        this.choices = new ArrayList<>(Arrays.asList("Java","Python","Assembly","C#"));
        problem = new MultipleChoice('C',"Which one is low-level language?",5,this.choices);
    }

    @Test
    public void testConstructor() {
        assertEquals("Which one is low-level language?",problem.getQuestion());
        assertEquals('C',problem.getCorrectAnswer());
        assertEquals(5, problem.getPoints());
        assertEquals(choices,problem.getChoices());
        assertFalse(problem.getCompleted());
        assertEquals("Multiple Choice",problem.getFormat());
    }

    @Test
    public void testSetCompleted() {
        problem.setCompleted(true);
        assertTrue(problem.getCompleted());
        problem.setCompleted(false);
        assertFalse(problem.getCompleted());
    }

    @Test
    public void testGetCorrectChoice() {
        assertEquals(this.choices.get(2),problem.getCorrectChoice());
    }

    @Test
    public void testCheckAnswer() {
        assertFalse(problem.checkAnswer('A'));
        assertFalse(problem.checkAnswer('B'));
        assertFalse(problem.checkAnswer('D'));
        assertTrue(problem.checkAnswer('C'));
    }
}

