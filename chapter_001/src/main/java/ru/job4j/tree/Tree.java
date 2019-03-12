package ru.job4j.tree;

import java.util.*;

/**
 * Структура данных - простое дерево.
 *
 * @param <E> Параметризованный тип данных, хранящихся в коллекции
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Корень дерева.
     */
    private Node<E> root;

    /**
     * Конструктор, инициализирует корень дерева.
     *
     * @param value корень
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Добавляет элемент в дерево.
     *
     * @param parent корень к которому добавляем.
     * @param child  добавляемый элемент.
     * @return true - если добавление прошло успешно.
     * false - если не удалось добавить элемент.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> findedParent = findBy(parent);
        Optional<Node<E>> findedChild = findBy(child);
        if (findedParent.isPresent() && !findedChild.isPresent()) {
            findedParent.get().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    /**
     * Производит поиск заданного элемента.
     *
     * @param value заданный элемент.
     * @return узел, заданного элемента.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * @return Итератор, для обхода дерева.
     */
    @Override
    public Iterator<E> iterator() {

        /**
         * Список элементов текущего уровня и потомков текущего корневого элемента.
         */
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        return new Iterator<E>() {

            /**
             * Проверяет наличие следующих элементов.
             * @return true - если элементы есть.
             *         false - если элементов больше нет.
             */
            @Override
            public boolean hasNext() {

                return !queue.isEmpty();
            }

            /**
             * Возвращает следующее значение узла дерева.
             * @return значение узла.
             */
            @Override
            public E next() {
                if (!(hasNext())) {
                    throw new NoSuchElementException();
                } else {
                    Node<E> el = queue.poll();
                    for (Node<E> child : el.leaves()) {
                        queue.offer(child);
                    }
                    return el.getValue();
                }
            }
        };
    }
}
