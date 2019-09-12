package ru.job4j.lsp;

import java.util.ArrayList;

/**
 * Класс хранилище Warehouse
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Warehouse implements Store {

    /**
     * Хранилище элеметнов класса Food
     */
    private ArrayList<Food> foods;

    public Warehouse() {
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
        if (percentLife < 25) {
            result = true;
        }
        return result;
    }

}
