package ru.job4j.serialization.json;

public class Engine {
    private final String name;
    private final int horsepower;

    public Engine(String name, int horsepower) {
        this.name = name;
        this.horsepower = horsepower;
    }

    public String getName() {
        return name;
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "name='" + name + '\''
                + ", horsepower=" + horsepower
                + '}';
    }
}
