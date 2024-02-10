package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the user interface for creating a quiz
public class QuizCreator extends QuizUser {
    QuizMaker maker;
    String quizName;
    int numProblems;
    Scanner scanner = new Scanner(System.in);

    /*
     * REQUIRES: name must be of a non-zero length
     *           year must be from 1-4 (UBC year)
     *           quizName must be of a non-zero length
     *           numProblems must be >= 1
     * EFFECTS: creates a new quiz creator interface that would help users
     *          to create a quiz
     */
    public QuizCreator(String name, int year, String quizName, int numProblems) {
        super(name, year);
        this.quizName = quizName;
        this.numProblems = numProblems;
        maker = new QuizMaker(quizName, numProblems);
    }

    /*
     * REQUIRES: c must be of type char
     * EFFECTS: converts the character t to boolean true,
     *          and character f to boolean false,
     *          used in creating boolean questions
     */
    public boolean charToBool(char c) {
        if (c == 't') {
            return true;
        }
        return false;
    }

    /*
     * REQUIRES: user input of question statement must be non-zero length
     *           user input of correctAnswer must be either t or f
     *           user input of points must be >= 1
     * MODIFIES: this
     * EFFECTS: adds a new True False question to the Quiz Maker class
     */
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

    /*
     * REQUIRES: user input of question statement must be non-zero length
     *           user input of correctAnswer must be an integer
     *           user input of points must be >= 1
     * MODIFIES: this
     * EFFECTS: adds a Numerical question to the Quiz Maker class
     */
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

    /*
     * REQUIRES: user input of question statement must be non-zero length
     *           user input of correctAnswer must be a character
     *           user input of points must be >= 1
     * MODIFIES: this
     * EFFECTS: adds a Multiple Choice question to the Quiz Maker class
     *
     */
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

    /*
     * REQUIRES: user input must be in the range specified
     * EFFECTS: prompts the user to a choice for  action
     */
    public int prompt() {
        System.out.println("What format of question do you want?");
        System.out.println("1. Multiple Choice");
        System.out.println("2. True False");
        System.out.println("3. Numerical");
        return scanner.nextInt();
    }

    /*
     * REQUIRES: all the questions must be added already
     * EFFECTS: creates a quiz from all the questions specified
     */
    public Quiz createQuiz() {
        return maker.quizGenerator();
    }

    /*
     * REQUIRES: users select 1-3 for different problem formats
     * EFFECTS: creates questions of different formats for the format
     *          specified by the user
     */
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

    /*
     * EFFECTS: concludes the quiz creating process
     */
    @Override
    public void createReport() {
        System.out.println("You have successfully created your quiz!");
    }
}
