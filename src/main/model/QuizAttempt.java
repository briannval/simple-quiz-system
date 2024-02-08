package model;

public class QuizAttempt {
    private Quiz currentQuiz;
    private int currentPoints;
    private String status; // "passed", "failed", or "ongoing"

    public QuizAttempt(Quiz currentQuiz) {
        this.currentQuiz = currentQuiz;
        this.currentPoints = 0;
        this.status = "ongoing";
    }

    public int calculatePercentage() {
        return ((this.currentPoints / this.currentQuiz.getMaxPoints()) * 100);
    }

    public boolean determinePass() {
        return (calculatePercentage() >= 0.7);
    }

    public String generateReport() {
        if (determinePass()) {
            return "Congratulations! You have passed with a score of " + String.valueOf(calculatePercentage());
        }
        return "Try harder next time! Your score is " + String.valueOf(calculatePercentage());
    }

    public void willStar(boolean answer) {
        if (answer) {
            currentQuiz.starQuiz();
        }
    }


}
