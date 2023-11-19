package com.friadenrich.pokemon.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    String name;
    String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<Pokedex> fromJsonArray(JSONArray arr) throws JSONException {
        List<Pokedex> pokedex = new ArrayList<Pokedex>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject jsonObject = arr.getJSONObject(i);

            String nombre = jsonObject.getString("name");
            String url = jsonObject.getString("url");

            Pokedex p = new Pokedex();
            p.setName(nombre);
            p.setUrl(url);

            pokedex.add(p);
        }
        return pokedex;
    }
}
