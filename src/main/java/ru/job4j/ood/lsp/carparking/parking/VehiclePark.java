package ru.job4j.ood.lsp.carparking.parking;

import ru.job4j.ood.lsp.carparking.model.Vehicle;

public class VehiclePark extends AbstractPark {
    /**
     * В поле carSize указывается сколько всего парковочных мест для легковых машин
     */
    private int carSize;
    /**
     * В поле truckSize указывается сколько всего парковочных мест для грузовых машин
     */
    private int truckSize;
    /**
     * В поле countCar указывается сколько всего легковых машин в данный момент на парковке
     */
    private int countCar = 0;
    /**
     * В поле countTruck указывается сколько всего грузовых машин в данный момент на парковке
     */
    private int countTruck = 0;

    public VehiclePark(int carSize, int truckSize) {
        this.carSize = carSize;
        this.truckSize = truckSize;
    }

    /**
     * В методе arrive() происходит попытка выделения парковочного места для прибывшей машины
     * @param vehicle прибывшая машина, которой нужно выделить парковочное место
     * @return true если парковочное место успешно выделено,
     *         false если попытка выделения парковочного места закончилась неудачей
     */
    @Override
    public boolean arrive(Vehicle vehicle) {
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == 1 && countCar < carSize) {
            countCar++;
            return super.arrive(vehicle);
        } else if (vehicleSize > 1 && countTruck < truckSize) {
            countTruck++;
            return super.arrive(vehicle);
        } else if (vehicleSize > 1 && countCar <= carSize - vehicleSize) {
            countCar += vehicleSize;
            return super.arrive(vehicle);
        }
        return false;
    }

    /**
     * В методе leave() происходит попытка отъезда машины с парковки и освобождение
     * занятого ею парковочного места
     * @param vehicle машина, которая собирается уезжать с парковки
     * @return true если машине удалось уехать с парковки и освободить парковочное место,
     *         false если попытка отъезда машины с парковки закончилось неудачей
     */
    @Override
    public boolean leave(Vehicle vehicle) {
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == 1) {
            countCar--;
            return super.leave(vehicle);
        } else if (vehicleSize > 1 && howFreeTruckSpace() == 0) {
            countCar -= vehicleSize;
            return super.leave(vehicle);
        } else if (vehicleSize > 1) {
            countTruck--;
            return super.leave(vehicle);
        }
        return false;
    }

    /**
     * В методе howFreeCarSpace() происходит вычисление количества
     * свободных мест для легковых машин
     * @return возвращает количество свободных мест для легковых машин
     */
    public int howFreeCarSpace() {
        return this.carSize - this.countCar;
    }

    /**
     * В методе howFreeTruckSpace() происходит вычисление количества
     * свободных мест для грузовых машин
     * @return возвращает количество свободных мест для грузовых машин
     */
    public int howFreeTruckSpace() {
        return this.truckSize - this.countTruck;
    }
}