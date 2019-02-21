package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Структура данных на основе связного списка.
 *
 * @param <E> Параметризованный тип списка.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 21.02.2019
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    /**
     * Добавляет объект в хранилище.
     *
     * @param value объект, который необходимо добавить в хранилище.
     */
    public void add(E value) {
        Node<E> node = new Node<>(value);
        if (this.size == 0) {
            this.first = node;
        } else {
            this.last.next = node;
        }
        this.last = node;
        modCount++;
        size++;
    }

    /**
     * Возвращает ссылку на объект из хранилища по заданному индексу.
     *
     * @param index заданный индекс.
     * @return ссылка на запрашиваемый объект.
     */
    public E get(int index) {
        checkIndexOutOfBounds(index);
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Проверяет на наличие индекса в хранилище.
     *
     * @param index проверяемый индекс
     */
    public void checkIndexOutOfBounds(int index) {
        if (size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Внутренний класс, описывающий объекты Node, которые будут
     * храниться в главном хранилище и обеспечивать работу
     * связного списка. У объектов есть поля:
     * data - хранимый в объекте элемент.
     * next - ссылка на следующий Node.
     * prev - ссылка на предыдущий Node.
     *
     * @param <E> Параметризованный тип Node.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    /**
     * @return возвращает итератор для обхода списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            private int expectedModCount = modCount;
            private int position = 0;

            /**
             * Проверяет наличие объектов за указателем.
             * @return true - если объект есть.
             *         false - если объектов больше нет.
             */
            @Override
            public boolean hasNext() {
                checkModeCount();
                return this.position < size;
            }

            /**
             * Возвращает текущий объект и переводит указатель.
             * @return ссылка на текущий объект под указателем.
             */
            @Override
            public E next() {
                checkModeCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.data;
                current = current.next;
                position++;
                return result;
            }

            /**
             * Провереяет - изменилось ли хранилище после создания итератора.
             */
            private void checkModeCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
