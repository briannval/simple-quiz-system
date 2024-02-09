package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import model.Quiz;

public class QuizPlayer extends QuizUser {
    private final List<Quiz> quizBank;
    QuizStarter starter;
    QuizCreator creator;
    Scanner scanner = new Scanner(System.in);

    public QuizPlayer(String name, int year) {
        super(name, year);
        quizBank = new ArrayList<>();
    }

    public void displayQuizNames() {
        for (int i = 0; i < quizBank.size(); i++) {
            System.out.println(String.valueOf(i + 1) + " " + quizBank.get(i).getName());
        }
        System.out.println("You may choose a number from 1 to " + String.valueOf(quizBank.size()));

    }

    public void printChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create a quiz");
        System.out.println("2. Attempt a quiz");
        System.out.println("3. Exit");
        System.out.print("Choose 1 or 2: ");
    }

    public void pressEnter() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    public void handleCreateQuiz() {
        System.out.println("What will your quiz name be?");
        String quizName = scanner.nextLine();
        System.out.println("How many questions do you want in " + quizName + "?");
        int numProblems = scanner.nextInt();
        scanner.nextLine();
        creator = new QuizCreator(super.getName(), super.getYear(), quizName, numProblems);
        creator.begin();
        creator.createReport();
        quizBank.add(creator.createQuiz());
    }

    public void handleAttemptQuiz() {
        displayQuizNames();
        int quizChoice = scanner.nextInt();
        starter = new QuizStarter(super.getName(), super.getYear(), quizBank.get(quizChoice - 1));
        starter.begin();
        starter.createReport();
        pressEnter();
    }

    @Override
    public void begin() {
        while (true) {
            printChoices();
            int menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1:
                    handleCreateQuiz();
                    break;
                case 2:
                    handleAttemptQuiz();
                    break;
                case 3:
                    pressEnter();
                    System.exit(0);
            }
        }
    }

    @Override
    public void createReport() {
        System.out.println("Your name is " + super.getName() + " and your year is " + String.valueOf(super.getYear()));
    }
}
