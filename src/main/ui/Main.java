package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
// Main class of the application

public class Main {

    // Referenced from StackOverflow
    // https://stackoverflow.com/questions/10367722/clearing-my-jframe-jpanel-in-a-new-game
    public static void resetPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

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
        new QuizPlayer(name, 9999);
    }
}
