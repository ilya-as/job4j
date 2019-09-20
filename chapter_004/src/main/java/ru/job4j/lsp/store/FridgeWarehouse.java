package ru.job4j.lsp.store;

import ru.job4j.lsp.FoodStuff;
import ru.job4j.lsp.Store;

/**
 * Хранилище-холодильник
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class FridgeWarehouse extends StoreDecorator {
    public FridgeWarehouse(Store store) {
        super(store);
    }

    @Override
    public boolean accept(FoodStuff food) {
        double percentLife = food.evaluatePercentLife();
        return food.isVegetable() && percentLife < 25;
    }
}
