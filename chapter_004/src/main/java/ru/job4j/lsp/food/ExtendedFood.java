package ru.job4j.lsp.food;

import ru.job4j.lsp.FoodStuff;

/**
 * Расширение для класса Food
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class ExtendedFood extends FoodDecorator {
    private boolean recyclable;
    private boolean isVegetable;

    public ExtendedFood(FoodStuff food, boolean recyclable, boolean isVegetable) {
        super(food);
        this.recyclable = recyclable;
        this.isVegetable = isVegetable;
    }

    @Override
    public boolean recyclable() {
        return this.recyclable;
    }

    @Override
    public boolean isVegetable() {
        return this.isVegetable;
    }
}
