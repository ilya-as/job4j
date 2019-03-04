package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * Test SimpleHashSet.
 *
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */

public class SimpleHashMapTest {
    SimpleHashMap<Integer, String> shm;

    @Before
    public void setUp() {
        shm = new SimpleHashMap<>();
    }

    @Test
    public void whenAddPairThenContainsThatPairReturnTrue() {
        shm.insert(1, "one");
        assertThat(shm.get(1), is("one"));
    }

    @Test
    public void whenRemovePairThenGetReturnNull() {
        shm.insert(1, "one");
        shm.delete(1);
        assertNull(shm.get(1));
    }

    @Test
    public void whenIterate() {
        shm.insert(1, "one");
        shm.insert(2, "two");
        Iterator<SimpleHashMap.Node<Integer, String>> it = shm.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("one"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().getValue(), is("two"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void ifAddToMapMoreElementsThenCapacityWasIncrease() {
        SimpleHashMap<Integer, Integer> testIntegerMap = new SimpleHashMap<>(10);
        for (int i = 0; i < 100; i++) {
            testIntegerMap.insert(i, i);
        }
        assertThat(testIntegerMap.getSize() > 10, is(true));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void ifModificationMapAfterIteratorCreateThenThrowException() {
        Iterator<SimpleHashMap.Node<Integer, String>> it = shm.iterator();
        shm.insert(1, "Student");
        it.next();
    }
}
