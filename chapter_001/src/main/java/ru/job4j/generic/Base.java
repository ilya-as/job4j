package ru.job4j.generic;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 10.02.2019
 */
public abstract class Base {
    private final String id;
    private final String name;

    protected Base(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
