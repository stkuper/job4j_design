package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whatNumberofVerticesSphereThan0() {
        Box box = new Box(0, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(0);
    }

    @Test
    void whatNumberofVerticesTetrahedronThan4() {
        Box box = new Box(4, 6);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void isExistTetrahedronThanTrue() {
        Box box = new Box(4, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isExistUNKNOWNThanFalse() {
        Box box = new Box(-1, 4);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void getAreaCubeThan864dot0() {
        Box box = new Box(8, 12);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(864.0d, withPrecision(0.001));
    }

    @Test
    void getAreaSphereThan1256dot637() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(1256.637d, withPrecision(0.001));
    }
}