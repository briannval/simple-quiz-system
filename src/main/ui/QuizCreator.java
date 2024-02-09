package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizCreator extends QuizUser {
    QuizMaker maker;
    String quizName;
    int numProblems;
    Scanner scanner = new Scanner(System.in);

    public QuizCreator(String name, int year, String quizName, int numProblems) {
        super(name, year);
        this.quizName = quizName;
        this.numProblems = numProblems;
        maker = new QuizMaker(quizName, numProblems);
    }

    public boolean charToBool(char c) {
        if (c == 't') {
            return true;
        }
        return false;
    }

    public void createTrueFalse() {
        String question;
        int points;
        char correctAnswer;
        System.out.println("Please input the question statement");
        question = scanner.nextLine();
        System.out.println("Is the statement supposed to be true or false? (t/f)");
        correctAnswer = scanner.next().charAt(0);
        System.out.println("How many points is this question worth?");
        points = scanner.nextInt();
        maker.addTrueFalseQuestion(question, charToBool(correctAnswer), points);
    }

    public void createNumerical() {
        String question;
        int points;
        int correctAnswer;
        System.out.println("Please input the question statement");
        question = scanner.nextLine();
        System.out.println("What is the answer to this question?");
        correctAnswer = scanner.nextInt();
        System.out.println("How many points is this question worth?");
        points = scanner.nextInt();
        maker.addNumericalQuestion(correctAnswer, question, points);
    }

    public void createMultipleChoice() {
        List<String> choices = new ArrayList<>();
        String question;
        int points;
        char correctAnswer;
        System.out.println("Please input the question");
        question = scanner.nextLine();
        System.out.println("What is the first answer option?");
        choices.add(scanner.nextLine());
        System.out.println("What is the second answer option?");
        choices.add(scanner.nextLine());
        System.out.println("What is the third answer option?");
        choices.add(scanner.nextLine());
        System.out.println("What is the fourth answer option?");
        choices.add(scanner.nextLine());
        System.out.println("What is the correct answer to the problem? (choose A, B, C, or D)");
        correctAnswer = scanner.next().charAt(0);
        System.out.println("How many points is this question worth?");
        points = scanner.nextInt();
        maker.addMultipleChoiceQuestion(correctAnswer, question, points, choices);
    }

    public int prompt() {
        System.out.println("What format of question do you want?");
        System.out.println("1. Multiple Choice");
        System.out.println("2. True False");
        System.out.println("3. Numerical");
        return scanner.nextInt();
    }

    public Quiz createQuiz() {
        return maker.quizGenerator();
    }

    @Override
    public void begin() {
        System.out.println("Let's start creating your quiz!");
        for (int i = 0; i < this.numProblems; i++) {
            System.out.println("NUMBER " + String.valueOf(i + 1));
            switch (prompt()) {
                case 1:
                    scanner.nextLine();
                    createMultipleChoice();
                    break;
                case 2:
                    scanner.nextLine();
                    createTrueFalse();
                    break;
                case 3:
                    scanner.nextLine();
                    createNumerical();
                    break;
            }
        }
    }

    @Override
    public void createReport() {
        System.out.println("You have successfully created your quiz!");
    }
}
