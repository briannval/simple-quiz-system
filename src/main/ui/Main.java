package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
// Main class of the application

public class Main {
    public static void main(String[] args) {
        String name = (String) JOptionPane.showInputDialog(null, "Enter your name:",
                "Get Name", JOptionPane.PLAIN_MESSAGE, Utils.generateRandomAvatar(), null, null);
        new QuizPlayer(name, 9999);
    }
}
