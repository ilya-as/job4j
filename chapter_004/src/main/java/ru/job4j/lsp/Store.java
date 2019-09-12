package ru.job4j.lsp;


import java.util.ArrayList;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    void addFood(Food food);

    boolean accept(Food food);

    ArrayList<Food> getFoods();

    void clearFoods();

}
