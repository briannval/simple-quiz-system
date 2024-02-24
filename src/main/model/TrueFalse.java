package model;

import org.json.JSONObject;

/*
 * Represents a true false problem
 * There can only be two answers, two or false
 */
public class TrueFalse implements Question<Boolean> {
    private final boolean correctAnswer;  // either true or false
    private final String format;
    private String question;        // question string
    private int points;             // how much question is worth
    private boolean completed;      // if the user has completed it
    private String explanation;

    public TrueFalse(String question, boolean correctAnswer, int points) {
        this.question = question;
        this.format = "True False";
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.completed = false;
        this.explanation = "";
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
     * REQUIRES: answer must be of type boolean
     * EFFECTS: returns if the answer is correct or not
     */
    @Override
    public boolean checkAnswer(Boolean answer) {
        if (!correctAnswer) {
            return !answer;
        }
        return answer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return this.explanation;
    }

    /*
     * REQUIRES: the explanation must have already been set,
     *           and it must not be the default empty string
     * EFFECTS: gives the explanation of whether the statement in question is
     *          correct, and if yes, why, and if no, why not
     */
    public String getAnswerStatement() {
        if (!correctAnswer) {
            return "This statement is incorrect because " + this.getExplanation();
        }
        return "This statement is correct because " + this.getExplanation();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("correctAnswer", this.correctAnswer);
        json.put("format", this.format);
        json.put("question", this.question);
        json.put("points", this.points);
        json.put("completed", this.completed);
        json.put("explanation", this.explanation);
        return json;
    }
}
