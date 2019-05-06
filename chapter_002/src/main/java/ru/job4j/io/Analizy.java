package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    private String timeStart;
    private List<String> timeList = new ArrayList<>();

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().filter(line -> !line.equals("")).forEach(s -> {
                String[] line = s.split(" ");
                int statusKey = Integer.parseInt(line[0]);
                if (timeStart == null && statusKey >= 400) {
                    timeStart = line[1] + ";";
                } else if (timeStart != null && statusKey < 400) {
                    timeList.add(timeStart + line[1]);
                    timeStart = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line : timeList) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}
