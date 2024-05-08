package ru.job4j.ood.dip.without;

import java.util.Objects;

public class Driver {
    private int id;
    private String fullName;
    private int age;

    public Driver(int id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return id == driver.id && age == driver.age && Objects.equals(fullName, driver.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, age);
    }

    @Override
    public String toString() {
        return "Driver{"
               + "id=" + id
               + ", fullName='" + fullName + '\''
               + ", age=" + age
               + '}';
    }
}