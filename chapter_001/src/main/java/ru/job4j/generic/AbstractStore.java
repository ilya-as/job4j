package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * Релизует методы для хранилищ: UserStore и RoleStore.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 10.02.2019
 */
public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> simpleArray = new SimpleArray();

    /**
     * Добавляет объект в хранилище.
     *
     * @param model объект.
     */
    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    /**
     * Вводит объект в хранилище на место объекта с заданным id.
     *
     * @param id    заданный id.
     * @param model объект для замены.
     * @return true - если замена проведена успешна.
     * false - если замена не удалась.
     */
    @Override
    public boolean replace(String id, T model) {
        int index = findIndexById(id);
        if (indexFound(index)) {
            simpleArray.set(index, model);
            return true;
        }
        return false;
    }

    /**
     * Удаляет объект из хранилища по заданному id объекта.
     *
     * @param id объекта.
     * @return true - если удаление проведено успешно.
     * false - если удаление не удалось.
     */
    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if (indexFound(index)) {
            simpleArray.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Возвращает объект по id.
     *
     * @param id объекта
     * @return объект с заданным id.
     */
    @Override
    public T findById(String id) {
        int index = findIndexById(id);
        if (!indexFound(index)) {
            throw new NoSuchElementException();
        }
        return simpleArray.get(index);
    }

    /**
     * Производит поиск объекта в хранилище по id.
     *
     * @param id заданный для поиска.
     * @return индекс объекта в хранилище, либо -1, если объекта нет.
     */
    public int findIndexById(String id) {
        int index = -1;
        for (int i = 0; i < simpleArray.size(); i++) {
            if (simpleArray.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Производит проверку на существование индекса в хранилище.
     *
     * @param index текущий индекс.
     * @return если индекс -1, значит такой индекс в хранилище не найден.
     */
    public boolean indexFound(int index) {
        return (index != -1);
    }
}

