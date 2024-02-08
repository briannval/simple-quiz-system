package model;

// Represents a question being able to output question
// and check answer
public interface Question<T> {
    String getQuestion();

    int getPoints();

    void setPoints(int points);

    void setQuestion(String question);

    void setCompleted(boolean completed);

    boolean getCompleted();

    String getFormat();

    boolean checkAnswer(T answer);
}
