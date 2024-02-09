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

    public <T> void attemptQuestion(int index, T answer) {
        Question currentQuestion = this.currentQuiz.getOneQuestion(index);
        boolean result = this.currentQuiz.doQuestion(currentQuestion,answer);
        if (result) {
            currentPoints += currentQuestion.getPoints();
        }
    }

    public int calculatePercentage() {
        return (getCurrentPoints() * 100) / this.currentQuiz.getMaxPoints();
    }

    public boolean determinePass() {
        return (calculatePercentage() >= 70);
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

    public int getCurrentPoints() {
        return this.currentPoints;
    }

    public Quiz getCurrentQuiz() {
        return this.currentQuiz;
    }

    public String getStatus() {
        return this.status;
    }
}
