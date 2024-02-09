package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Test methods for the QuizAttempt class
public class QuizAttemptTest {
    private List<Question> questions;
    private Question sampleQuestionOne;
    private Question sampleQuestionTwo;
    private Question sampleQuestionThree;
    private Question sampleQuestionFour;
    private Question sampleQuestionFive;
    private List<String> sampleChoices;
    private Quiz quiz;
    private QuizAttempt test;

    @BeforeEach
    public void setup() {
        questions = new ArrayList<>();
        sampleChoices = new ArrayList<>(Arrays.asList("Edison","Ricky","Moriarty","Bond"));
        this.questions.add(new MultipleChoice('A',"Who invented the light bulb?",5,this.sampleChoices));
        this.questions.add(new Numerical(85,"What is five times seventeen?",10));
        this.questions.add(new Numerical(1,"What is sin half pi?",5));
        this.questions.add(new TrueFalse("Is Kyoto the capital of Korea?",false,5));
        this.questions.add(new TrueFalse("Is Vancouver part of BC?",true,5));
        quiz = new Quiz(questions, "Brian's quiz");
        test = new QuizAttempt(quiz);
    }

    @Test
    public void testConstructor() {
        assertEquals(0,test.getCurrentPoints());
        assertEquals(quiz,test.getCurrentQuiz());
        assertEquals("ongoing",test.getStatus());
        assertEquals(0,test.calculatePercentage());
    }

    @Test
    public void testAttemptQuestion() {
        test.attemptQuestion(0,'A');
        assertEquals(5,test.getCurrentPoints());
        test.attemptQuestion(1,84);
        assertEquals(5,test.getCurrentPoints());
        test.attemptQuestion(2,1);
        assertEquals(10,test.getCurrentPoints());
        test.attemptQuestion(3,true);
        assertEquals(10,test.getCurrentPoints());
        test.attemptQuestion(4,true);
        assertEquals(15,test.getCurrentPoints());
    }

    @Test
    public void testCalculatePercentage() {
        test.attemptQuestion(0,'A');
        assertEquals(16, test.calculatePercentage());
        test.attemptQuestion(1,85);
        assertEquals(50, test.calculatePercentage());
        test.attemptQuestion(2,1);
        assertEquals(66, test.calculatePercentage());
        test.attemptQuestion(3,false);
        assertEquals(83, test.calculatePercentage());
        test.attemptQuestion(4,true);
        assertEquals(100, test.calculatePercentage());
    }

    @Test
    public void testDeterminePass() {
        test.attemptQuestion(0,'A');
        assertFalse(test.determinePass());
        test.attemptQuestion(1,85);
        assertFalse(test.determinePass());
        test.attemptQuestion(2,1);
        assertFalse(test.determinePass());
        test.attemptQuestion(3,false);
        assertTrue(test.determinePass());
        test.attemptQuestion(4,true);
        assertTrue(test.determinePass());
    }

    @Test
    public void generateReportWithFail() {
        test.attemptQuestion(0,'A');
        test.attemptQuestion(1,80);
        test.attemptQuestion(2,2);
        test.attemptQuestion(3,true);
        test.attemptQuestion(4,false);
        assertEquals("Try harder next time! Your score is 16",test.generateReport());
    }

    @Test
    public void generateReportWithPass() {
        test.attemptQuestion(0,'A');
        test.attemptQuestion(1,85);
        test.attemptQuestion(2,1);
        test.attemptQuestion(3,false);
        test.attemptQuestion(4,false);
        assertEquals("Congratulations! You have passed with a score of 83",test.generateReport());
    }
}
