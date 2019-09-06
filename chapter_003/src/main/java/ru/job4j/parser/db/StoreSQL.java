package ru.job4j.parser.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.parser.utils.Config;
import ru.job4j.parser.ParsedItem;

import java.sql.Connection;
import java.util.*;
import java.sql.*;

/**
 * Класс, для работы с базой данных.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {

    /**
     * Хранит настройки.
     */
    private final Config config;
    /**
     * Хранит соединение с базой данных.
     */
    private Connection connect;

    /**
     * Логирование.
     */
    private final static Logger LOG = LogManager.getLogger(Config.class);

    public StoreSQL(Config config) {
        this.config = config;
        this.prepareDB();
    }

    /**
     * Инициализирует соединение с базой данных.
     * Создает таблицу parsed_data в базе данных, если её нет.
     */
    private void prepareDB() {
        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");
        String driver = config.get("driver");
        String createTable = "CREATE TABLE IF NOT EXISTS parsed_data (id  serial primary key not null,  name TEXT," +
                "url TEXT, text TEXT, date TIMESTAMP)";
        try {
            Class.forName(driver);
            this.connect = DriverManager.getConnection(url, username, password);
            if (this.connect != null) {
                Statement statement = this.connect.createStatement();
                statement.executeUpdate(createTable);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Добавляет распрарсенный элемент из ArrayList в базу данных.
     * Учитывает уникальность поля name
     *
     * @param parsedItemsArray ArrayList распрарсенных элементов.
     */
    public void addElement(ArrayList<ParsedItem> parsedItemsArray) throws SQLException {
        String insertQuery = "INSERT INTO parsed_data (name, url, text, date) VALUES (?, ?, ?, ?)" +
                " ON CONFLICT (name) DO NOTHING";
        try {
            PreparedStatement statement = this.connect.prepareStatement(insertQuery);
            for (ParsedItem item : parsedItemsArray) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getUrl());
                statement.setString(3, item.getText());
                statement.setTimestamp(4, new Timestamp(item.getDate().getTime().getTime()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            connect.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * @return Возвращает дату самой свежей записи из базы данных.
     * Если БД пустая - вернет дату год назад от сегодня.
     */
    public Calendar getEndingDate() {
        Calendar calendar = new GregorianCalendar();
        String maxDateQuery = "SELECT MAX(date) AS date FROM parsed_data";
        try {
            PreparedStatement statement = this.connect.prepareStatement(maxDateQuery);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Timestamp timestamp = resultSet.getTimestamp("date");
            if (timestamp != null) {
                calendar.setTimeInMillis(timestamp.getTime());
            } else {
                calendar.add(Calendar.YEAR, -1);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return calendar;
    }

    /**
     * Закрывает подключение к базе данных.
     */
    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
