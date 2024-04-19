package ru.job4j.ood.lsp.carparking.model;

public abstract class Vehicle {
    protected String number;
    protected int size;

//    public Vehicle(String number, int size) {
//        this.number = number;
//        this.size = size;
//    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Vehicle{"
               + "name='" + number + '\''
               + ", size=" + size
               + '}';
    }
}