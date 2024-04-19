package ru.job4j.ood.lsp.carparking.model;

public class Car extends Vehicle {
    private static final int CAR_SIZE = 1;

    public Car(String number) {
        this.number = number;
        this.size = CAR_SIZE;
    }
}
