package ru.job4j.lsp;

import org.junit.Test;

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

        Food milk = new Food(
                "Milk to warehouse",
                LocalDate.of(2019, Month.SEPTEMBER, 15),
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
    public void whenFoodMoveToTrash() {
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Store shop = new Shop();

        Food milk = new Food(
                "Milk to trash",
                LocalDate.of(2019, Month.AUGUST, 1),
                LocalDate.of(2019, Month.SEPTEMBER, 1),
                50
        );

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addTempStore(warehouse);
        controlQuality.addTempStore(shop);
        controlQuality.addTempStore(trash);
        controlQuality.selectPlace(milk);

        assertThat(trash.getFoods().get(0).getName(), is("Milk to trash"));
    }
}
