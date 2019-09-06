package ru.job4j.parser;

import java.io.IOException;
import java.util.ArrayList;

public interface Parser {
    ArrayList<ParsedItem> getDataFromURL() throws IOException;
}
