package ru.job4j.lsp;

import java.util.ArrayList;

/**
 * Класс хранилище Trash
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Trash implements Store {

    /**
     * Хранилище элеметнов класса Food
     */
    private ArrayList<Food> foods;

    public Trash() {
        initFoods();
    }

    /**
     * Инициализирует хранилище элеметнов класса Food
     */
    private void initFoods() {
        this.foods = new ArrayList<>();
    }

    /**
     * Очищает хранилище элеметнов класса Food
     */
    public void clearFoods() {
        initFoods();
    }

    /**
     * @return Возвращает значение поля foods.
     */
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     * @param food добавляет объект в хранилище
     *             элеметнов класса Food
     */
    public void addFood(Food food) {
        this.foods.add(food);
    }

    /**
     * @param food переданный продукт
     * @return возвращает булево значение - примет ли хранилище переданный продукт.
     */
    public boolean accept(Food food) {
        boolean result = false;
        double percentLife = food.evaluatePercentLife();
        if (percentLife >= 100) {
            result = true;
        }
        return result;
    }
}
