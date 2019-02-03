package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по четным чмслам массива.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 29.01.2019
 */
public class EvenNumbersIterator implements Iterator {
    private final int[] items;
    private int index;

    /**
     * Конструктор, инициализирует массив items
     *
     * @param items массив чисел.
     */
    public EvenNumbersIterator(int[] items) {
        this.items = items;
    }

    /**
     * Проверяет наличие четных чисел после указателя.
     *
     * @return true, если далее четные числа есть,
     * false, если далее четных чисел  нет.
     */
    @Override
    public boolean hasNext() {
        boolean res = false;
        for (int i = index; i < items.length; i++) {
            if (items[i] % 2 == 0) {
                index = i;
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * Возвращает текущий элемент и переводит указатель на следующее четное число.
     *
     * @return четное число под индексом index в массиве items.
     * @throws NoSuchElementException в случае если четное число отсутствует.
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return items[index++];
    }

    /**
     * Метод не поддерживается в данной реализации и
     * выбрасывает исключение при попытке использования.
     *
     * @throws UnsupportedOperationException Операция не поддерживается.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}