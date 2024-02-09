package ui;

import java.util.Scanner;

public class QuizPlayer extends QuizUser {
    public QuizPlayer(String name, int year) {
        super(name, year);
    }

    public void printChoices() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create a quiz");
        System.out.println("2. Attempt a quiz");
        System.out.println("3. Exit");
        System.out.print("Choose 1 or 2: ");
    }

    @Override
    public void begin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printChoices();
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    @Override
    public void createReport() {

    }
}
