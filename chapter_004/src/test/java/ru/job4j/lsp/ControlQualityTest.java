package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.food.ExtendedFood;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.*;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * ControlQualityTest
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityTest {

    @Test
    public void whenFoodMoveToWarehouse() {
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Store shop = new Shop();

        FoodStuff milk = new Food(
                "Milk to warehouse",
                LocalDate.of(2019, Month.SEPTEMBER, 19),
                LocalDate.of(2019, Month.SEPTEMBER, 30),
                50
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(warehouse);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.selectPlace(milk);

        assertThat(warehouse.getFoods().get(0).getName(), is("Milk to warehouse"));
    }

    @Test
    public void whenFoodMoveToShop() {
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Store shop = new Shop();

        Food milk = new Food(
                "Milk to shop",
                LocalDate.of(2019, Month.SEPTEMBER, 1),
                LocalDate.of(2019, Month.SEPTEMBER, 22),
                50
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(warehouse);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.selectPlace(milk);

        assertThat(shop.getFoods().get(0).getName(), is("Milk to shop"));
    }

    @Test
    public void whenFoodMoveToExtraWarehouse() {
        Store warehouse = new SetSizeWarehouse(new Warehouse(), 2);
        Store warehouseExtra = new SetSizeWarehouse(new Warehouse(), 10);
        Store trash = new Trash();
        Store shop = new Shop();

        Food milk = new Food(
                "Milk to extra warehouse",
                LocalDate.of(2019, Month.SEPTEMBER, 19),
                LocalDate.of(2019, Month.SEPTEMBER, 30),
                50
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(warehouse);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.addTempStore(warehouseExtra);
        controlQuality.selectPlace(milk);
        controlQuality.selectPlace(milk);
        controlQuality.selectPlace(milk);
        controlQuality.selectPlace(milk);

        assertThat(warehouseExtra.getFoods().get(0).getName(), is("Milk to extra warehouse"));
    }

    @Test
    public void whenVegetableFoodMoveToFridgeWarehouse() {
        Store fridgeWarehouse = new FridgeWarehouse(new Warehouse());
        Store trash = new Trash();
        Store shop = new Shop();

        FoodStuff vegetable = new ExtendedFood(new Food(
                "Vegetable to fridge warehouse",
                LocalDate.of(2019, Month.SEPTEMBER, 19),
                LocalDate.of(2019, Month.SEPTEMBER, 30),
                50), true, true
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(fridgeWarehouse);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.selectPlace(vegetable);

        assertThat(fridgeWarehouse.getFoods().get(0).getName(), is("Vegetable to fridge warehouse"));
    }

    @Test
    public void whenRecyclableFoodMoveToReproduction() {
        Store reproduction = new Reproduction(new Warehouse());
        Store trash = new Trash();
        Store shop = new Shop();

        FoodStuff recyclable = new ExtendedFood(new Food(
                "Recyclable to reproduction",
                LocalDate.of(2019, Month.AUGUST, 1),
                LocalDate.of(2019, Month.SEPTEMBER, 1),
                50), true, true
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(reproduction);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.selectPlace(recyclable);

        assertThat(reproduction.getFoods().get(0).getName(), is("Recyclable to reproduction"));
    }
}
