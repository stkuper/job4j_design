package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int horsepower;

    public Engine() {

    }

    public Engine(String name, int horsepower) {
        this.name = name;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "name='" + name + '\''
                + ", horsepower=" + horsepower
                + '}';
    }
}
