package ui;

import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

// Referenced from StackOverflow
// https://stackoverflow.com/questions/12542185/sort-a-java-collection-object-based-on-one-field-in-it
// A class to display all the quizzes and sort them based on user's input
public class QuizUI implements ActionListener {
    private final Color white = new java.awt.Color(255, 255, 255);
    private final Font smallFont = new Font("Thoma", Font.BOLD, 20);
    private final Font mediumFont = new Font("Thoma", Font.BOLD, 24);
    private final String imageUrl = "public/ubccs.png";
    private final List<Quiz> playerQuizBank;
    private final Map<String, List<Quiz>> allQuizzes;
    private final String nameAscending = "name_ascending";
    private final String nameDescending = "name_descending";
    private final String starsAscending = "stars_ascending";
    private final String starsDescending = "stars_descending";
    private QuizStarter starter;
    private final QuizPlayer player;
    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private final JPanel quizPanel;
    private final JPanel actionsPanel;
    private String currState;
    private final String name;
    private final int year;

    /*
     * REQUIRES: a non-empty quizBank, an initialized QuizStarter, the user's name, year of birth,
     *           and an initialized QuizPlayer
     * EFFECTS: initializes a mainFrame of quizzes and a map of all quizzes sorted
     */
    public QuizUI(List<Quiz> quizBank, QuizStarter starter, String name, int year, QuizPlayer player) {
        this.playerQuizBank = quizBank;
        this.allQuizzes = new HashMap<>();
        this.starter = starter;
        this.currState = "default";
        this.name = name;
        this.year = year;
        this.player = player;
        allQuizzes.put("default", copyQuizList(quizBank));
        mainFrame = new JFrame("Quiz Starter");
        mainFrame.setSize(1100, 1000);
        mainFrame.setLocation(250, 250);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        quizPanel = new JPanel();
        actionsPanel = new JPanel();
        sorter();
        initGui();
        quizGui();
    }

