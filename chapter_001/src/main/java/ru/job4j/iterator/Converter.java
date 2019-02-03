package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 03.02.2019
 */

public class Converter {

    /**
     * Метод конвертирует итератор с вложенными итераторами в итератор чисел.
     *
     * @param it - итератор с вложенными итераторами.
     * @return итератор чисел.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator = it.next();

            /**
             * Проверяет наличие следующего элемента.
             * @return true - если элемент есть,
             *         false - если элементов больше нет.
             */
            @Override
            public boolean hasNext() {
                if (currentIterator.hasNext()) {
                    return true;
                }
                while (it.hasNext() && !currentIterator.hasNext()) {
                    currentIterator = it.next();
                }
                return currentIterator.hasNext();
            }

            /**
             * Возвращает число и сдвигает указатель, обходя все вложенные итераторы.
             * @return текущее число под указателем.
             */
            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}
