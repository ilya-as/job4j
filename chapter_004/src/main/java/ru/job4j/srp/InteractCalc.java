package ru.job4j.srp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * Меню пользователя для класса Calculator.
 *
 * @author Ilya Aslamov
 * @since 1.0
 */

public class InteractCalc {

    /**
     * объект Calculator.
     */
    private Calculator calculator;

    /**
     * Результат вычисления.
     */
    private double result;

    /**
     * Поток ввода.
     */
    private BufferedReader reader;

    /**
     * Хранит список: строка меню - действие.
     */
    private final Map<String, CheckedInput<BufferedReader>> actions;

    /**
     * Конструктор.
     * При создании объекта InteractCalc заполняется список меню actions и создается объект Calculator.
     */
    public InteractCalc() {
        this.actions = new HashMap<>();
        this.calculator = new Calculator();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        fillActions();
    }

    /**
     * @return Возвращает результат вычисления.
     */
    private double getResult() {
        return result;
    }

    /**
     * @param result Устанавливает переменную result в поле result объекта Calculator
     */
    public void setCalcResult(double result) {
        this.calculator.setResult(result);
    }

    /**
     * Заполняет список: строка меню - действие.
     */
    public void fillActions() {
        this.actions.put("+", (BufferedReader reader) -> calculator.add(inputValue(reader), inputValue(reader)));
        this.actions.put("-", (BufferedReader reader) -> calculator.subtract(inputValue(reader), inputValue(reader)));
        this.actions.put("*", (BufferedReader reader) -> calculator.multiple(inputValue(reader), inputValue(reader)));
        this.actions.put("/", (BufferedReader reader) -> calculator.div(inputValue(reader), inputValue(reader)));
    }

    /**
     * @return Возвращает структуру HashMap строка меню - действие.
     */
    public Map<String, CheckedInput<BufferedReader>> getActions() {
        return actions;
    }

    /**
     * Ввод числа.
     *
     * @param reader - поток ввода.
     * @return - возвращает число введенное пользователем.
     */
    public Double inputValue(BufferedReader reader) throws IOException {

        Double inputResult = 0.0;
        boolean inputCorrect;
        do {
            inputCorrect = true;
            System.out.print("Введите m - для использования прошлого результата вычисления или новое число: ");
            String inputString = reader.readLine();
            if ("m".equals(inputString))
                inputResult = getResult();
            else
                try {
                    inputResult = Double.parseDouble(inputString);
                } catch (Exception e) {
                    inputCorrect = false;
                    System.out.println("Введенные данные некорректны!");
                }
        } while (!inputCorrect);

        return inputResult;
    }

    /**
     * Выбираем пункт меню.
     *
     * @param reader - поток ввода.
     * @return - возвращает выбранный пункт меню.
     */
    public String inputMenuItem(BufferedReader reader) throws IOException {
        String result;
        boolean inputCorrect;
        do {
            inputCorrect = true;
            System.out.println("Список операций: ");
            for (String action : this.actions.keySet()) {
                System.out.print(action + " ");
            }
            System.out.print("Выберите операцию:");
            result = reader.readLine();
            if (!actions.containsKey(result)) {
                inputCorrect = false;
                System.out.println("Введенные данные некорректны!");
            }
        } while (!inputCorrect);
        return result;
    }

    /**
     * Производит действие по выбранному пункту меню.
     *
     * @param action - выбранный пункт меню.
     * @param reader - поток ввода.
     */
    public void doAction(String action, BufferedReader reader) throws IOException {
        this.actions.get(action).apply(reader);
        this.result = this.calculator.getResult();
    }

    /**
     * Спрашиваем у пользоватеяля выходить из программы или нет.
     *
     * @return true - выход из программы, false - программа работает дальше.
     */
    public boolean askForExit(BufferedReader reader) throws IOException {
        boolean result = false;
        System.out.println("Для выхода нажмите 'y' .Для продолжения - любую клавишу!");
        String inputString = reader.readLine();
        if ("y".equals(inputString)) {
            result = true;
        }
        return result;
    }

    /**
     * Метод для запуска калькулятора.
     */
    public void runCalc() {
        boolean checkExitKey = false;
        do {
            try {
                String action = this.inputMenuItem(this.reader);
                this.doAction(action, this.reader);
                System.out.println("Result: " + this.getResult());
                checkExitKey = this.askForExit(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!checkExitKey);

    }

    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc();
        interactCalc.runCalc();
    }

}