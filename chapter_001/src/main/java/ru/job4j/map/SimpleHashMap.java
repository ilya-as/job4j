package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleHashMap
 *
 * @param <K> Параметризованный тип ключа.
 * @param <V> Параметризованный тип значения.
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>> {

    /**
     * Главное хранилище.
     */

    private Node<K, V>[] storage;
    /**
     * Константа - размер хранилища по умолчанию.
     */
    private static final int DEFAULT_DATA_SIZE = 20;

    /**
     * Константа - коэффициент загрузки хранилища.
     */
    private static final double LOAD_FACTOR = 0.5;

    /**
     * Количество хранимых элементов в коллекции.
     */
    private int size;

    /**
     * Счетчик операций, которые могут повлиять на правильную работу итератора.
     */
    private int modCount;

    /**
     * Конструктор, инициализирует хранилище размером 20.
     */
    public SimpleHashMap() {
        this.storage = new Node[DEFAULT_DATA_SIZE];
    }

    /**
     * Конструктор, инициализирует хранилище заданным размером.
     *
     * @param size - заданный размер.
     */
    public SimpleHashMap(int size) {
        this.storage = (Node<K, V>[]) new Node[size];
    }

    /**
     * Производит добавление пары ключ - значение в хранилище.
     *
     * @param key   ключ.
     * @param value значение.
     * @return true - если хранилище не содержит добавляемого ключа и если не возникло коллизии.
     * false - если хранилище уже содержит добавляемый ключ либо если возникла коллизия.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if ((double) this.size / storage.length > LOAD_FACTOR) {
            resize();
        }
        int i = hash(key);
        if (this.storage[i] == null) {
            this.storage[i] = new Node<>(key, value);
            result = true;
        }
        this.size++;
        this.modCount++;
        return result;
    }

    /**
     * @param key ключ.
     * @return Значения ключа, если такой ключ содержится в коллекции.
     * @throws NoSuchElementException если ключа нет в коллекции.
     */
    public V get(K key) {
        int i = hash(key);
        return (this.storage[i] != null && key.equals(this.storage[i].key) ? this.storage[i].value : null);
    }

    /**
     * Возвращает количество хранимых в коллекции пар.
     */
    public int getSize() {
        return size;
    }

    /**
     * Удаляет пару ключ - значение из хранилища.
     *
     * @param key ключ заданной для удаления пары.
     * @return true - если пара удалена из коллекции.
     * false - если ключ не найден в коллекции,
     * либо если объект ключ не совпадает с объектом хранящимся под вычисленным индексом(Возможно при коллизии).
     */
    public boolean delete(K key) {
        boolean result = false;
        int i = hash(key);
        if (this.storage[i] != null && key.equals(this.storage[i].key)) {
            this.storage[i] = null;
            result = true;
        }
        this.size--;
        modCount++;
        return result;
    }

    /**
     * Вычисляет хэш модуль ключа, в зависимости от размеров хэш таблицы.
     *
     * @param key заданный для вычисления хэш модуля ключ.
     * @return хэш модуль заданного ключа.
     */
    private int hash(K key) {
        return key.hashCode() % storage.length;
    }

    /**
     * Увеличивает размер таблицы и производит перехеширование
     * всех элементов, в соответствии с новым размером.
     */
    private void resize() {
        Node<K, V>[] newStorage = new Node[this.storage.length * 2];
        for (Node<K, V> data : this.storage) {
            if (data != null) {
                int i = hash((K) data.key);
                newStorage[i] = data;
            }
        }
        this.storage = newStorage;
    }

    /**
     * @return возвращает итератор для обхода коллекции.
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {

            /**
             * Указатель для обхода коллекции.
             */
            private int index = 0;

            /**
             * Число изменений коллекции, на момент создания итератора.
             */
            private int currentModCount = modCount;

            /**
             * @return true - если за указателем есть ненулевой элемент.
             *         false - если ненулевых элементов больше нет.
             */
            @Override
            public boolean hasNext() {
                checkConcurrentModification();
                boolean res = false;
                for (int i = index; i < storage.length; i++) {
                    if (storage[i] != null) {
                        index = i;
                        res = true;
                        break;
                    }
                }
                return res;
            }

            /**
             * проверяет изменение коллекции.
             */
            private void checkConcurrentModification() {
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            /**
             * Возвращает следующий не нулевой элемент за указателем.
             * Переводит указатель на текущий ненулевой элемент + 1.
             * @return ненулевой элемент.
             */
            @Override
            public Node<K, V> next() {
                checkConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = storage[index];
                index++;
                return result;
            }
        };
    }

    /**
     * Класс, описывающий объекты, загружаемые в главное хранилище.
     *
     * @param <K> Параметризованный тип ключа.
     * @param <V> Параметризованный тип значения.
     */
    public static class Node<K, V> {
        K key;
        V value;

        /**
         * @return значение.
         */
        public V getValue() {
            return value;
        }

        /**
         * Конструктор, инициализирует поля ключ, значение.
         *
         * @param key   ключ.
         * @param value значение.
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
