package ru.job4j.lsp.food;

import ru.job4j.lsp.FoodStuff;

/**
 * Абстрактный класс - декоратор для класса Food
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public abstract class FoodDecorator implements FoodStuff {

    private FoodStuff food;

    public FoodDecorator(FoodStuff food) {
        this.food = food;
    }

    @Override
    public double evaluatePercentLife() {
        return this.food.evaluatePercentLife();
    }

    @Override
    public String getName() {
        return this.food.getName();
    }

    @Override
    public void setDiscount(double discount) {
        this.food.setDiscount(discount);
    }

}
