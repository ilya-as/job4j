package ru.job4j.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {

    /**
     * Проверяет статистику по изменениям коллекции
     *
     * @param previous - список до изменений.
     * @param current  - текущий список.
     * @return возвращает статистику изменений списка.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> currentMap = new HashMap<>();
        for (User curr : current) {
            currentMap.put(curr.id, curr);
        }
        for (User previousUser : previous) {
            boolean idEquals = currentMap.containsKey(previousUser.id);
            if (idEquals && !currentMap.get(previousUser.id).equals(previousUser)) {
                info.addChanged();
            } else if (!idEquals) {
                info.addDeleted();
            }
        }
        info.added = current.size() - (previous.size() - info.deleted);
        return info;

    }

    /**
     * Хранение данные пользователя.
     */
    public static class User {
        /**
         * Уникальный идентификатор пользователя.
         */
        int id;

        /**
         * Имя пользователя.
         */
        String name;

        /**
         * Конструктор инициализирует класс.
         *
         * @param id   - уникальный идентификатор пользователя.
         * @param name - имя пользователя.
         */
        public User(int id, String name) {

            this.id = id;
            this.name = name;
        }

        /**
         * Сравнивает пользователей по имени.
         *
         * @param o - пользователь с которым сравниваем.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    /**
     * Хранение статистики изменения коллекции.
     */
    public static class Info {

        /**
         * Количество добавленных элементов.
         */
        int added;

        /**
         * Количество измененных элементов.
         */
        int changed;

        /**
         * Количество удаленных элементов.
         */
        int deleted;


        /**
         * Увеличивает статистику по удаленным.
         */
        public void addDeleted() {
            deleted++;
        }

        /**
         * Увеличивает статистику по имененным.
         */
        public void addChanged() {
            changed++;
        }

        /**
         * Представление статистики.
         *
         * @return представление статистики.
         */
        @Override
        public String toString() {
            return "Info{" +
                    "changed=" + changed +
                    ", added=" + added +
                    ", deleted=" + deleted +
                    '}';
        }

    }
}
