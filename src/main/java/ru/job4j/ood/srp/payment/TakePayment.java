package ru.job4j.ood.srp.payment;

public class TakePayment implements Payment {
    @Override
    public void takePayment() {
        System.out.println("I take his money");
    }
}
