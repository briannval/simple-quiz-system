package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Static utility methods for the UI classes
public class Utils {

    /*
     * REQUIRES: an instantiated JFrame
     * MODIFIES: frame
     * EFFECTS: brings the frame into focus
     */
    public static void requestFrameFocus(JFrame frame) {
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
    }

    // Referenced from StackOverflow
    // https://stackoverflow.com/questions/10367722/clearing-my-jframe-jpanel-in-a-new-game
    /*
     * REQUIRES: an instantiated JPanel
     * MODIFIES: panel
     * EFFECTS: brings the panel into focus
     */
    public static void resetPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    /*
     * EFFECTS: gives a square shaped avatar ImageIcon
     */
    public static ImageIcon generateRandomAvatar() {
        Random random = new Random();
        int randomNumber = random.nextInt(7) + 1;
        ImageIcon icon = new ImageIcon("./public/avatar" + randomNumber + ".jpeg");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    /*
     * REQUIRES: c must be of type char
     * EFFECTS: converts the character t to boolean true,
     *          and character f to boolean false,
     *          used in creating boolean questions
     */
    public static boolean charToBool(char c) {
        return c == 't';
    }
}
