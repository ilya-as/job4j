package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический контейнер на основе массива.
 *
 * @param <E> Тип данных, которые будут с которыми будет работать хранилище.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 09.02.2019
 */
public class ArrayContainer<E> implements Iterable<E> {
    private Object[] array;
    int size = 0;
    private final static int DEFAULT_CONTAINER_SIZE = 10;
    int modCount = 0;

    /**
     * Конструктор, инициализирует хранилище, размером 10.
     */
    public ArrayContainer() {
        array = new Object[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Конструктор, инициализирует хранилище, заданным размером.
     *
     * @param size заданный размер хранилища.
     */
    public ArrayContainer(int size) {
        array = new Object[size];
    }

    /**
     * Добавляет элемент в хранилище
     * Увеличивает счетчик изменний хранилища
     *
     * @param model объект, который нужно добавить в хранилище.
     */
    public void add(E model) {
        if (array.length < size + 1) {
            growArraySize();
        }
        array[size++] = model;
        modCount++;
    }

    /**
     * Возвращает объект из хранилища.
     *
     * @param index индекс требуемого объекта
     * @return объект, под заданным индексом.
     */
    public E get(int index) {
        checkIndexOutOfBounds(index);
        return (E) array[index];
    }

    /**
     * Увеличивает в 2 раза размер хранилища.
     */
    private void growArraySize() {
        this.array = Arrays.copyOf(this.array, this.array.length * 2);
    }

    /**
     * Проверяет на наличие индекса в хранилище.
     *
     * @param index проверяемый индекс
     */
    public void checkIndexOutOfBounds(int index) {
        if (array.length < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * @return итератор типа <E> для обхода хранилища.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private int expectedModCount = modCount;

            /**
             * Проверяет наличие элементов после указателя.
             * @return true - если после указателя есть элементы.
             *         false - если после указателя элементов нет.
             */
            @Override
            public boolean hasNext() {
                checkModeCount();
                return size > position;
            }

            /**
             * Возвращает текущий элемент и переводит указатель.
             * @return текущий элемент под указателем.
             */
            @Override
            public E next() {
                checkModeCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) array[position++];
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
