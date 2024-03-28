package ui;

import model.*;

import javax.swing.*;

// Represents a class interface for users to attempt / start a quiz
public class QuizStarter extends QuizUser {
    private final Quiz quiz;
    private final QuizAttempt attempt;

    /*
     * REQUIRES: name must be of non-zero length
     *           year must be UBC year (1-4)
     * EFFECTS:
     */
    public QuizStarter(String name, int year, Quiz quiz) {
        super(name, year);
        this.quiz = quiz;
        this.attempt = new QuizAttempt(quiz);
    }

    // Old console methods
    /*
     * REQUIRES: a choices list of length 4
     *           user input must be a single character (A,B,C,D)
     * EFFECTS: prompts the user for a multiple choice answer
    public char getMultipleChoiceAnswer(List<String> choices) {
        System.out.println("A. " + choices.get(0));
        System.out.println("B. " + choices.get(1));
        System.out.println("C. " + choices.get(2));
        System.out.println("D. " + choices.get(3));
        System.out.println("Choose one of A, B, C, or D");
        String userAnswer = scanner.next();
        return userAnswer.charAt(0);
    }

     * REQUIRES: user's input must be an integer
     * EFFECTS: prompts the user for an integer answer
    public int getNumericalAnswer() {
        System.out.println("Input a numerical answer below");
        int userAnswer = scanner.nextInt();
        return userAnswer;
    }

     * REQUIRES: user's input must be a single character (t/f)
     * EFFECTS: prompts the user for a true false answer
    public boolean getTrueFalseAnswer() {
        System.out.println("Is the statement true or false?");
        System.out.println("Choose t for true, f for false");
        String userAnswer = scanner.next();
        if (userAnswer.charAt(0) == 't') {
            return true;
        }
        return false;
    }
    */

    /*
     * REQUIRES: userAnswer must be 't' or 'f'
     * MODIFIES: this
     * EFFECTS: stars the quiz in the attempt if the user wants to
     */
    public void handleUserStar(char userAnswer) {
        if (userAnswer == 't') {
            attempt.willStar();
        }
    }

    /*
     * REQUIRES: users must answer each question accordingly
     * MODIFIES: this
     * EFFECTS: iterates through the questions in the quiz and attempt them one by one
     */
    @Override
    public void begin() {
        int i = 0;
        for (Question question: quiz.getAllQuestions()) {
            /*
            System.out.println("NUMBER " + String.valueOf(i + 1));
            System.out.println(question.getQuestion());
             */
            switch (question.getFormat()) {
                case "Multiple Choice":
                    MultipleChoice mcq = (MultipleChoice) question;
                    /*
                    attempt.attemptQuestion(i, getMultipleChoiceAnswer(mcq.getChoices()));
                     */
                    QuestionUI.askMultipleChoicePanel(mcq, attempt, i);
                    break;
                case "Numerical":
                    Numerical num = (Numerical) question;
                    /*
                    attempt.attemptQuestion(i, getNumericalAnswer());
                     */
                    QuestionUI.askNumericalPanel(num, attempt, i);
                    break;
                case "True False":
                    TrueFalse tf = (TrueFalse) question;
                    /*
                    attempt.attemptQuestion(i, getTrueFalseAnswer());
                     */
                    QuestionUI.askTrueFalsePanel(tf, attempt, i);
                    break;
            }
            i++;
        }

        /*
        System.out.println("Do you want to star this quiz? (t/f) ");
        char userAnswer = scanner.next().charAt(0);
        handleUserStar(userAnswer);
        */

        QuestionUI.handleUserStarPanel(this);
    }

    /*
     * REQUIRES: quiz in the attempt must be completed
     * EFFECTS: generates report for the quiz attempt
     */
    @Override
    public void createReport() {
        JOptionPane.showMessageDialog(null, attempt.generateReport(),
                "Results", JOptionPane.WARNING_MESSAGE);
    }
}
