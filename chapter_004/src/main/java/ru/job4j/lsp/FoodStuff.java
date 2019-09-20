package ru.job4j.lsp;

public interface FoodStuff {

    boolean recyclable();

    boolean isVegetable();

    double evaluatePercentLife();

    String getName();

    void setDiscount(double discount);
}
