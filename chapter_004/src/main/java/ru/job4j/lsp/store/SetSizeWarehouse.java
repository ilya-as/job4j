package ru.job4j.lsp.store;

import ru.job4j.lsp.FoodStuff;
import ru.job4j.lsp.Store;

/**
 * Класс хранилище c заданным размером
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SetSizeWarehouse extends StoreDecorator {
    private int capacity;
    private int size;

    public SetSizeWarehouse(Store store, int size) {
        super(store);
        this.capacity = store.getFoods().size();
        this.size = size;
    }

    @Override
    public boolean accept(FoodStuff food) {
        boolean result = false;
        if (capacity < size) {
            result = super.accept(food);
        }
        return result;
    }

    @Override
    public void addFood(FoodStuff food) {
        super.addFood(food);
        capacity++;
    }
}
