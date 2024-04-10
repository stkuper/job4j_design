package ru.job4j.ood.lsp.warehouseproducts.food;

import java.time.LocalDate;

public class Egg extends Food {
    public Egg(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        super(name, expiryDate, createDate, price);
    }
}
