package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по четным числам массива.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 29.01.2019
 */
public class EvenNumbersIterator implements Iterator {
    private final int[] items;
    private int index = 0;

    /**
     * Конструктор, инициализирует поле items.
     * @param items двумерный массив.
     */
    public EvenNumbersIterator(int[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return nextIndexEvenElement()> -1;
    }

    public int nextIndexEvenElement() {
        for (int i=index; i<items.length; i++) {
            if (items[i] % 2 == 0){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index = nextIndexEvenElement();
        int currentElement = items[index];
        index++;
        return currentElement;
    }
}
