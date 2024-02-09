package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

// Test methods for the QuizMaker class
public class QuizMakerTest {
    private QuizMaker test;

    @BeforeEach
    public void setup() {
        test = new QuizMaker("Brian's Quiz", 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(8, test.getNumQuestion());
        assertEquals("Brian's Quiz", test.getName());
    }

    @Test
    public void testAddMultipleChoiceQuestion() {
        test.addMultipleChoiceQuestion('B',"Who is a CS professor?",
                10,new ArrayList<>(Arrays.asList("Robert","Gregor","John","Mark")));
        assertEquals(10,test.getQuestionInList(0).getPoints());
        assertTrue(test.getQuestionInList(0).checkAnswer('B'));
        test.addMultipleChoiceQuestion('C', "What is the best course at UBC?",
                20, new ArrayList<>(Arrays.asList("ECON","MATH","CPSC","SCIE")));
        assertEquals("What is the best course at UBC?",test.getQuestionInList(1).getQuestion());
        assertFalse(test.getQuestionInList(1).checkAnswer('B'));
    }

    @Test
    public void testAddNumericalQuestion() {
        test.addNumericalQuestion(5, "What is 2+3?",10);
        assertEquals("What is 2+3?",test.getQuestionInList(0).getQuestion());
        test.addNumericalQuestion(20, "What is 10+10?", 20);
        assertEquals(20, test.getQuestionInList(1).getPoints());
        test.addNumericalQuestion(10, "What is 2x5?", 20);
        assertTrue(test.getQuestionInList(2).checkAnswer(10));
    }

    @Test
    public void testAddTrueFalseQuestion() {
        test.addTrueFalseQuestion("CPSC 210 is a great course.",true, 20);
        assertEquals("CPSC 210 is a great course.", test.getQuestionInList(0).getQuestion());
        test.addTrueFalseQuestion("Prof. Felix is amazing!", true, 30);
        assertTrue(test.getQuestionInList(1).checkAnswer(true));
        test.addTrueFalseQuestion("Not everyone can learn how to code.", false, 40);
        assertEquals(40, test.getQuestionInList(2).getPoints());
    }

    @Test
    public void testQuizGenerator() {
        test.addMultipleChoiceQuestion('B',"Who is a CS professor?",
                10,new ArrayList<>(Arrays.asList("Robert","Gregor","John","Mark")));
        test.addMultipleChoiceQuestion('C', "What is the best course at UBC?",
                20, new ArrayList<>(Arrays.asList("ECON","MATH","CPSC","SCIE")));
        test.addNumericalQuestion(5, "What is 2+3?",10);
        test.addNumericalQuestion(20, "What is 10+10?", 20);
        test.addNumericalQuestion(10, "What is 2x5?", 20);
        test.addTrueFalseQuestion("CPSC 210 is a great course.",true, 20);
        test.addTrueFalseQuestion("Prof. Felix is amazing!", true, 30);
        test.addTrueFalseQuestion("Not everyone can learn how to code.", false, 40);
        Quiz res = test.quizGenerator();
        assertEquals(170,res.getMaxPoints());
        assertTrue(res.doQuestion(res.getOneQuestion(1),'C'));
        assertTrue(res.getOneQuestion(1).getCompleted());
        assertEquals(20, res.getOneQuestion(3).getPoints());
        assertEquals("Prof. Felix is amazing!", res.getOneQuestion(6).getQuestion());
    }
}
