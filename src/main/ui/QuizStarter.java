package ui;

import model.Quiz;
import model.QuizAttempt;
import model.Question;

import java.util.Scanner;
import java.util.List;

public class QuizStarter extends QuizUser {
    private Quiz quiz;
    private QuizAttempt attempt;
    Scanner scanner = new Scanner(System.in);

    public QuizStarter(String name, int year, Quiz quiz) {
        super(name, year);
        this.quiz = quiz;
        this.attempt = new QuizAttempt(quiz);
    }

    public char getMultipleChoiceAnswer(List<String> choices) {
        System.out.println("A. " + choices.get(0));
        System.out.println("B. " + choices.get(1));
        System.out.println("C. " + choices.get(2));
        System.out.println("D. " + choices.get(3));
        System.out.println("Choose one of A, B, C, or D");
        String userAnswer = scanner.next();
        return userAnswer.charAt(0);
    }

    public int getNumericalAnswer() {
        System.out.println("Input a numerical answer below");
        int userAnswer = scanner.nextInt();
        return userAnswer;
    }

    public boolean getTrueFalseAnswer() {
        System.out.println("Is the statement true or false?");
        System.out.println("Choose t for true, f for false");
        String userAnswer = scanner.next();
        if (userAnswer.charAt(0) == 't') {
            return true;
        }
        return false;
    }

    @Override
    public void begin() {
        int i = 0;
        for (Question question: quiz.getAllQuestions()) {
            System.out.println("NUMBER " + String.valueOf(i + 1));
            System.out.println(question.getQuestion());
            switch (question.getFormat()) {
                case "Multiple Choice":
                    attempt.attemptQuestion(i, getMultipleChoiceAnswer(question.getChoices()));
                    break;
                case "Numerical":
                    attempt.attemptQuestion(i, getNumericalAnswer());
                    break;
                case "True False":
                    attempt.attemptQuestion(i, getTrueFalseAnswer());
                    break;
            }
            i++;
        }
    }

    @Override
    public void createReport() {
        System.out.println(attempt.generateReport());
    }
}
