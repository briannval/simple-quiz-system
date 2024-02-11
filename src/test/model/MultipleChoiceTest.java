package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Test methods for the MultipleChoice class
public class MultipleChoiceTest {
    MultipleChoice problem;
    MultipleChoice secondProblem;
    List<String> choices;
    List<String> secondChoices;

    @BeforeEach
    public void setup() {
        this.choices = new ArrayList<>(Arrays.asList("Java","Python","Assembly","C#"));
        this.problem = new MultipleChoice('C',"Which one is low-level language?",5,this.choices);
        this.secondChoices = new ArrayList<>(Arrays.asList("abc","def","ghi","jkl"));
        this.secondProblem = new MultipleChoice('A', "What are the first three letters?", 5, this.secondChoices);
    }

    @Test
    public void testConstructor() {
        assertEquals("Which one is low-level language?",problem.getQuestion());
        assertEquals('C',problem.getCorrectAnswer());
        assertEquals(5, problem.getPoints());
        assertEquals(choices,problem.getChoices());
        assertFalse(problem.getCompleted());
        assertEquals("Multiple Choice",problem.getFormat());
        assertEquals("What are the first three letters?", secondProblem.getQuestion());
        assertEquals('A', secondProblem.getCorrectAnswer());
        assertEquals(5, secondProblem.getPoints());
        assertEquals(secondChoices, secondProblem.getChoices());
        assertFalse(secondProblem.getCompleted());
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
    public void testCheckAnswer() {
        assertFalse(problem.checkAnswer('A'));
        assertFalse(problem.checkAnswer('B'));
        assertFalse(problem.checkAnswer('D'));
        assertTrue(problem.checkAnswer('C'));
        assertTrue(secondProblem.checkAnswer('A'));
        assertFalse(secondProblem.checkAnswer('B'));
        assertFalse(secondProblem.checkAnswer('C'));
        assertFalse(secondProblem.checkAnswer('D'));
    }

    @Test
    public void testSetQuestionOnce() {
        problem.setQuestion("I am setting this question once");
        assertEquals("I am setting this question once", problem.getQuestion());
    }

    @Test
    public void testSetQuestionMultipleTimes() {
        problem.setQuestion("I am setting this question once");
        assertEquals("I am setting this question once", problem.getQuestion());
        problem.setQuestion("I am setting this question twice");
        assertEquals("I am setting this question twice", problem.getQuestion());
        problem.setQuestion("I am setting this question thrice");
        assertEquals("I am setting this question thrice", problem.getQuestion());
    }

    @Test
    public void testSetPointsOnce() {
        problem.setPoints(20);
        assertEquals(20,problem.getPoints());
    }

    @Test
    public void testSetPointsMultipleTimes() {
        problem.setPoints(20);
        assertEquals(20,problem.getPoints());
        problem.setPoints(30);
        assertEquals(30,problem.getPoints());
        problem.setPoints(40);
        assertEquals(40,problem.getPoints());
    }

    @Test
    public void testGetChoices() {
        assertEquals(this.choices, problem.getChoices());
        assertEquals(this.secondChoices, secondProblem.getChoices());
    }
}

