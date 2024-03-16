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
public class QuizUI implements ActionListener {
    private Map<String, List<Quiz>> allQuizzes;
    private final String nameAscending = "name_ascending";
    private final String nameDescending = "name_descending";
    private final String starsAscending = "stars_ascending";
    private final String starsDescending = "stars_descending";
    private QuizStarter starter;
    private QuizPlayer player;
    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private String currState;
    private String name;
    private int year;

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
        scrollPane = new JScrollPane();
        sorter();
        initGui();
        quizGui();
    }

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

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initGui() {
        panel.setBackground(new java.awt.Color(255, 255, 255));
        // Referenced from StackOverflow
        // https://stackoverflow.com/questions/5854005/setting-horizontal-and-vertical-margins
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        panel.setLayout(new FlowLayout());
        frame.add(panel);
        ImageIcon icon = new ImageIcon("./public/ubccs.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(50));
        JLabel label = new JLabel("Sort quizzes by:");
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
        panel.add(Box.createVerticalStrut(40));
    }

    private List<Quiz> getCurrStateQuizzes() {
        return allQuizzes.get(this.currState);
    }

    private void quizGui() {
        int counter = 1;
        for (Quiz q: getCurrStateQuizzes()) {
            JLabel quizNameLabel = new JLabel(counter + ". " + q.getName());
            JLabel quizStarsLabel = new JLabel("Stars: " + q.getStars());
            quizNameLabel.setFont(new Font("Thoma", Font.CENTER_BASELINE, 20));
            quizStarsLabel.setFont(new Font("Thoma", Font.PLAIN, 20));
            panel.add(quizNameLabel);
            panel.add(Box.createHorizontalStrut(15));
            panel.add(quizStarsLabel);
            panel.add(Box.createHorizontalStrut(15));
            counter += 1;
        }
        JButton confirmButton = new JButton("Select a quiz");
        confirmButton.setFont(new Font("Thoma", Font.BOLD, 24));
        confirmButton.addActionListener(this);
        confirmButton.setActionCommand("select_quiz");
        panel.add(confirmButton);
        Utils.requestFrameFocus(this.frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != "select_quiz") {
            this.currState = e.getActionCommand();
            Utils.resetPanel(panel);
            initGui();
            quizGui();
        } else {
            String quizSelectionString = (String) JOptionPane.showInputDialog(null,
                    "Select a number from 1 to " + getCurrStateQuizzes().size(), "Get Quiz",
                    JOptionPane.PLAIN_MESSAGE, Utils.generateRandomAvatar(), null, null);
            frame.dispose();
            starter = new QuizStarter(name, year, getCurrStateQuizzes().get(Integer.parseInt(quizSelectionString) - 1));
            starter.begin();
            starter.createReport();
            player.begin();
        }
    }
}
