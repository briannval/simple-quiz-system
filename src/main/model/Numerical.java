package model;

import java.util.List;

// Represents a numerical question with integer answer
public class Numerical implements Question<Integer> {
    private final int correctAnswer;
    private final String format;
    private String question;
    private String hint;
    private int points;
    private boolean completed;

    public Numerical(int correctAnswer, String question, int points) {
        this.correctAnswer = correctAnswer;
        this.format = "Numerical";
        this.question = question;
        this.points = points;
        this.hint = "";
        this.completed = false;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the hint for the problem
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean getCompleted() {
        return this.completed;
    }

    @Override
    public String getFormat() {
        return this.format;
    }

    /*
     * REQUIRES: answer must be an integer
     * EFFECTS: returns true if the answer is correct
     */
    @Override
    public boolean checkAnswer(Integer answer) {
        return this.correctAnswer == answer;
    }

    // Invalid for this particular class
    @Override
    public List<String> getChoices() {
        return null;
    }

    public String getHint() {
        return this.hint;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
}
