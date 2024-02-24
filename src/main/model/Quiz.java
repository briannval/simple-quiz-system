package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.List;

/*
 * Represents a question list that can be starred
 * Questions can be of varying format
 * It can be multiple choice, true false, or numerical (as specified)
 * The class automatically calculates the total maximum points achievable
 * Starring will be done from the QuizAttempt class
 */
public class Quiz implements Writeable {
    private int stars;
    private final List<Question> questions;
    private int maxPoints;
    private final String name;

    /*
     * REQUIRES: questions of varying format (or same, if preferable)
     * EFFECTS: initializes the question list to be the questions provided,
     *          calculates the number of each type of question,
     */
    public Quiz(int stars, List<Question> questions, String name) {
        this.questions = questions;
        for (Question question: questions) {
            this.maxPoints += question.getPoints();
        }
        this.stars = stars;
        this.name = name;
    }

    /*
     * REQUIRES: index must be in range of the question list length
     * EFFECTS: gives a specific question at that index
     */
    public Question getOneQuestion(int index) {
        return questions.get(index);
    }

    /*
     * REQUIRES: answer has to be the same corresponding type as the question,
     * if question is numerical, answer has to be int
     * if question is true / false, answer has to be boolean
     * if question is multiple choice, answer has to be character
     * MODIFIES: this
     * EFFECTS: checks the answer of a particular question and sets it to completed
     */
    // Referenced from the Generics demo from Oracle Website
    // https://docs.oracle.com/javase/tutorial/java/generics/methods.html
    public <T> boolean doQuestion(Question<T> question, T answer) {
        question.setCompleted(true);
        return question.checkAnswer(answer);
    }

    public int getMaxPoints() {
        return this.maxPoints;
    }

    public List<Question> getAllQuestions() {
        return this.questions;
    }

    public int getStars() {
        return this.stars;
    }

    public String getName() {
        return this.name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: increments the stars of the quiz by 1
     */
    public void starQuiz() {
        this.stars++;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stars", this.stars);
        json.put("questions", questionsToJson());
        json.put("name", this.name);
        return json;
    }

    private JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question question: this.questions) {
            jsonArray.put(question.toJson());
        }

        return jsonArray;
    }
}
