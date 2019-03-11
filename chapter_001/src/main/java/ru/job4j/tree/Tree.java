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
        Optional<Node<E>> finded = findBy(parent);
        if (finded.isPresent()) {
            finded.get().add(new Node<>(child));
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
        return new Iterator<E>() {
            /**
             * Итератор List.
             */
            private Iterator<E> it;
            /**
             * Список всех элементов дерева.
             */
            private List<E> list = null;

            /**
             * Проверяет наличие следующих элементов.
             * @return true - если элементы есть.
             *         false - если элементов больше нет.
             */
            @Override
            public boolean hasNext() {
                if (list == null) {
                    list = new LinkedList<>();
                    traverse(root, list);
                    it = list.iterator();
                }
                return it.hasNext();
            }

            /**
             * Возвращает следующее значение узла дерева.
             * @return значение узла.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }

    /**
     * Рекурсивно добавляет все элементы дерева наследуюие от указанного узла в list.
     *
     * @param root корневой элемент.
     * @param list список List для сбора значений.
     */
    private void traverse(Node<E> root, List<E> list) {
        if (root != null) {
            list.add(root.getValue());
            for (Node<E> node : root.leaves()) {
                traverse(node, list);
            }
        }
    }
}
