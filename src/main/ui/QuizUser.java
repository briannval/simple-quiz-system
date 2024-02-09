package ui;

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

    public abstract void begin();

    public abstract void createReport();
}
