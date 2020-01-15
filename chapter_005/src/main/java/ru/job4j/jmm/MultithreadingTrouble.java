package ru.job4j.jmm;

/**
 * Решение задачи 1. Проиллюстрировать проблемы с многопоточностью.
 * В некоторых случаях программа будет выводить и нечётные переменные,
 * хотя перед выводом в консоль стоит проверка на чётность. Это происходит
 * из-за несинхронизированного обращения к общей переменной sharedValue
 */

public class MultithreadingTrouble {
    static int sharedValue;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (sharedValue % 2 == 0) {
                    System.out.println("sharedValue = " + sharedValue);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) sharedValue++;
        });
        thread1.start();
        thread2.start();
    }
}
