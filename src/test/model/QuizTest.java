package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

// Test methods for the Quiz class
public class QuizTest {
    private List<Question> questions;
    private Question sampleQuestionOne;
    private Question sampleQuestionTwo;
    private Question sampleQuestionThree;
    private Question sampleQuestionFour;
    private Question sampleQuestionFive;
    private List<String> sampleChoices;
    private Quiz test;

    @BeforeEach
    public void setup() {
        questions = new ArrayList<>();
        sampleChoices = new ArrayList<>();
        sampleChoices.add("Edison");
        sampleChoices.add("Ricky");
        sampleChoices.add("Moriarty");
        sampleChoices.add("Bond");
        this.questions.add(new MultipleChoice('A',"Who invented the light bulb?",5,this.sampleChoices));
        this.questions.add(new Numerical(85,"What is five times seventeen?",10));
        this.questions.add(new Numerical(1,"What is sin half pi?",5));
        this.questions.add(new TrueFalse("Is Kyoto the capital of Korea?",false,5));
        this.questions.add(new TrueFalse("Is Vancouver part of BC?",true,5));
        test = new Quiz(0, questions, "Brian's quiz");
    }

    @Test
    public void testConstructor() {
        assertEquals(30,test.getMaxPoints());
        assertEquals(0,test.getStars());
        assertEquals(questions,test.getAllQuestions());
        assertEquals("Brian's quiz",test.getName());
    }

    @Test
    public void testGetOneQuestion() {
        assertEquals("Who invented the light bulb?",test.getOneQuestion(0).getQuestion());
        assertEquals(10,test.getOneQuestion(1).getPoints());
        assertEquals("What is sin half pi?",test.getOneQuestion(2).getQuestion());
        assertEquals(5, test.getOneQuestion(3).getPoints());
        assertEquals("True False",test.getOneQuestion(4).getFormat());
        assertEquals(false,test.getOneQuestion(3).getCompleted());
    }

    @Test
    public void testDoQuestion() {
        Question firstQuestion = test.getOneQuestion(0);
        assertTrue(test.doQuestion(firstQuestion, 'A'));
        assertTrue(firstQuestion.getCompleted());
        Question secondQuestion = test.getOneQuestion(1);
        assertFalse(test.doQuestion(secondQuestion,80));
        assertTrue(secondQuestion.getCompleted());
        Question thirdQuestion = test.getOneQuestion(2);
        assertFalse(test.doQuestion(thirdQuestion,2));
        assertTrue(thirdQuestion.getCompleted());
        Question fourthQuestion = test.getOneQuestion(3);
        assertTrue(test.doQuestion(fourthQuestion,false));
        assertTrue(fourthQuestion.getCompleted());
        Question fifthQuestion = test.getOneQuestion(4);
        assertTrue(test.doQuestion(fifthQuestion,true));
        assertTrue(fifthQuestion.getCompleted());
    }

    @Test
    public void testStarQuizOnce() {
        test.starQuiz();
        assertEquals(1,test.getStars());
    }

    @Test
    public void testStarQuizMultipleTimes() {
        test.starQuiz();
        test.starQuiz();
        test.starQuiz();
        assertEquals(3,test.getStars());
    }
}
