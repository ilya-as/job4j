package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */

public class UserTest {

    /**
     * Test to add map .
     */
    @Test
    public void notOverrideEqualsHashcode() {
        Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY, 25);
        User user1 = new User("Petr", 1, calendar);
        User user2 = new User("Petr", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "user1");
        map.put(user2, "user2");
        System.out.println("map: " + map);
        System.out.println("разный hashcode у объектов user1 и  user2");
        System.out.println("hashcode user1: " + map.get(user1).hashCode());
        System.out.println("hashcode user2: " + map.get(user2).hashCode());
        System.out.println("сравнение объектов через equals: " + user1.equals(user2));
    }
}
