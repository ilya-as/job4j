package ru.job4j.parser;

import java.util.Calendar;

public interface ParsedItem {
    String getName();
    String getText();
    String getUrl();
    Calendar getDate();
}
