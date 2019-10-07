package ru.job4j.tdd;

import java.util.*;
import java.util.regex.*;
import java.lang.*;

/**
 * Класс-генератор переданной строки по шаблону.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleGenerator {

    /**
     * Шаблон для преобразования строки.
     */
    private final Pattern KEYS = Pattern.compile("\\$.(\\p{L}+)\\}");

    /**
     * Преобразует строку.
     *
     * @param inputString - исходное сообщение.
     * @param mapOptions  - список ключей и значений для замены.
     * @return - Взвращает преобразованную строку.
     * @throws Exception - могут возникать исключения при не соответсвии списка ключей требуемым.
     */
    public String generate(String inputString, HashMap<String, String> mapOptions) throws Exception {
        Matcher matcher = KEYS.matcher(inputString);
        StringBuffer result = new StringBuffer();
        int count = 0;
        while (matcher.find()) {
            if (!mapOptions.containsKey(matcher.group(1))) {
                throw new KeyNotFoundException("Ключ: " + matcher.group(1) + " не найден!");
            }
            matcher.appendReplacement(result, mapOptions.get(matcher.group(1)));
            count++;
        }
        if ((mapOptions.size()) != count) {
            throw new InvalidKeyException("Количество переданных ключей не совпадает с количесвом ключей в предложении!");
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
