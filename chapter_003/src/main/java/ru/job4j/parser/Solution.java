package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Solution {
    private final static Logger LOG = LogManager.getLogger(Solution.class);

    public static void main(String[] args) throws IOException, SQLException {

        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        HTMLParser htmlParser = new HTMLParser(config, storeSQL.getEndingDate());
        storeSQL.addVacancy(htmlParser.getDataFromURL());

    }
}