    /*
     * REQUIRES: a non-empty list of quizzes
     * EFFECTS: performs a deep copy of the quizzes list
     *          quizzes reference still remains
     */
    public List<Quiz> copyQuizList(List<Quiz> list) {
        List<Quiz> newQuizList = new ArrayList<>();
        for (Quiz q: list) {
            newQuizList.add(q);
        }
        return newQuizList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: (1) sorts by quiz name ascending
     *          (2) sorts by quiz name descending
     *          (3) sorts by quiz stars ascending
     *          (4) sorts by quiz stars descending
     */
    private void sorter() {
        List<Quiz> temp = copyQuizList(allQuizzes.get("default"));

        Collections.sort(temp, Comparator.comparing(Quiz::getName));
        allQuizzes.put(nameAscending, copyQuizList(temp));

        Collections.sort(temp, Comparator.comparing(Quiz::getName).reversed());
        allQuizzes.put(nameDescending, copyQuizList(temp));

        Collections.sort(temp, Comparator.comparing(Quiz::getStars));
        allQuizzes.put(starsAscending, copyQuizList(temp));

        Collections.sort(temp, Comparator.comparing(Quiz::getStars).reversed());
        allQuizzes.put(starsDescending, copyQuizList(temp));
    }

    /*
     * EFFECTS: gives the initial GUI of operation to be performed on all the quizzes
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initGui() {
        mainPanel.setBackground(white);
        // Referenced from StackOverflow
        // https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(white);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        ImageIcon icon = new ImageIcon(imageUrl);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
        buttonsPanel.add(imageLabel);
        JLabel label = new JLabel("Sort by:");
        label.setFont(mediumFont);
        buttonsPanel.add(label);
        JButton button1 = new JButton("Name Ascending");
        JButton button2 = new JButton("Name Descending");
        JButton button3 = new JButton("Stars Ascending");
        JButton button4 = new JButton("Stars Descending");
        button1.setFont(smallFont);
        button1.addActionListener(this);
        button1.setActionCommand(nameAscending);
        button2.setFont(smallFont);
        button2.addActionListener(this);
        button2.setActionCommand(nameDescending);
        button3.setFont(smallFont);
        button3.addActionListener(this);
        button3.setActionCommand(starsAscending);
        button4.setFont(smallFont);
        button4.addActionListener(this);
        button4.setActionCommand(starsDescending);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(button4);
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createHorizontalStrut(100));
    }


    /*
     * EFFECTS: returns a list of quizzes sorted as requested by the user
     */
    private List<Quiz> getCurrStateQuizzes() {
        return allQuizzes.get(this.currState);
    }

    /*
     * EFFECTS: adds the quizzes in the HashMap to the quizPanel
     */
    private void setQuizPanel() {
        int counter = 1;
        quizPanel.setBackground(white);
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        for (Quiz q: getCurrStateQuizzes()) {
            JLabel quizNameLabel = new JLabel(counter + ". " + q.getName());
            JLabel quizStarsLabel = new JLabel("Stars: " + q.getStars());
            quizNameLabel.setFont(new Font("Thoma", Font.BOLD, 18));
            quizStarsLabel.setFont(new Font("Thoma", Font.PLAIN, 18));
            quizPanel.add(quizNameLabel);
            quizPanel.add(Box.createHorizontalStrut(15));
            quizPanel.add(quizStarsLabel);
            quizPanel.add(Box.createHorizontalStrut(15));
            counter += 1;
        }
    }

    /*
     * REQUIRES: prints out all the list of quizzes onto the mainFrame
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void quizGui() {
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        setQuizPanel();
        mainPanel.add(quizPanel);
        mainPanel.add(Box.createHorizontalStrut(100));
        actionsPanel.setBackground(white);
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        JButton confirmButton = new JButton("Select a quiz");
        confirmButton.setFont(mediumFont);
        confirmButton.addActionListener(this);
        confirmButton.setActionCommand("select_quiz");
        actionsPanel.add(confirmButton);
        JButton deleteButton = new JButton("Delete a quiz");
        deleteButton.setFont(mediumFont);
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("delete_quiz");
        actionsPanel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(mediumFont);
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        actionsPanel.add(cancelButton);
        mainPanel.add(actionsPanel);
        mainFrame.add(mainScrollPane);
        Utils.requestFrameFocus(this.mainFrame);
    }

    /*
     * MODIFIES: this
     * EFFECTS: handles deletion of a quiz
     */
    private void handleDeleteQuiz() {
        String quizSelectionString = (String) JOptionPane.showInputDialog(null,
                "Select a number from 1 to " + getCurrStateQuizzes().size() + " to delete", "Delete Quiz",
                JOptionPane.PLAIN_MESSAGE, Utils.generateRandomAvatar(), null, null);
        mainFrame.dispose();
        Quiz deleteQuiz = getCurrStateQuizzes().get(Integer.parseInt(quizSelectionString) - 1);
        JOptionPane.showMessageDialog(null, "We will delete " + deleteQuiz.getName(),
                "Delete Quiz", JOptionPane.WARNING_MESSAGE);
        this.playerQuizBank.remove(deleteQuiz);
        player.begin();
    }

    /*
     * MODIFIES: this
     * EFFECTS: handles starting of a quiz
     */
    private void handleStartQuiz() {
        String quizSelectionString = (String) JOptionPane.showInputDialog(null,
                "Select a number from 1 to " + getCurrStateQuizzes().size() + " to start", "Get Quiz",
                JOptionPane.PLAIN_MESSAGE, Utils.generateRandomAvatar(), null, null);
        mainFrame.dispose();
        Quiz startQuiz = getCurrStateQuizzes().get(Integer.parseInt(quizSelectionString) - 1);
        JOptionPane.showMessageDialog(null, "We will now begin " + startQuiz.getName(),
                "Start Quiz", JOptionPane.WARNING_MESSAGE);
        starter = new QuizStarter(name, year, startQuiz);
        starter.begin();
        starter.createReport();
        player.begin();
    }

    /*
     * MODIFIES: an accurate ActionEvent performed by the button
     * EFFECTS: makes changes to the screen after user response
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isNameAscending = e.getActionCommand().equals(nameAscending);
        boolean isNameDescending = e.getActionCommand().equals(nameDescending);
        boolean isStarsAscending = e.getActionCommand().equals(starsAscending);
        boolean isStarsDescending = e.getActionCommand().equals(starsDescending);
        if ((isNameDescending || isStarsDescending) || (isNameAscending || isStarsAscending)) {
            this.currState = e.getActionCommand();
            Utils.resetPanel(quizPanel);
            setQuizPanel();
        } else if (e.getActionCommand().equals("delete_quiz")) {
            handleDeleteQuiz();
        } else if (e.getActionCommand().equals("cancel")) {
            mainFrame.dispose();
            player.begin();
        } else {
            handleStartQuiz();
        }
    }
}
