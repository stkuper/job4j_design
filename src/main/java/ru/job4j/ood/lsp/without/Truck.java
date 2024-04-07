package ru.job4j.ood.lsp.without;

public class Truck extends Vehicle {
    @Override
    public void passengerTransportation() {
        throw new RuntimeException("Пассажиров не перевозим");
    }
}
