package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Класс User для тестирования коллекции map
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class User {

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
