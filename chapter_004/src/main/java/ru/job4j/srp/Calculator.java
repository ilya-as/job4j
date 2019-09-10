package ru.job4j.srp;

/**
 * Простой калькулятор
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */

public class Calculator {

    /**
     * Хранит результат вычисления.
     */
    private double result;

    /**
     * @param first
     * @param second Производит операцию сложения параметров
     *               first и second.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * @param first
     * @param second Производит операцию вычитания параметра second
     *               из параметра first.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * @param first
     * @param second Производит операцию деления параметра first
     *               на параметр second.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * @param first
     * @param second Производит перемножение параметров.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * @return Возвращает значение переменной result.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * @param result Устанавливает переменную result в поле result
     */
    public void setResult(double result) {
        this.result = result;
    }

}