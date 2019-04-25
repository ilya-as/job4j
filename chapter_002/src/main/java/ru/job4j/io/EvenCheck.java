package ru.job4j.io;

import java.io.*;

/**
 * Проверка байтового потока.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class EvenCheck {
    /**
     * Метод проверяет,что в байтовом потоке записано четное число.
     *
     * @param is входящий поток InputStream.
     * @return true - если число четное, false-нечетное.
     */
    public boolean isNumber(InputStream is) {
        boolean result = false;
        try {
            String string = new String(is.readAllBytes());
            for (int i = 0; i < string.length(); i++) {
                if (!(Character.isDigit(string.charAt(i)))) {
                    return false;
                }
            }
            char lastCharacter = string.charAt(string.length() - 1);
            result = lastCharacter % 2 == 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
