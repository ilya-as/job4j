package ru.job4j.lsp.store;

import ru.job4j.lsp.FoodStuff;
import ru.job4j.lsp.Store;

/**
 * Хранилище-переработчик
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Reproduction extends StoreDecorator {
    public Reproduction(Store store) {
        super(store);
    }

    @Override
    public boolean accept(FoodStuff food) {
        double percentLife = food.evaluatePercentLife();
        return food.recyclable() && percentLife >= 100;
    }
}
