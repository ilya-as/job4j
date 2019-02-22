package ru.job4j.list;

/**
 * контейнер Queue FIFO на базе SimpleLinkedList.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> {
    private SimpleLinkedList<T> container;

    public SimpleQueue() {
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
     * Добавляет элемент в конец очереди.
     *
     * @param value элемент.
     */
    public void push(T value) {
        container.add(value);
    }
}
