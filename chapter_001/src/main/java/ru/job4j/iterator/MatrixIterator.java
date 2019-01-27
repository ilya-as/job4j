package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двумерного массива.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 27.01.2019
 */
public class MatrixIterator implements Iterator {
    private final int[][] items;
    private int rows = 0;    //ряды
    private int columns =0;  //колонки

    /**
     * Конструктор, инициализирует поле items.
     * @param items двумерный массив.
     */
    public MatrixIterator(int[][] items) {
        this.items = items;
    }

    /**
     * Проверяет наличие следующего элемента.
     * @return true - следующий элемент существует,
     *         false - конец массива.
     */
    @Override
    public boolean hasNext() {
        return items[rows].length > columns &&items.length > rows;
    }

    /**
     * Возвращает текущий элемент и сдвигает указатели.
     * @return текущий элемент массива.
     * @throws NoSuchElementException - в случае пустого массива.
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int currentElement = items[rows][columns];
        if (columns == (items[rows].length-1)&&(items.length-1 > rows)){
            columns=0;
            rows++;}
        else {columns++;}
        return currentElement;
    }
}
