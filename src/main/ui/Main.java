package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
// Main class of the application

public class Main {
    public static ImageIcon generateRandomAvatar() {
        Random random = new Random();
        int randomNumber = random.nextInt(7) + 1;
        ImageIcon icon = new ImageIcon("./public/avatar" + randomNumber + ".jpeg");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    public static void main(String[] args) {
        String name = (String) JOptionPane.showInputDialog(null, "Enter your name:",
                "Get Name", JOptionPane.PLAIN_MESSAGE, generateRandomAvatar(), null, null);
        String yearStr = (String) JOptionPane.showInputDialog(null, "Enter the year you were born:",
                "Get Year", JOptionPane.PLAIN_MESSAGE, generateRandomAvatar(), null, null);
        int year = Integer.parseInt(yearStr);
        new QuizPlayer(name, year);
    }
}
