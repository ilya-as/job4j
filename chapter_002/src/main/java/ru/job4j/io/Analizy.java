package ru.job4j.io;

import java.io.*;

public class Analizy {

    private boolean writeLog = false;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {

            read.lines().filter(line -> !line.equals("")).forEach(s -> {
                String[] line = s.split(" ");
                int statusKey = Integer.parseInt(line[0]);
                if (!writeLog && statusKey >= 400) {
                    writeLog = true;
                    try {
                        writer.write(line[1] + ";");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (writeLog && statusKey < 400) {
                    writeLog = false;
                    try {
                        writer.write(line[1] + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}
