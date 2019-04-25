package ru.job4j.io;

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
        InputStream is = new ByteArrayInputStream("123456789123456789123456789123456788".getBytes());
        assertThat(this.even.isNumber(is), is(true));
    }

    @Test
    public void whenNumberOddThenFalse() {
        InputStream is = new ByteArrayInputStream("123456789123456789123456789123456789".getBytes());
        assertThat(this.even.isNumber(is), is(false));
    }

    @Test()
    public void whenNotNumberThenFalse() {
        InputStream is = new ByteArrayInputStream("123456789мама_мыла_раму89123456788".getBytes());
        assertThat(this.even.isNumber(is), is(false));
    }
}
