package ru.job4j.ood.ocp.drive;

public class CheckDocumentDriver {
    public String checkDocumentDriver(Driver driver) {
        if (driver.isDrive()) {
            return "Имеет право водить";
        }
        return "";
    }
}
