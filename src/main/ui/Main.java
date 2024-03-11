package ui;

import javax.swing.*;
// Main class of the application

public class Main {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String yearStr = JOptionPane.showInputDialog("Enter the year you were born:");
        int year = Integer.parseInt(yearStr);
        new QuizPlayer(name, year);
    }
}
