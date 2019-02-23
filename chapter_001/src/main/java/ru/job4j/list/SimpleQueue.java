package ru.job4j.list;

/**
 * контейнер Queue FIFO на 2-х стеках.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> {
    private SimpleStack<T> enqueue;
    private SimpleStack<T> dequeue;

    public SimpleQueue() {
        this.enqueue = new SimpleStack<>();
        this.dequeue = new SimpleStack<>();
    }

    /**
     * Переносит элементы из enqueue в dequeue
     * Удаляет первый элемент из очереди dequeue.
     *
     * @return удаленный элемент из начала очереди.
     */
    public T poll() {
        if (dequeue.isEmpty()) {
            while (!enqueue.isEmpty()) {
                dequeue.push(enqueue.poll());
            }
        }
        return dequeue.poll();
    }

    /**
     * Добавляет элемент в начало стека enqueue.
     *
     * @param value элемент.
     */
    public void push(T value) {
        enqueue.push(value);
    }
}
