package ru.job4j.parser.items;

import ru.job4j.parser.ParsedItem;

import java.util.Calendar;

/**
 * Описывает вакансию.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Vacancy implements ParsedItem {
    /**
     * Заголовок темы вакансии.
     */
    private String name;
    /**
     * Текст вакансии.
     */
    private String text;
    /**
     * URL вакансии.
     */
    private String url;
    /**
     * Дата размещения вакансии.
     */
    private Calendar date;

    /**
     * Инициализирует:
     *
     * @param name Тема вакансии.
     * @param text Описание.
     * @param url  Ссылка.
     * @param date Дата размещения.
     */
    public Vacancy(String name, String text, String url, Calendar date) {
        this.name = name;
        this.text = text;
        this.url = url;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
