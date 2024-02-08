package model;

import java.util.List;

/*
 * Represents a question list that can be starred
 * Questions can be of varying format
 * It can be multiple choice, true false, or numerical (as specified)
 * The class automatically calculates the total maximum points achievable
 * Starring will be done from the QuizAttempt class
 */
public class Quiz {
    private int stars;
    private List<Question> questions;
    private int maxPoints;

    /*
     * REQUIRES: questions of varying format (or same, if preferable)
     * EFFECTS: initializes the question list to be the questions provided,
     *          calculates the number of each type of question,
     *          and initializing stars to be 0
     */
    public Quiz(List<Question> questions) {
        this.questions = questions;
        for (Question question: questions) {
            this.maxPoints += question.getPoints();
        }
        this.stars = 0;
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

    /*
     * MODIFIES: this
     * EFFECTS: increments the stars of the quiz by 1
     */
    public void starQuiz() {
        this.stars++;
    }
}
