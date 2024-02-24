package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private final String filePath;
    private final List<Quiz> quizzes;

    public JsonReader(String filePath) {
        this.filePath = filePath;
        this.quizzes = new ArrayList<>();
    }

    public String readFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(this.filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public List<Quiz> read() throws IOException {
        String jsonData = readFile();
        JSONArray jsonArray = new JSONArray(jsonData);
        parseQuizArray(jsonArray);
        return this.quizzes;
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public List<Question> parseQuestionArray(JSONArray questionsJson) {
        List<Question> questions = new ArrayList<>();
        for (Object questionObjectJson: questionsJson) {
            JSONObject questionObject = (JSONObject) questionObjectJson;
            String questionObjectFormat = (String) questionObject.get("format");
            switch (questionObjectFormat) {
                case "Numerical":
                    int numericalCorrectAnswer = (int) questionObject.get("correctAnswer");
                    String numericalQuestion = (String) questionObject.get("question");
                    int numericalPoints = (int) questionObject.get("points");
                    questions.add(new Numerical(numericalCorrectAnswer, numericalQuestion, numericalPoints));
                    break;
                case "True False":
                    String trueFalseQuestion = (String) questionObject.get("question");
                    boolean trueFalseCorrectAnswer = (boolean) questionObject.get("correctAnswer");
                    int trueFalsePoints = (int) questionObject.get("points");
                    questions.add(new TrueFalse(trueFalseQuestion, trueFalseCorrectAnswer, trueFalsePoints));
                    break;
                case "Multiple Choice":
                    int correctAnswerUnicode = (int) questionObject.get("correctAnswer");
                    char correctAnswer = (char) correctAnswerUnicode;
                    String question = (String) questionObject.get("question");
                    int points = (int) questionObject.get("points");
                    JSONArray choicesArray = new JSONArray(questionObject.get("choices").toString());
                    // Referenced from StackOverflow
                    // https://stackoverflow.com/questions/24882927/
                    // using-streams-to-convert-a-list-of-objects-into-a-string-obtained-from-the-tostr
                    List<String> choices = choicesArray.toList().stream()
                            .map(Object::toString)
                            .collect(Collectors.toList());
                    questions.add(new MultipleChoice(correctAnswer, question, points, choices));
                    break;
            }
        }
        return questions;
    }

    public void parseQuizArray(JSONArray jsonArray) {
        for (Object object: jsonArray) {
            JSONObject quiz = (JSONObject) object;
            int stars = (int) quiz.get("stars");
            JSONArray questionsArray = new JSONArray(quiz.get("questions").toString());
            List<Question> questions = parseQuestionArray(questionsArray);
            String name = (String) quiz.get("name");
            quizzes.add(new Quiz(stars, questions, name));
        }
    }

}
