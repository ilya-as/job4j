package ru.job4j.list;

/**
 * Класс SimpleArrayList
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет данные в начало списка.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Удаляет первый элемент из списка
     *
     * @return возвращает данные удаляемого элемента
     * или null,если список пустой
     */
    public E delete() {
        if (this.size == 0) {
            return null;
        }
        E temp = first.date;
        first = first.next;
        size--;
        return temp;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
