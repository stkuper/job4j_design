package ru.job4j.ood.dip.with;

public class DriverDispetcher {
    Checkator checkator;

    public DriverDispetcher(Checkator checkator) {
        this.checkator = checkator;
    }

    public boolean wentToWork(Driver driver) {
        return  checkator.check(driver);
    }
}