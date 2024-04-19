package ru.job4j.ood.lsp.carparking.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.carparking.model.Car;
import ru.job4j.ood.lsp.carparking.model.Truck;
import ru.job4j.ood.lsp.carparking.model.Vehicle;

import static org.assertj.core.api.Assertions.*;

class VehicleParkTest {
    @Test
    void whenAdd1CarWithFreePlaceThenTrue() {
        Vehicle car = new Car("m123zx");
        VehiclePark park = new VehiclePark(3, 2);
        assertThat(park.arrive(car)).isTrue();
        assertThat(park.findAll().size()).isEqualTo(1);
        assertThat(park.howFreeCarSpace()).isEqualTo(2);
        assertThat(park.howFreeTruckSpace()).isEqualTo(2);
    }

    @Test
    void whenAdd1CarWithoutFreePlaceThenFalse() {
        Vehicle car = new Car("m123zx");
        VehiclePark park = new VehiclePark(0, 2);
        assertThat(park.arrive(car)).isFalse();
        assertThat(park.findAll().size()).isEqualTo(0);
        assertThat(park.howFreeCarSpace()).isEqualTo(0);
        assertThat(park.howFreeTruckSpace()).isEqualTo(2);
    }

    @Test
    void whenAdd1TruckWithFreePlaceThenTrue() {
        Vehicle truck = new Truck("m123zx", 2);
        VehiclePark park = new VehiclePark(5, 2);
        assertThat(park.arrive(truck)).isTrue();
        assertThat(park.findAll().size()).isEqualTo(1);
        assertThat(park.howFreeCarSpace()).isEqualTo(5);
        assertThat(park.howFreeTruckSpace()).isEqualTo(1);
    }

    @Test
    void whenAdd1TruckWithoutFreePlaceThenFalse() {
        Vehicle truck = new Truck("m123zx", 2);
        VehiclePark park = new VehiclePark(1, 0);
        assertThat(park.arrive(truck)).isFalse();
        assertThat(park.findAll().size()).isEqualTo(0);
        assertThat(park.howFreeCarSpace()).isEqualTo(1);
        assertThat(park.howFreeTruckSpace()).isEqualTo(0);
    }

    @Test
    void whenAdd1TruckWithoutFreeTruckPlaceButWithFreeCarPlaceThenTrue() {
        Vehicle truck = new Truck("m123zx", 2);
        VehiclePark park = new VehiclePark(5, 0);
        assertThat(park.arrive(truck)).isTrue();
        assertThat(park.findAll().size()).isEqualTo(1);
        assertThat(park.howFreeCarSpace()).isEqualTo(3);
        assertThat(park.howFreeTruckSpace()).isEqualTo(0);
    }

    @Test
    void whenLeave1TruckThenTrue() {
        Vehicle truck = new Truck("m123zx", 2);
        VehiclePark park = new VehiclePark(3, 3);
        assertThat(park.arrive(truck)).isTrue();
        assertThat(park.leave(truck)).isTrue();
        assertThat(park.findAll().size()).isEqualTo(0);
        assertThat(park.howFreeCarSpace()).isEqualTo(3);
        assertThat(park.howFreeTruckSpace()).isEqualTo(3);
    }

    @Test
    void whenLeave1CarThenTrue() {
        Vehicle car = new Car("m123zx");
        VehiclePark park = new VehiclePark(1, 1);
        assertThat(park.arrive(car)).isTrue();
        assertThat(park.leave(car)).isTrue();
        assertThat(park.findAll().size()).isEqualTo(0);
        assertThat(park.howFreeCarSpace()).isEqualTo(1);
        assertThat(park.howFreeTruckSpace()).isEqualTo(1);
    }
}