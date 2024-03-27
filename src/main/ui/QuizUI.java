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
    private final Map<String, List<Quiz>> allQuizzes;
    private final String nameAscending = "name_ascending";
    private final String nameDescending = "name_descending";
    private final String starsAscending = "stars_ascending";
    private final String starsDescending = "stars_descending";
    private QuizStarter starter;
    private final QuizPlayer player;
    private final JFrame frame;
    private final JPanel panel;
    private String currState;
    private final String name;
    private final int year;

    /*
     * REQUIRES: a non-empty quizBank, an initialized QuizStarter, the user's name, year of birth,
     *           and an initialized QuizPlayer
     * EFFECTS: initializes a frame of quizzes and a map of all quizzes sorted
     */
    public QuizUI(List<Quiz> quizBank, QuizStarter starter, String name, int year, QuizPlayer player) {
        this.allQuizzes = new HashMap<>();
        this.starter = starter;
        this.currState = "default";
        this.name = name;
        this.year = year;
        this.player = player;
        allQuizzes.put("default", copyQuizList(quizBank));
        frame = new JFrame("Quiz Starter");
        frame.setSize(400, 750);
        frame.setLocation(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
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
        panel.setBackground(new java.awt.Color(255, 255, 255));
        // Referenced from StackOverflow
        // https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        ImageIcon icon = new ImageIcon("./public/ubccs.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(20));
        JLabel label = new JLabel("Sort by:");
        label.setFont(new Font("Thoma", Font.BOLD, 24));
        panel.add(label);
        JButton button1 = new JButton("Name Ascending");
        JButton button2 = new JButton("Name Descending");
        JButton button3 = new JButton("Stars Ascending");
        JButton button4 = new JButton("Stars Descending");
        button1.setFont(new Font("Thoma", Font.PLAIN, 16));
        button1.addActionListener(this);
        button1.setActionCommand(nameAscending);
        button2.setFont(new Font("Thoma", Font.PLAIN, 16));
        button2.addActionListener(this);
        button2.setActionCommand(nameDescending);
        button3.setFont(new Font("Thoma", Font.PLAIN, 16));
        button3.addActionListener(this);
        button3.setActionCommand(starsAscending);
        button4.setFont(new Font("Thoma", Font.PLAIN, 16));
        button4.addActionListener(this);
        button4.setActionCommand(starsDescending);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(Box.createVerticalStrut(20));
    }

    /*
     * EFFECTS: returns a list of quizzes sorted as requested by the user
     */
    private List<Quiz> getCurrStateQuizzes() {
        return allQuizzes.get(this.currState);
    }

    /*
     * REQUIRES: prints out all the list of quizzes onto the frame
     */
    private void quizGui() {
        int counter = 1;
        for (Quiz q: getCurrStateQuizzes()) {
            JLabel quizNameLabel = new JLabel(counter + ". " + q.getName());
            JLabel quizStarsLabel = new JLabel("Stars: " + q.getStars());
            quizNameLabel.setFont(new Font("Thoma", Font.BOLD, 18));
            quizStarsLabel.setFont(new Font("Thoma", Font.PLAIN, 18));
            panel.add(quizNameLabel);
            panel.add(Box.createHorizontalStrut(15));
            panel.add(quizStarsLabel);
            panel.add(Box.createHorizontalStrut(15));
            counter += 1;
        }
        panel.add(Box.createVerticalStrut(20));
        JButton confirmButton = new JButton("Select a quiz");
        confirmButton.setFont(new Font("Thoma", Font.BOLD, 24));
        confirmButton.addActionListener(this);
        confirmButton.setActionCommand("select_quiz");
        panel.add(confirmButton);
        Utils.requestFrameFocus(this.frame);
    }

    /*
     * MODIFIES: an accurate ActionEvent performed by the button
     * EFFECTS: makes changes to the screen after user response
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("select_quiz")) {
            this.currState = e.getActionCommand();
            Utils.resetPanel(panel);
            initGui();
            quizGui();
        } else {
            String quizSelectionString = (String) JOptionPane.showInputDialog(null,
                    "Select a number from 1 to " + getCurrStateQuizzes().size(), "Get Quiz",
                    JOptionPane.PLAIN_MESSAGE, Utils.generateRandomAvatar(), null, null);
            frame.dispose();
            Quiz startQuiz = getCurrStateQuizzes().get(Integer.parseInt(quizSelectionString) - 1);
            JOptionPane.showMessageDialog(null, "We will now begin " + startQuiz.getName(),
                    "Start Quiz", JOptionPane.WARNING_MESSAGE);
            starter = new QuizStarter(name, year, startQuiz);
            starter.begin();
            starter.createReport();
            player.begin();
        }
    }
}
