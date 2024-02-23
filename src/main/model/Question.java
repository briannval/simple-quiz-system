package model;

import persistence.Writeable;

// Represents an interface for question being able to output question
// and check answer with different inputs
// includes commonly used getters and setters
public interface Question<T> extends Writeable {
    String getQuestion();

    int getPoints();

    void setPoints(int points);

    void setQuestion(String question);

    void setCompleted(boolean completed);

    boolean getCompleted();

    String getFormat();

    boolean checkAnswer(T answer);


}
