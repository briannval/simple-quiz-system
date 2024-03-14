package ui;

import model.QuizMaker;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// Referenced from StackOverflow
// For adding multiple inputs in a JPanel:
// https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
// For adding layout to the fields and labels:
// https://stackoverflow.com/questions/1534889/how-to-make-jlabels-start-on-the-next-line
// Displays prompts for user to make questions in QuizCreator
public class QuestionUI {

    /*
     * REQUIRES: an instantiated QuizMaker
     * MODIFIES: maker
     * EFFECTS: prompts the user to create a multiple choice question (GUI) and adds it
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public static void multipleChoicePanel(QuizMaker maker) {
        JTextField questionField = new JTextField(20);
        JTextField correctAnswerField = new JTextField(20);
        JTextField choiceAField = new JTextField(20);
        JTextField choiceBField = new JTextField(20);
        JTextField choiceCField = new JTextField(20);
        JTextField choiceDField = new JTextField(20);
        JTextField pointsField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Multiple Choice Question: "));
        panel.add(questionField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Choice A: "));
        panel.add(choiceAField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Choice B: "));
        panel.add(choiceBField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Choice C: "));
        panel.add(choiceCField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Choice D: "));
        panel.add(choiceDField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Correct Answer (a/b/c/d): "));
        panel.add(correctAnswerField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Points: "));
        panel.add(pointsField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please input question details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            List<String> choices = new ArrayList<>();
            String question = questionField.getText();
            String correctAnswerString = correctAnswerField.getText();
            char correctAnswer = correctAnswerString.charAt(0);
            int points = Integer.parseInt(pointsField.getText());
            choices.add(choiceAField.getText());
            choices.add(choiceBField.getText());
            choices.add(choiceCField.getText());
            choices.add(choiceDField.getText());
            maker.addMultipleChoiceQuestion(correctAnswer, question, points, choices);
        }
    }


    /*
     * REQUIRES: an instantiated QuizMaker
     * MODIFIES: maker
     * EFFECTS: prompts the user to create a true false question (GUI) and adds it
     */
    public static void trueFalsePanel(QuizMaker maker) {
        JTextField questionField = new JTextField(20);
        JTextField correctAnswerField = new JTextField(20);
        JTextField pointsField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("True False Question: "));
        panel.add(questionField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Correct Answer (t/f): "));
        panel.add(correctAnswerField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Points: "));
        panel.add(pointsField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please input question details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String question = questionField.getText();
            String correctAnswerString = correctAnswerField.getText();
            char correctAnswer = correctAnswerString.charAt(0);
            int points = Integer.parseInt(pointsField.getText());
            maker.addTrueFalseQuestion(question, Utils.charToBool(correctAnswer), points);
        }
    }

    /*
     * REQUIRES: an instantiated QuizMaker
     * MODIFIES: maker
     * EFFECTS: prompts the user to create a numerical question (GUI) and adds it
     */
    public static void numericalPanel(QuizMaker maker) {
        JTextField questionField = new JTextField(20);
        JTextField correctAnswerField = new JTextField(20);
        JTextField pointsField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Numerical Question: "));
        panel.add(questionField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Correct Answer (Number): "));
        panel.add(correctAnswerField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Points: "));
        panel.add(pointsField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Please input question details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String question = questionField.getText();
            int correctAnswer = Integer.parseInt(correctAnswerField.getText());
            int points = Integer.parseInt(pointsField.getText());
            maker.addNumericalQuestion(correctAnswer, question, points);
        }
    }
}
