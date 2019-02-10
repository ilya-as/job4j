package ru.job4j.generic;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 10.02.2019
 */
public class User extends Base {
    /**
     * Конструктор, инициализирует поля id и name.
     *
     * @param id
     * @param name
     */
    protected User(String id, String name) {
        super(id, name);
    }
}
