package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для четным чмслам массива.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 29.01.2019
 */
public class EvenNumbersIterator implements Iterator {
    private final int[] items;
    private int index;

    /**
     * Конструктор, инициализирует массив items и ставит указатель index
     * на первое чётное число если оно есть, в противном случае указатель остается на -1.
     *
     * @param items массив чисел.
     */
    public EvenNumbersIterator(int[] items) {
        this.items = items;
        this.index = -1;
        this.index = nextIndexEvenElement();
    }

    /**
     * Проверяет наличие четных чисел после указателя.
     *
     * @return true, если далее четные числа есть,
     * false, если далее четных чисел  нет.
     */
    @Override
    public boolean hasNext() {
        return index > -1;
    }

    /**
     * Ищет следующее четное число после указателя в массиве items,
     * Если число не найдено, возвращает -1.
     *
     * @return индекс четного числа в массиве items, либо -1.
     */
    public int nextIndexEvenElement() {
        for (int i = index + 1; i < items.length; i++) {
            if (items[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает текущий элемент и переводит указатель на следующее четное число.
     *
     * @return четное число под индексом index в массиве items.
     * @throws NoSuchElementException в случае если четное число отсутствует.
     */
    @Override
    public Object next() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        int currentElement = items[index];
        index = nextIndexEvenElement();
        return currentElement;
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