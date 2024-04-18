package ru.job4j.ood.lsp.carparking.parking;

import ru.job4j.ood.lsp.carparking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AbstractPark implements Park {
    private List<Vehicle> vehicleList = new ArrayList<>();
    private int carSize;
    private int truckSize;

    public AbstractPark(int carSize, int truckSize) {
        this.carSize = carSize;
        this.truckSize = truckSize;
    }

    @Override
    public boolean arrive(Vehicle vehicle) {
        return false;
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
