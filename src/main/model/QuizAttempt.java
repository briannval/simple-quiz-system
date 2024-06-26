package model;

// Represents a single attempt of a quiz
public class QuizAttempt {
    private final Quiz currentQuiz;
    private int currentPoints;

    /*
     * REQUIRES: currentQuiz has to be a valid quiz
     * EFFECTS: creates a new quiz attempt with a quiz
     *          and current points set to 0
     */
    public QuizAttempt(Quiz currentQuiz) {
        EventLog el = EventLog.getInstance();
        el.logEvent(new Event("Attempting quiz named" + currentQuiz.getName()));
        this.currentQuiz = currentQuiz;
        this.currentPoints = 0;
    }

    /*
     * REQUIRES: answer type has to be in accordion to question at index type
     * MODIFIES: this
     * EFFECTS: attempts a question at a particular index and increments points if it's
     *          correct
     */
    public <T> void attemptQuestion(int index, T answer) {
        Question currentQuestion = this.currentQuiz.getOneQuestion(index);
        boolean result = this.currentQuiz.doQuestion(currentQuestion,answer);
        if (result) {
            currentPoints += currentQuestion.getPoints();
        }
    }

    /*
     * REQUIRES: all questions in the quiz must be completed
     * EFFECTS: calculates the percentage score earned based on current points
     *          and the maximum points that can be earned of the quiz
     */
    public int calculatePercentage() {
        return (getCurrentPoints() * 100) / this.currentQuiz.getMaxPoints();
    }

    /*
     * EFFECTS: determines if the current percentage is pass or fail
     */
    public boolean determinePass() {
        return (calculatePercentage() >= 70);
    }

    /*
     * EFFECTS: generates a short report in according to the score obtained
     *          and whether the user passed
     */
    public String generateReport() {
        EventLog el = EventLog.getInstance();
        int score = calculatePercentage();
        String currName = currentQuiz.getName();
        if (determinePass()) {
            el.logEvent(new Event("An attempt of " + currName + " passed with score " + score));
            return "Congratulations! You have passed with a score of " + calculatePercentage();
        }
        el.logEvent(new Event("An attempt of " + currName + " failed with score " + score));
        return "Try harder next time! Your score is " + calculatePercentage();
    }

    /*
     * EFFECTS: stars the quiz in attempt
     */
    public void willStar() {
        EventLog el = EventLog.getInstance();
        currentQuiz.starQuiz();
        String currName = currentQuiz.getName();
        int newStars = currentQuiz.getStars();
        String starDescription = "Starred quiz for " + currName + " to " + newStars + " stars.";
        el.logEvent(new Event(starDescription));
    }

    public int getCurrentPoints() {
        return this.currentPoints;
    }

    public Quiz getCurrentQuiz() {
        return this.currentQuiz;
    }
}
