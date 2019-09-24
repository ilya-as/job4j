package ru.job4j.isp;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuItem {

    private Map<String, MenuItem> menuMap;

    private String name;

    private MenuAction action;

    public MenuItem(String name, MenuAction action) {
        this.name = name;
        this.action = action;
        menuMap = new LinkedHashMap<>();
    }

    public void print(String prefix) {
        System.out.println(prefix + this.getName());
        for (MenuItem item : menuMap.values()) {
            item.print(prefix + "-");
        }
    }

    public MenuItem find(String inputString) {
        MenuItem result = null;
        if (this.getName().equals(inputString)) {
            result = this;
            return result;
        }
        for (MenuItem item : menuMap.values()) {
            if (item.getName().equals(inputString)) {
                result = item;
                return result;
            }
            item.find(inputString);
        }
        return result;
    }

    public void doAction() {
        action.accept();
    }

    public void addMenu(MenuItem menuItem) {
        menuMap.put(menuItem.getName(), menuItem);
    }

    public Map<String, MenuItem> getMenuMap() {
        return menuMap;
    }

    public String getName() {
        return name;
    }

}
