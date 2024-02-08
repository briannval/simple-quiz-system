package ui;

import model.Quiz;

public class QuizStarter extends QuizUser {
    private Quiz quiz;

    public QuizStarter(String name, int year, Quiz quiz) {
        super(name, year);
        this.quiz = quiz;
    }

    @Override
    public boolean begin() {
        return false;
    }

    @Override
    public void createReport() {

    }
}
