package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuTest {
    private Menu menu;

    @Before
    public void initMenu() {
        MenuItem menuItem = new MenuItem("tasks", () -> System.out.print(""));
        MenuItem task1 = new MenuItem("task1", () -> System.out.print("action of task1"));
        MenuItem task2 = new MenuItem("task2", () -> System.out.print("action of task2"));
        MenuItem task21 = new MenuItem("task2-1", () -> System.out.print("action of task2-1"));
        MenuItem task22 = new MenuItem("task2-2", () -> System.out.print("action of task2-2"));
        MenuItem task3 = new MenuItem("task3", () -> System.out.print("action of task3"));

        task2.addMenu(task21);
        task2.addMenu(task22);

        menuItem.addMenu(task1);
        menuItem.addMenu(task2);
        menuItem.addMenu(task3);

        this.menu = new Menu(menuItem);
    }

    @Test
    public void whenShowMenu() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        menu.showMenu();

        StringBuilder expected = new StringBuilder();
        expected.append("Menu tasks").append(System.lineSeparator())
                .append("task1").append(System.lineSeparator())
                .append("task2").append(System.lineSeparator())
                .append("-task2-1").append(System.lineSeparator())
                .append("-task2-2").append(System.lineSeparator())
                .append("task3").append(System.lineSeparator());
        assertThat(outputStream.toString(), is(expected.toString()));
    }

    @Test
    public void whenSelectAction() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        menu.select("task2-2");

        String expected = "action of task2-2";

        assertThat(outputStream.toString(), is(expected));
    }
}
