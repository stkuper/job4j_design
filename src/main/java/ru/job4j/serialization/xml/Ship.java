package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "ship")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ship {
    @XmlAttribute
    private boolean commercial;
    @XmlAttribute
    private int cargo;
    @XmlAttribute
    private String nameShip;
    private Engine engine;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Ship() {

    }

    public Ship(boolean commercial, int cargo, String nameShip, Engine engine, String[] statuses) {
        this.commercial = commercial;
        this.cargo = cargo;
        this.nameShip = nameShip;
        this.engine = engine;
        this.statuses = statuses;
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
