package ru.job4j.list;

/**
 * контейнер Stack LIFO на базе SimpleLinkedList.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> container;

    public SimpleStack() {
        this.container = new SimpleLinkedList<>();
    }

    /**
     * Удаляет последний элемент из очереди.
     *
     * @return удаленный элемент из начала очереди.
     */
    public T poll() {
        return container.deleteFirst();
    }

    /**
     * Добавляет элемент в начало очереди.
     *
     * @param value элемент.
     */
    public void push(T value) {
        container.insertFirst(value);
    }
}
