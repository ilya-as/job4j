package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConsoleChat {
    private String PATH_TO_PROPERTIES;
    private String PATH_TO_LOG;
    private String PATH_TO_Answer;
    private ArrayList<String> AnswerList = new ArrayList<>();
    private boolean needsAnswer = true;

    public ConsoleChat(String PATH_TO_PROPERTIES) {
        this.PATH_TO_PROPERTIES = PATH_TO_PROPERTIES;
        readProperty();
    }

    public void startChat() throws IOException {
        String str;
        String answer = "";
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(System.in));

        try (FileWriter fw = new FileWriter(PATH_TO_LOG)) {
            do {
                str = br.readLine();
                if (str.compareTo("finish") == 0) {
                    break;
                } else if (str.compareTo("stop") == 0) {
                    needsAnswer = false;
                } else if (str.compareTo("continue") == 0) {
                    needsAnswer = true;
                }

                if (needsAnswer) {
                    answer = getAnswer();
                    System.out.println(answer);
                }
                str = str + answer + "\r\n";
                fw.write(str);
            } while (str.compareTo("finish") != 0);
        }
    }

    private void readProperty() {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
            PATH_TO_Answer = prop.getProperty("PATH_TO_Answer");
            PATH_TO_LOG = prop.getProperty("PATH_TO_LOG");
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
        try (BufferedReader read = new BufferedReader(new FileReader(PATH_TO_Answer))) {
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
