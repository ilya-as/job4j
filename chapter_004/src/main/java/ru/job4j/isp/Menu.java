package ru.job4j.isp;

import java.util.Map;

public class Menu {
    private MenuItem menuItem;

    public Menu(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void showMenu() {
        Map<String, MenuItem> menuItemMap = menuItem.getMenuMap();
        System.out.println("Menu " + menuItem.getName());
        for (Map.Entry<String, MenuItem> entry : menuItemMap.entrySet()) {
            entry.getValue().print("");
        }
    }

    public void select(String inputString) {
        Map<String, MenuItem> menuItemMap = menuItem.getMenuMap();
        for (Map.Entry<String, MenuItem> entry : menuItemMap.entrySet()) {
            MenuItem temp = entry.getValue().find(inputString);
            if (temp != null) {
                temp.doAction();
                break;
            }
        }
    }
}
