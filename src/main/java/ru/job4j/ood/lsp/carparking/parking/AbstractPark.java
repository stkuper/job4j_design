package ru.job4j.ood.lsp.carparking.parking;

import ru.job4j.ood.lsp.carparking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AbstractPark implements Park {
    private List<Vehicle> vehicleList = new ArrayList<>();

    @Override
    public boolean arrive(Vehicle vehicle) {
        return vehicleList.add(vehicle);
    }

    @Override
    public boolean leave(Vehicle vehicle) {
        return vehicleList.remove(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleList;
    }
}
