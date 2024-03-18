package ui;

// Abstract class for major UI classes
public abstract class QuizUser {
    private String name;
    private int year;

    public QuizUser(String name, int year) {
        this.name = name;
        this.year = year;
    }

    protected String getName() {
        return this.name;
    }

    protected int getYear() {
        return this.year;
    }

    /*
     * EFFECTS: create quiz / attempt quiz / run application
     */
    public abstract void begin();

    /*
     * EFFECTS: creates report after each begin action
     */
    public abstract void createReport();
}
