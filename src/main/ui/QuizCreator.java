package ui;

import model.Quiz;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizCreator extends QuizUser {
    private int numProblems;
    private Quiz newQuiz;

    public QuizCreator(String name, int year, int numProblems) {
        super(name, year);
        this.numProblems = numProblems;
    }

    @Override
    public void begin() {
    }

    @Override
    public void createReport() {

    }
}
