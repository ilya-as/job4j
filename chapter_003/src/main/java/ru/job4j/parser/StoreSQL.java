package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
     * Создает таблицу vacancy в базе данных, если её нет.
     */
    private void prepareDB() {
        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");
        String driver = config.get("driver");
        String createTable = "CREATE TABLE IF NOT EXISTS vacancy (id  serial primary key not null,  name TEXT," +
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
     * Добавляет вакансии из ArrayList в базу данных.
     * Учитывает уникальность поля name
     *
     * @param vacancyArray ArrayList вакансий.
     */
    public void addVacancy(ArrayList<Vacancy> vacancyArray) throws SQLException {
        String insertQuery = "INSERT INTO vacancy (name, url, text, date) VALUES (?, ?, ?, ?)" +
                " ON CONFLICT (name) DO NOTHING";
        try {
            PreparedStatement statement = this.connect.prepareStatement(insertQuery);
            for (Vacancy vacancy : vacancyArray) {
                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getUrl());
                statement.setString(3, vacancy.getText());
                statement.setTimestamp(4, new Timestamp(vacancy.getDate().getTime().getTime()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            connect.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * @return Возвращает дату самой свежей вакансии из базы данных.
     * Если БД пустая - вернет дату год назад от сегодня.
     */
    public Calendar getEndingDate() {
        Calendar calendar = new GregorianCalendar();
        String maxDateQuery = "SELECT MAX(date) AS date FROM vacancy";
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
     * @return Возвращает ArrayList вакансий из базы данных.
     */
    public ArrayList<Vacancy> getAll() throws SQLException {
        ArrayList<Vacancy> vacancyArray = new ArrayList<>();
        String getAllQuery = "SELECT * FROM vacancies";
        try {
            PreparedStatement statement = this.connect.prepareStatement(getAllQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                Timestamp timestamp = resultSet.getTimestamp("date");
                calendar.setTimeInMillis(timestamp.getTime());
                vacancyArray.add(new Vacancy(
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("url"),
                        calendar));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancyArray;
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
