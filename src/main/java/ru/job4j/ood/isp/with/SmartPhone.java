package ru.job4j.ood.isp.with;

public class SmartPhone implements Call, Message, Watch, Game, Web, Camera {
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
