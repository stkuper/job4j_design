package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Stas Kupriyanov";
        byte footSize = 28;
        short shoesSize = 44;
        int age = 36;
        long height = 185L;
        float bonus = 9999.99F;
        double salary = 70500.50;
        char admission = 'A';
        boolean isBonus = true;
        LOG.debug("User info name : {}, age : {}, height : {}, footSize : {}, shoesSize : {},"
                        + " salary : {}, admission : {}, bonus : {}, isBonus : {}",
                name, age, height, footSize, shoesSize, salary, admission, bonus, isBonus);
    }
}
