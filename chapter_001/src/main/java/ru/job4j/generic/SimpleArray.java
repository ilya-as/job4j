package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Простое хранилище данных на основе массива.
 *
 * @param <T> Тип данных, которые будут с которыми будет работать хранилище.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 09.02.2019
 */
public class SimpleArray<T> implements Iterable<T> {
    private final Object[] array;
    int index = 0;

    /**
     * Конструктор, инициализирует хранилище, заданным размером.
     *
     * @param size заданный размер хранилища.
     */
    public SimpleArray(int size) {
        array = new Object[size];
    }

    /**
     * Конструктор, инициализирует хранилище, размером 10.
     */
    public SimpleArray() {
        array = new Object[10];
    }

    /**
     * Добавляет элемент в хранилище
     *
     * @param model объект, который нужно добавить в хранилище.
     */
    public void add(T model) {
        checkIndexOutOfBounds(index + 1);
        array[index++] = model;
    }

    /**
     * Заменяет объект.
     *
     * @param position индекс заменяемого объекта.
     * @param model    объект, который помещаем в
     *                 хранилище на место заменяемого объекта.
     */
    public void set(int position, T model) {
        checkIndexOutOfBounds(position);
        array[position] = model;
    }

    /**
     * Удаляет элемент из хранилища со сдвигом.
     *
     * @param position индекс удаляемого элемента.
     */
    public void remove(int position) {
        checkIndexOutOfBounds(position);
        index--;
        System.arraycopy(array, position + 1, array, position, array.length - position - 1);
    }

    /**
     * Возвращает объект из хранилища.
     *
     * @param position индекс требуемого объекта
     * @return объект, под заданным индексом.
     */
    public T get(int position) {
        checkIndexOutOfBounds(position);
        return (T) array[position];
    }

    /**
     * @return количество элементов в хранилище.
     */
    public int size() {
        return index;
    }

    /**
     * Проверяет на наличие индекса в хранилище.
     *
     * @param position проверяемый индекс
     */
    public void checkIndexOutOfBounds(int position) {
        if (array.length < position) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * @return итератор типа <T> для обхода хранилища.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position = 0;

            /**
             * Проверяет наличие элементов после указателя.
             * @return true - если после указателя есть элементы.
             *         false - если после указателя элементов нет.
             */
            @Override
            public boolean hasNext() {
                return index > position;
            }

            /**
             * Возвращает текущий элемент и переводит указатель.
             * @return текущий элемент под указателем.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[position++];
            }
        };
    }
}
