package ru.job4j.jdbc.xmlxslt;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.sql.*;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        this.prepareDB();
    }

    public void generate(int size) throws SQLException {
        String addElement = "INSERT INTO entry(field) VALUES(?)";
        try {
            PreparedStatement statement = this.connect.prepareStatement(addElement);
            for (int i = 0; i < size; i++) {
                statement.setInt(1, i);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            connect.rollback();
            e.printStackTrace();
        }
    }

    private void prepareDB() {
        String url = config.get("urlDatabase");
        String createTable = "CREATE TABLE IF NOT EXISTS entry (field INTEGER)";
        String clearTable = "DELETE FROM entry";
        try {
            this.connect = DriverManager.getConnection(url);
            if (this.connect != null) {
                Statement statement = this.connect.createStatement();
                statement.executeUpdate(createTable);
                statement.executeUpdate(clearTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<XmlUsage.Entry> load() {
        List<XmlUsage.Entry> entrylist = new ArrayList();
        String getElements = "SELECT field FROM entry";
        try {
            PreparedStatement statement = this.connect.prepareStatement(getElements);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entrylist.add(new XmlUsage.Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrylist;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
