package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.List;

/*
 * Represents a multiple choice question with four options
 * The options are 'A','B','C', or 'D'
 * Only one of the options are correct
 */
public class MultipleChoice implements Question<Character>, Writeable {
    private final char correctAnswer;
    private final String format;
    private String question;
    private final List<String> choices;
    private int points;
    private boolean completed;

    /*
     * REQUIRES: question has a non-zero length;
     *           points is greater than 0;
     *           each element of choice has a non-zero length;
     *           correctAnswer is either 'A','B','C','D' (char)
     * EFFECTS: constructs a multiple choice question
     *          with four different choices and one correct answer
     *          has points worth
     */
    public MultipleChoice(char correctAnswer, String question, int points, List<String> choices) {
        this.correctAnswer = correctAnswer;
        this.format = "Multiple Choice";
        this.question = question;
        this.points = points;
        this.choices = choices;
        this.completed = false;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    public char getCorrectAnswer() {
        return this.correctAnswer;
    }

    /*
     * REQUIRES: answer is either 'A','B','C', or 'D'
     * EFFECTS: checks whether is the answer is correct,
     *          returning true if it is and false otherwise
     */
    @Override
    public boolean checkAnswer(Character answer) {
        return this.correctAnswer == answer;
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

    public List<String> getChoices() {
        return this.choices;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("correctAnswer", this.correctAnswer);
        json.put("format", this.format);
        json.put("question", this.question);
        json.put("points", String.valueOf(this.points));
        json.put("choices", choicesToJson());
        return json;
    }

    private JSONArray choicesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String choice: this.choices) {
            jsonArray.put(choice);
        }

        return jsonArray;
    }
}
