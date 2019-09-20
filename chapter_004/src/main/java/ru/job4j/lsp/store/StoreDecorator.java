package ru.job4j.lsp.store;

import ru.job4j.lsp.FoodStuff;
import ru.job4j.lsp.Store;

import java.util.ArrayList;

/**
 * Абстрактный класс - декоратор для класса Store
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public abstract class StoreDecorator implements Store {

    private Store store;

    public StoreDecorator(Store store) {
        this.store = store;
    }

    @Override
    public void addFood(FoodStuff food) {
        this.store.addFood(food);
    }

    @Override
    public boolean accept(FoodStuff food) {
        return this.store.accept(food);
    }

    @Override
    public ArrayList<FoodStuff> getFoods() {
        return this.store.getFoods();
    }

    @Override
    public void clearFoods() {
        this.store.clearFoods();
    }
}
