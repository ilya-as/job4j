package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тестирование класса SimpleLinkedList
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class SimpleLinkedListTest {
    SimpleLinkedList<String> list;

    @Before
    public void setUp() {
        list = new SimpleLinkedList<>();
    }

    @Test
    public void whenAddItemThenGetFirstItem() {
        list.add("1");
        assertThat(list.get(0), is("1"));
    }

    @Test
    public void whenAddThreeItemThenGetLastItem() {
        list.add("1");
        list.add("2");
        list.add("3");
        assertThat(list.get(2), is("3"));
    }

    @Test
    public void whenIterateThenInputValuesEqualsNextValues() {
        list.add("1");
        list.add("2");
        Iterator<String> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("2"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        list.add("1");
        Iterator<String> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        list.add("2");
        it.hasNext();
    }

}
