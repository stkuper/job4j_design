package ru.job4j.ood.isp.without;

public class KeypadMobilePhone implements Phone {
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
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void game() {
        System.out.println("Game");
    }

    @Override
    public void web() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void camera() {
        System.out.println("Photo");
    }
}
