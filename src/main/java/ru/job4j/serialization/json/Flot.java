package ru.job4j.serialization.json;

import com.google.gson.Gson;

public class Flot {
    public static void main(String[] args) {
        final Ship ship = new Ship(true, 8000, "Vezuchyi",
                new Engine("YMZ", 1500), new String[] {"in the sea", "cargo wheat"});

        final Gson gson = new Gson();
        final String shipJson = gson.toJson(ship);
        System.out.println(shipJson);

        final Ship shipFromJson = gson.fromJson(shipJson, Ship.class);
        System.out.println(shipFromJson);
    }
}
