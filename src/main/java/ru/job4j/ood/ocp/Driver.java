package ru.job4j.ood.ocp;

public class Driver {
    public String checkDocumentDriver(CarDriver driver) {
        if (driver.isDrive()) {
            return "Имеет право водить";
        }
        return "";
    }

    public String checkDocumentDriver(BusDriver driver) {
        if (driver.isDrive()) {
            return "Имеет право водить";
        }
        return "";
    }

    public String checkDocumentDriver(TrackDriver driver) {
        if (driver.isDrive()) {
            return "Имеет право водить";
        }
        return "";
    }
}

class CarDriver {
    public boolean isDrive() {
        return true;
    }
}

class BusDriver {
    public boolean isDrive() {
        return true;
    }
}

class TrackDriver {
    public boolean isDrive() {
        return true;
    }
}
