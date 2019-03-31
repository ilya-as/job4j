package ru.job4j;

import java.io.InputStream;
import java.util.Scanner;

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
        Scanner scanner = null;
        boolean result = false;
        try {
            scanner = new Scanner(is);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                result = value % 2 == 0;
            }
            scanner.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
