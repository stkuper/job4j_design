package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> menuItemInfoIterator = menu.iterator();
        while (menuItemInfoIterator.hasNext()) {
            Menu.MenuItemInfo menuItemInfo = menuItemInfoIterator.next();
            System.out.print(getIndent(menuItemInfo.getNumber().length()));
            System.out.printf("%s %s%n", menuItemInfo.getName(), menuItemInfo.getNumber());
        }
    }

    private String getIndent(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length - 2; i += 2) {
            builder.append("----");
        }
        return builder.toString().trim();
    }
}