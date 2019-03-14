package ru.job4j.statistic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ilya Aslamov
 * @version $Id$
 * @since 0.1
 */
public class AnalizeTest {

    /**
     * Проверим удаление изменение и добавление списка.
     */
    @Test
    public void diff() {

        List<Analize.User> oldList = new ArrayList<>();
        List<Analize.User> curList = new ArrayList<>();

        Analize.User kolya = new Analize.User(12, "Коля");

        oldList.add(new Analize.User(1, "Иван"));
        oldList.add(new Analize.User(2, "Владимир"));
        oldList.add(new Analize.User(3, "Олег"));
        oldList.add(new Analize.User(4, "Маша"));
        oldList.add(new Analize.User(12, "Коля"));
        oldList.add(new Analize.User(5, "Таня"));


        curList.add(new Analize.User(12, "Николай")); // изменили
        curList.add(new Analize.User(1, "Иван"));
        //curList.add(new Analize.User(2, "Владимир")); - удалили
        curList.add(new Analize.User(3, "Олег"));
        //curList.add(new Analize.User(4, "Маша")); - удалили
        curList.add(new Analize.User(5, "Таня"));
        curList.add(new Analize.User(6, "Женя")); // добавили

        Analize Analize = new Analize();

        assertThat(Analize.diff(oldList, curList).toString(), is("Info{changed=1, added=1, deleted=2}"));

    }
}
