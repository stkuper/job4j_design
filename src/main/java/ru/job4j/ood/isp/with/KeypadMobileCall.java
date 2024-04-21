package ru.job4j.ood.isp.with;

public class KeypadMobileCall implements Call, Message, Game, Camera {
    @Override
    public void call() {
        System.out.println("Call");
    }

    @Override
    public void message() {
        System.out.println("Message");
    }

    @Override
    public void game() {
        System.out.println("Game");
    }

    @Override
    public void camera() {
        System.out.println("Photo");
    }
}
