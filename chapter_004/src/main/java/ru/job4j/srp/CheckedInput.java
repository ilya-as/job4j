package ru.job4j.srp;

import java.io.IOException;

/**
 * Функциональный интерфейс для ввода данных с обработкой ошибок.
 */
@FunctionalInterface
public interface CheckedInput<BufferedReader> {
    void apply(BufferedReader reader) throws IOException;
}
