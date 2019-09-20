package ru.job4j.lsp.food;

import ru.job4j.lsp.FoodStuff;

import java.time.LocalDate;
import java.time.Period;

/**
 * Food
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Food implements FoodStuff {

    /**
     * Наименование продукта
     */
    private String name;

    /**
     * Дата создания продукта.
     */
    private LocalDate createDate;

    /**
     * Дата окончания срока годности
     */
    private LocalDate expiredDate;

    /**
     * Цена
     */
    private double price;

    /**
     * Скидка
     */
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiredDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
        this.price = price;
    }

    /**
     * @return Возвращает значение поля name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает значение поля discount.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param price Устанавливает цену на продукт.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Возвращает дату создания продукта.
     */
    private LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * @return Возвращает дату окончания срока годности.
     */
    private LocalDate getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param discount Устанавливает скидку на продукт.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * @return расчитывает процент времени от даты выпуска до окончания срока годности.
     */
    @Override
    public double evaluatePercentLife() {
        LocalDate today = LocalDate.now();
        double totalTime = Period.between(this.getCreateDate(), this.getExpiredDate()).getDays();
        double currentTime = Period.between(this.getCreateDate(), today).getDays();
        return currentTime / totalTime * 100;
    }

    /**
     * @return булево значение - подлежит или нет продукт переработке.
     */
    @Override
    public boolean recyclable() {
        return false;
    }

    /**
     * @return булево значение - относится продукт к овощам или нет.
     */
    @Override
    public boolean isVegetable() {
        return false;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                ", expiredDate=" + expiredDate +
                ", price=" + price +
                ", disscount=" + discount +
                '}';
    }

}
