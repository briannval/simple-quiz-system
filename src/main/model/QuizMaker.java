package model;

import java.util.ArrayList;
import java.util.List;

public class QuizMaker {
    private String name;
    private int numMultipleChoice;
    private int numNumerical;
    private int numTrueFalse;
    private List<Question> questions;

    public QuizMaker(String name, int numMultipleChoice, int numNumerical, int numTrueFalse) {
        this.name = name;
        this.numMultipleChoice = numMultipleChoice;
        this.numNumerical = numNumerical;
        this.numTrueFalse = numTrueFalse;
        this.questions = new ArrayList<>();
    }

    public void addMultipleChoiceQuestion(char correctAnswer, String question, int points, List<String> choices) {
        questions.add(new MultipleChoice(correctAnswer, question, points, choices));
    }

    public void addTrueFalseQuestion(String question, boolean correctAnswer, int points) {
        questions.add(new TrueFalse(question, correctAnswer, points));
    }

    public void addNumericalQuestion(int correctAnswer, String question, int points) {
        questions.add(new Numerical(correctAnswer, question, points));
    }

    public Question getQuestionInList(int index) {
        return this.questions.get(index);
    }

    public int getNumMultipleChoice() {
        return this.numMultipleChoice;
    }

    public int getNumNumerical() {
        return this.numNumerical;
    }

    public int getNumTrueFalse() {
        return this.numTrueFalse;
    }

    public Quiz quizGenerator() {
        return new Quiz(this.questions);
    }
}
