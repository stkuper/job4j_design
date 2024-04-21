package ru.job4j.ood.isp.without;

public class SmartPhone implements Phone {
    @Override
    public void call() {
        System.out.println("Call");
    }

    @Override
    public void message() {
        System.out.println("Message");
    }

    @Override
    public void watch() {
        System.out.println("Watch movie");
    }

    @Override
    public void game() {
        System.out.println("Game");
    }

    @Override
    public void web() {
        System.out.println("Surfing the Internet");
    }

    @Override
    public void camera() {
        System.out.println("Photo");
    }
}
