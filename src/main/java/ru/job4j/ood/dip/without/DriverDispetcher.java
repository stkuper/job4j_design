package ru.job4j.ood.dip.without;

public class DriverDispetcher {
    SimpleCheck simpleCheck = new SimpleCheck();

    public boolean wentToWork(Driver driver) {
        return simpleCheck.check(driver);
    }
}