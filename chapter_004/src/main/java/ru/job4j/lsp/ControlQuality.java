package ru.job4j.lsp;

import java.util.ArrayList;

/**
 * Класс обработчик перераспределения продуктов в место использования
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class ControlQuality {

    /**
     * Общее хранилище классов,имплементирующих интерфейс Store.
     */
    private ArrayList<Store> storeArray;


    public ControlQuality() {
        this.storeArray = new ArrayList<>();
    }

    /**
     * @param store Добавляет переданный параметр store в хранилище storeArray
     */
    public void addTempStore(Store store) {
        this.storeArray.add(store);
    }

    /**
     * Распределяет продукты из хранилища storeArray.
     */
    public void selectPlace(FoodStuff food) {
        for (Store store : this.storeArray) {
            if (store.accept(food)) {
                store.addFood(food);
                break;
            }
        }
    }

    /**
     * Извлекает все продукты и перераспределяет их заново
     */
    public void resort() {
        ArrayList<FoodStuff> tempArray = new ArrayList<>();
        for (Store store : this.storeArray) {
            tempArray.addAll(store.getFoods());
            store.clearFoods();
        }

        for (FoodStuff food : tempArray) {
            selectPlace(food);
        }
    }
}
