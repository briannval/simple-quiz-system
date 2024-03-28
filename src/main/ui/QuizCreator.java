package ui;

import model.*;

import javax.swing.*;
import java.util.Scanner;

// Represents the user interface for creating a quiz
public class QuizCreator extends QuizUser {
    private JPanel panel;
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
     * REQUIRES: user input of question statement must be non-zero length
     *           user input of correctAnswer must be either t or f
     *           user input of points must be >= 1
     * MODIFIES: this
     * EFFECTS: adds a new True False question to the Quiz Maker class
     */
    public void createTrueFalse() {
        QuestionUI.trueFalsePanel(maker);
        // Console method listed below
        /*
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
        */
    }

    /*
     * REQUIRES: user input of question statement must be non-zero length
     *           user input of correctAnswer must be an integer
     *           user input of points must be >= 1
     * MODIFIES: this
     * EFFECTS: adds a Numerical question to the Quiz Maker class
     */
    public void createNumerical() {
        QuestionUI.numericalPanel(maker);
        // Console method listed below
        /*
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
         */
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
        QuestionUI.multipleChoicePanel(maker);
        // Console method listed below
        /*
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
         */
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
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void begin() {
        // System.out.println("Let's start creating your quiz!");
        JOptionPane.showMessageDialog(null, "Let's start creating your quiz!",
                "Create Quiz", JOptionPane.WARNING_MESSAGE);
        for (int i = 0; i < this.numProblems; i++) {
            panel = new JPanel();
            ButtonGroup bg = new ButtonGroup();
            JRadioButton mc = new JRadioButton("Multiple Choice");
            JRadioButton tf = new JRadioButton("True False");
            JRadioButton nm = new JRadioButton("Numerical");
            mc.setActionCommand("MultipleChoice");
            tf.setActionCommand("TrueFalse");
            nm.setActionCommand("Numerical");
            bg.add(mc);
            bg.add(tf);
            bg.add(nm);
            panel.add(new JLabel("Question Type: "));
            panel.add(mc);
            panel.add(tf);
            panel.add(nm);
            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Please choose question type for number " + (i + 1), JOptionPane.OK_CANCEL_OPTION);
            // System.out.println("NUMBER " + String.valueOf(i + 1));
            if (result == JOptionPane.OK_OPTION) {
                switch (bg.getSelection().getActionCommand()) {
                    case "MultipleChoice":
                        createMultipleChoice();
                        break;
                    case "TrueFalse":
                        createTrueFalse();
                        break;
                    case "Numerical":
                        createNumerical();
                        break;
                }
            }
        }
    }

    /*
     * EFFECTS: concludes the quiz creating process
     */
    @Override
    public void createReport() {
        JOptionPane.showMessageDialog(null, "Quiz successfully created!",
                "Load Data", JOptionPane.WARNING_MESSAGE);
    }
}
