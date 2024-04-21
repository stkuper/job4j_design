package ru.job4j.ood.isp.without;

public class LandLinePhone implements Phone {
    @Override
    public void call() {
        System.out.println("Call");
    }

    @Override
    public void message() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void watch() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void game() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void web() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }

    @Override
    public void camera() {
        throw new UnsupportedOperationException("Opperation unsupport");
    }
}
