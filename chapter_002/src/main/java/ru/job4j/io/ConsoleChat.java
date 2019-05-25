package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConsoleChat {
    private final String PATH_TO_PROPERTIES;
    private String pathToLog;
    private String pathToAnswer;
    private ArrayList<String> AnswerList;
    private boolean needsAnswer = true;
    private final Map<String, Runnable> acts = new HashMap<>();

    public ConsoleChat(String PATH_TO_PROPERTIES) {
        this.PATH_TO_PROPERTIES = PATH_TO_PROPERTIES;
        readProperty();
        acts.put("stop", () -> this.turnAnswers(false));
        acts.put("continue", () -> this.turnAnswers(true));
        acts.put("finish", () -> this.turnAnswers(false));
    }

    public void startChat() throws IOException {
        String str;
        String answer = "";
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(System.in));

        try (FileWriter fw = new FileWriter(pathToLog)) {
            do {
                str = br.readLine();
                if (acts.containsKey(str)) {
                    acts.get(str).run();
                }
                if (needsAnswer()) {
                    answer = getAnswer();
                    System.out.println(answer);
                }
                fw.write(str + answer + "\r\n");
            } while (!str.equals("finish"));
        }
    }

    private void turnAnswers(boolean checkValue) {
        this.needsAnswer = checkValue;
    }

    private boolean needsAnswer() {
        return this.needsAnswer;
    }

    private void readProperty() {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            pathToAnswer = prop.getProperty("pathToAnswer");
            pathToLog = prop.getProperty("pathToLog");
            populateAnswerList();
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен!");
            e.printStackTrace();
        }
        if (AnswerList.isEmpty()) {
            throw new NullPointerException("Лист ответов не заполнен!");
        }
    }

    private void populateAnswerList() {
        AnswerList = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(pathToAnswer))) {
            read.lines().filter(line -> !line.equals("")).forEach(s -> AnswerList.add(s));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + PATH_TO_PROPERTIES);
            e.printStackTrace();
        }
    }

    private String getAnswer() {
        String result;
        result = AnswerList.get(randomIndex());
        return result;
    }

    private int randomIndex() {
        int max = AnswerList.size();
        return (int) (Math.random() * max);
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("chapter_002/src/main/resources/app.properties");
        consoleChat.startChat();
    }
}
