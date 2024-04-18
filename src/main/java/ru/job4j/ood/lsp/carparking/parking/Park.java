package ru.job4j.ood.lsp.carparking.parking;

import ru.job4j.ood.lsp.carparking.model.Vehicle;

import java.util.List;

public interface Park {
    boolean arrive(Vehicle vehicle);

    boolean leave(Vehicle vehicle);

    List<Vehicle> findAll();
}
