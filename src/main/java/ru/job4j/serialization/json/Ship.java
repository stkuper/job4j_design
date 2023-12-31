package ru.job4j.serialization.json;

import java.util.Arrays;

public class Ship {
    private final boolean commercial;
    private final int cargo;
    private final String nameShip;
    private final Engine engine;
    private final String[] statuses;

    public Ship(boolean commercial, int cargo, String nameShip, Engine engine, String[] statuses) {
        this.commercial = commercial;
        this.cargo = cargo;
        this.nameShip = nameShip;
        this.engine = engine;
        this.statuses = statuses;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public int getCargo() {
        return cargo;
    }

    public String getNameShip() {
        return nameShip;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Ship{"
                + "commercial=" + commercial
                + ", cargo=" + cargo
                + ", nameShip='" + nameShip + '\''
                + ", engine=" + engine
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
