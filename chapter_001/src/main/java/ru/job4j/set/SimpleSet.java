package ru.job4j.set;

import ru.job4j.list.ArrayContainer;

import java.util.Iterator;

/**
 * Простой Set на базе ArrayContainer.
 *
 * @param <E> Параметризованный тип.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable<E> {

    private ArrayContainer container;

    /**
     * Конструктор, инициализирует хранилище.
     */
    public SimpleSet() {
        this.container = new ArrayContainer<>();
    }

    /**
     * Производит добавление элемента в хранилище.
     * Соблюдается условие, что хранятся только разные элементы.
     *
     * @param value вводимый в хранилище элемент.
     * @return true - если добавление прошло успешно.
     * false - если добавление не удалось.
     */
    public boolean add(E value) {
        if (!containValue(value)) {
            container.add(value);
            return true;
        }
        return false;
    }

    /**
     * Производит проверку на наличе элемента в хранилище.
     *
     * @param value вводимый в хранилище элемент.
     * @return true - если в хранилище есть элемент.
     * false - элемент отсутсвует.
     */
    private boolean containValue(E value) {
        boolean result = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает итератор для обхода коллекции.
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
