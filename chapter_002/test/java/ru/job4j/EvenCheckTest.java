package ru.job4j;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


/**
 * Проверка байтового потока.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class EvenCheckTest {

    private EvenCheck even = new EvenCheck();

    @Test
    public void whenNumberEvenThenTrue() {
        InputStream is = new ByteArrayInputStream("122".getBytes());
        assertThat(this.even.isNumber(is), is(true));
    }

    @Test
    public void whenNumberOddThenFalse() {
        InputStream is = new ByteArrayInputStream("19".getBytes());
        assertThat(this.even.isNumber(is), is(false));
    }

    @Test()
    public void whenNotNumberThenFalse() {
        InputStream is = new ByteArrayInputStream("Not Number".getBytes());
        assertThat(this.even.isNumber(is), is(false));
    }
}
