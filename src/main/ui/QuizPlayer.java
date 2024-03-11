package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import model.*;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents the whole quiz system with user info
public class QuizPlayer extends QuizUser implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private static final String FILE_URL = "./data/quizzes.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private List<Quiz> quizBank;
    QuizStarter starter;
    QuizCreator creator;
    Scanner scanner = new Scanner(System.in);

    /*
     * REQUIRES: name must be of non-zero length
     *           year must be from 1-4 (UBC year)
     * EFFECTS: initializes a new user quiz interface
     *          with a quiz bank
     */
    public QuizPlayer(String name, int year) {
        super(name, year);
        quizBank = new ArrayList<>();
        jsonWriter = new JsonWriter(FILE_URL);
        jsonReader = new JsonReader(FILE_URL);
        begin();
    }

    // Referenced from StackOverflow
    // https://stackoverflow.com/questions/10367722/clearing-my-jframe-jpanel-in-a-new-game
    public void resetPanel() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CreateQuiz":
                frame.dispose();
                handleCreateQuiz();
                break;
            case "LoadQuiz":
                frame.dispose();
                handleAttemptQuiz();
                break;
            case "LoadData":
                loadQuizzes();
                JOptionPane.showMessageDialog(frame, "Data successfully loaded!",
                        "Load Data", JOptionPane.WARNING_MESSAGE);
                break;
            case "SaveData":
                saveQuizzes();
                JOptionPane.showMessageDialog(frame, "Data successfully saved!",
                        "Save Data", JOptionPane.WARNING_MESSAGE);
                break;
            case "Exit":
                frame.dispose();
                System.exit(0);
        }
    }


    /*
     * EFFECTS: handles the user's interaction on what action to take during runtime
     *          choice 1 allows user to create a quiz
     *          choice 2 allows user to attempt a quiz
     *          choice 3 allows user to read from the local json storage
     *          choice 4 allows user to save to the local json storage
     *          choice 5 allows user to exit the application
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void begin() {
        frame = new JFrame("Quiz Player");
        frame.setSize(450, 550);
        frame.setLocation(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setBackground(new java.awt.Color(255, 255, 255));
        // Referenced from StackOverflow
        // https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.setLayout(new FlowLayout());
        frame.add(panel);

        JLabel label = new JLabel("Welcome, " + super.getName() + "!");
        label.setFont(new Font("Thoma", Font.BOLD, 36));
        panel.add(label);
        JLabel imageLabel = new JLabel(new ImageIcon("./public/ubc.png"));
        panel.add(imageLabel);
        JButton button1 = new JButton("Create a quiz");
        JButton button2 = new JButton("Attempt a quiz");
        JButton button3 = new JButton("Load local data");
        JButton button4 = new JButton("Save local data");
        JButton button5 = new JButton("Exit and close");
        button1.setFont(new Font("Thoma", Font.PLAIN, 16));
        button2.setFont(new Font("Thoma", Font.PLAIN, 16));
        button3.setFont(new Font("Thoma", Font.PLAIN, 16));
        button4.setFont(new Font("Thoma", Font.PLAIN, 16));
        button5.setFont(new Font("Thoma", Font.PLAIN, 16));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        button1.addActionListener(this);
        button1.setActionCommand("CreateQuiz");
        button2.addActionListener(this);
        button2.setActionCommand("AttemptQuiz");
        button3.addActionListener(this);
        button3.setActionCommand("LoadData");
        button4.addActionListener(this);
        button4.setActionCommand("SaveData");
        button5.addActionListener(this);
        button5.setActionCommand("Exit");

        frame.setVisible(true);
    }

    /*
     * REQUIRES: the local json storage shouldn't be empty
     * MODIFIES: this
     * EFFECTS: loads all the quizzes in the json storage into quizBank
     *          if it's unable to load, throw an IOException
     */
    public void loadQuizzes() {
        try {
            this.quizBank = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Error reading from " + FILE_URL);
        }
    }

    /*
     * REQUIRES: quizzes inside the quiz bank shouldn't be empty
     * MODIFIES: this
     * EFFECTS: saves the quizBank quizzes to the local json storage
     *          which is in ./data/quizzes.json
     *          if it's unable to save, throw a FileNotFoundException
     */
    public void saveQuizzes() {
        try {
            jsonWriter.openFile();
            jsonWriter.saveToFile(quizzesToJson());
            jsonWriter.closeFile();
            System.out.println("Saved all the quizzes to " + FILE_URL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FILE_URL);
        }
    }

    /*
     * REQUIRES: quizzes inside the quiz bank shouldn't be empty
     * EFFECTS: parses the quizzes inside the quiz bank to json
     */
    public JSONArray quizzesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Quiz quiz: quizBank) {
            jsonArray.put(quiz.toJson());
        }
        return jsonArray;
    }

    /*
     * EFFECTS: displays all the quizzes currently inside the quiz bank
     */
    public void displayQuizNames() {
        for (int i = 0; i < quizBank.size(); i++) {
            System.out.println((i + 1) + " " + quizBank.get(i).getName());
            System.out.println("STARS: " + quizBank.get(i).getStars());
        }
        System.out.println("Choose the number of quiz you would like to try");
        System.out.println("or enter -1 to exit");
    }


    /*
     * EFFECTS: prints all actions users can do inside the interface
     */
    public void printChoices() {

        System.out.println("What would you like to do?");
        System.out.println("1. Create a quiz");
        System.out.println("2. Attempt a quiz");
        System.out.println("3. Load data");
        System.out.println("4. Save data");
        System.out.println("5. Exit");
        System.out.print("Choose 1-5: ");
    }

    /*
     * REQUIRES: users to press enter to go next
     * EFFECTS: prompts the user to press enter before continuing next action
     */
    public void pressEnter() {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    public ImageIcon getAvatar() {
        ImageIcon icon = new ImageIcon("./public/avatar1.jpeg");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    /*
     * REQUIRES: user's quiz name must be of a non-zero length
     *           number of questions must be >= 1
     * EFFECTS: handler for the Quiz Creator class to create a quiz
     */
    public void handleCreateQuiz() {
        String quizName = (String) JOptionPane.showInputDialog(null,"What will your quiz name be?",
                getName() + "'s quiz", JOptionPane.PLAIN_MESSAGE, getAvatar(), null, null);
        String problems = JOptionPane.showInputDialog(frame,
                "How many questions do you want in " + quizName + "?", "Number of Questions",
                JOptionPane.PLAIN_MESSAGE);
        int numProblems = Integer.parseInt(problems);
        creator = new QuizCreator(super.getName(), super.getYear(), quizName, numProblems);
        creator.begin();
        creator.createReport();
        quizBank.add(creator.createQuiz());
    }

    /*
     * REQUIRES: user's choice must be a valid index of the list of quizzes
     * EFFECTS: handler for the Quiz Starter class to start a quiz
     */
    public void handleAttemptQuiz() {
        displayQuizNames();
        int quizChoice = scanner.nextInt();
        if (quizChoice != -1) {
            starter = new QuizStarter(super.getName(), super.getYear(), quizBank.get(quizChoice - 1));
            starter.begin();
            starter.createReport();
            pressEnter();
        }
    }

    /*
     * EFFECTS: gets the user's name and input onto a string
     */
    @Override
    public void createReport() {
        System.out.println("Your name is " + super.getName() + " and your year is " + (super.getYear()));
    }

}
