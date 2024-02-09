package model;

import java.util.ArrayList;
import java.util.List;

public class QuizMaker {
    private String name;
    private List<Question> questions;
    private int numQuestion;

    public QuizMaker(String name, int numQuestion) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.numQuestion = numQuestion;
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

    public int getNumQuestion() {
        return this.numQuestion;
    }

    public String getName() {
        return this.name;
    }

    public Quiz quizGenerator() {
        return new Quiz(this.questions,this.name);
    }
}
