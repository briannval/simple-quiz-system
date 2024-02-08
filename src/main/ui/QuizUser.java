package ui;

public abstract class QuizUser {
    protected String name;
    protected int year;

    public QuizUser(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public abstract boolean begin();

    public abstract void createReport();
}
