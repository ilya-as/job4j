package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.Period;

/**
 * Food
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class Food {

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
    private double disscount;

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
     * @return Возвращает значение поля disscount.
     */
    public double getDisscount() {
        return disscount;
    }

    /**
     * @param price Устанавливает цену на продукт.
     */
    public Food(double price) {
        this.price = price;
    }

    public Food(String name, LocalDate createDate, LocalDate expiredDate) {
        this.name = name;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
    }

    /**
     * @return Возвращает дату создания продукта.
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * @return Возвращает дату окончания срока годности.
     */
    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param disscount Устанавливает скидку на продукт.
     */
    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }

    /**
     * @return расчитывает процент времени от даты выпуска до окончания срока годности.
     */
    public double evaluatePercentLife() {
        LocalDate today = LocalDate.now();
        double totalTime = Period.between(this.getCreateDate(), this.getExpiredDate()).getDays();
        double currentTime = Period.between(this.getCreateDate(), today).getDays();
        return currentTime / totalTime * 100;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", createDate=" + createDate +
                ", expiredDate=" + expiredDate +
                ", price=" + price +
                ", disscount=" + disscount +
                '}';
    }
}
