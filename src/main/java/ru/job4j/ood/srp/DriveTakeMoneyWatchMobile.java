package ru.job4j.ood.srp;

public class DriveTakeMoneyWatchMobile {

    private static final DriveTakeMoneyWatchMobile SINGL = new DriveTakeMoneyWatchMobile();

    private DriveTakeMoneyWatchMobile() {
    }

    public static DriveTakeMoneyWatchMobile getSingl() {
        return SINGL;
    }

    public void drive() {
        System.out.println("I drive");
    }

    public void takePayment() {
        System.out.println("I take his money");
    }

    public void watchMobile() {
        System.out.println("I watch mobile");
    }
}
