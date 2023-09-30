package ru.job4j.serialization.json;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class Flot {
    public static void main(String[] args) {
        final Ship ship = new Ship(true, 8000, "Vezuchyi",
                new Engine("YMZ", 1500), new String[]{"in the sea", "cargo wheat"});

        final Gson gson = new Gson();
        final String shipJson = gson.toJson(ship);
        System.out.println(shipJson);

        final Ship shipFromJson = gson.fromJson(shipJson, Ship.class);
        System.out.println(shipFromJson);

        JSONArray jsonStatuses = new JSONArray(Arrays.asList(ship.getStatuses()));
        JSONObject jsonEngine = new JSONObject(ship.getEngine());
        JSONObject jsonShip = new JSONObject();
        jsonShip.put("commercial", ship.isCommercial());
        jsonShip.put("cargo", ship.getCargo());
        jsonShip.put("nameShip", ship.getNameShip());
        jsonShip.put("engine", jsonEngine);
        jsonShip.put("statuses", jsonStatuses);
        System.out.println(jsonShip);
        System.out.println(new JSONObject(ship));
    }
}
