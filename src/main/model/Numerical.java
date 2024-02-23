package model;

import org.json.JSONObject;
import persistence.Writeable;

// Represents a numerical question with integer answer
public class Numerical implements Question<Integer> {
    private final int correctAnswer;
    private final String format;
    private String question;
    private int points;
    private boolean completed;

    /*
     * REQUIRES: correctAnswer must be an integer
     *           question must be a valid question, of non-zero length
     *           points is greater than 0
     * EFFECTS: constructs a numerical question with different types of
     */
    public Numerical(int correctAnswer, String question, int points) {
        this.correctAnswer = correctAnswer;
        this.format = "Numerical";
        this.question = question;
        this.points = points;
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

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("correctAnswer", correctAnswer);
        json.put("format", this.format);
        json.put("question", this.question);
        json.put("points", this.points);
        return json;
    }
}
