package ru.job4j.ood.lsp.carparking.parking;

import ru.job4j.ood.lsp.carparking.model.Vehicle;

public class VehiclePark extends AbstractPark {
    private int carSize;
    private int truckSize;
    private int countCar = 0;
    private int countTruck = 0;

    public VehiclePark(int carSize, int truckSize) {
        this.carSize = carSize;
        this.truckSize = truckSize;
    }

    @Override
    public boolean arrive(Vehicle vehicle) {
        if (vehicle.getSize() == 1 && countCar < carSize) {
            countCar++;
            return super.arrive(vehicle);
        } else if (vehicle.getSize() > 1 && countTruck < truckSize) {
            countTruck++;
            return super.arrive(vehicle);
        } else if (vehicle.getSize() > 1 && countCar <= carSize - vehicle.getSize()) {
            countCar += vehicle.getSize();
            return super.arrive(vehicle);
        }
        return false;
    }

    @Override
    public boolean leave(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            countCar--;
            return super.leave(vehicle);
        } else if (vehicle.getSize() > 1) {
            countTruck--;
            return super.leave(vehicle);
        }
        return false;
    }

    public int howFreeCarSpace() {
        return this.carSize - this.countCar;
    }

    public int howFreeTruckSpace() {
        return this.truckSize - this.countTruck;
    }
}
