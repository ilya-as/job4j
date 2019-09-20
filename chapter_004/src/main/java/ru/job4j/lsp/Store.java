package ru.job4j.lsp;


import java.util.ArrayList;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    void addFood(FoodStuff food);

    boolean accept(FoodStuff food);

    ArrayList<FoodStuff> getFoods();

    void clearFoods();

}
