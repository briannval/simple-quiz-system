package model;

import java.util.ArrayList;
import java.util.List;

// Represents a quiz maker class with different types of questions
public class QuizMaker {
    private final String name;
    private final List<Question> questions;
    private final int numQuestion;

    /*
     * REQUIRES: quiz name has to be non-zero length
     *           and numQuestion > 0
     * EFFECTS: starts creating a new quiz by generating an empty question list
     */
    public QuizMaker(String name, int numQuestion) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.numQuestion = numQuestion;
    }

    /*
     * REQUIRES: valid inputs to a multiple choice question
     * MODIFIES: this
     * EFFECTS: adds a multiple choice question to the question list
     */
    public void addMultipleChoiceQuestion(char correctAnswer, String question, int points, List<String> choices) {
        questions.add(new MultipleChoice(correctAnswer, question, points, choices));
    }

    /*
     * REQUIRES: valid inputs to a true false question
     * MODIFIES: this
     * EFFECTS: adds a true false question to the question list
     */
    public void addTrueFalseQuestion(String question, boolean correctAnswer, int points) {
        questions.add(new TrueFalse(question, correctAnswer, points));
    }

    /*
     * REQUIRES: valid inputs to a numerical question
     * MODIFIES: this
     * EFFECTS: adds a numerical question to the question list
     */
    public void addNumericalQuestion(int correctAnswer, String question, int points) {
        questions.add(new Numerical(correctAnswer, question, points));
    }

    /*
     * EFFECTS: gets a specific question by index in the question list
     */
    public Question getQuestionInList(int index) {
        return this.questions.get(index);
    }

    public int getNumQuestion() {
        return this.numQuestion;
    }

    public String getName() {
        return this.name;
    }

    /*
     * REQUIRES: length of questions has to be numQuestion
     * EFFECTS: generates a new quiz with the current question
     */
    public Quiz quizGenerator() {
        return new Quiz(this.questions,this.name);
    }
}
