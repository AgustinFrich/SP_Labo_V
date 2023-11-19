package com.friadenrich.pokemon.editMVC;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditModel {
    public static int posicion;
    private int id;
    private String name;
    // sprites.front_default
    private byte[] sprite;

    public EditModel(int posicion, Activity ac){
        SharedPreferences sp = ac.getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
        String dexStr = sp.getString("Pokemones", "[]");
        try {
            JSONArray arr = new JSONArray(dexStr);
            JSONObject obj = arr.getJSONObject(posicion);

            this.setId(obj.getInt("id"));
            this.setName(obj.getString("name"));
            String encodedSprite = obj.getString("sprite");
            this.setSprite(Base64.decode(encodedSprite, Base64.DEFAULT));
            EditModel.posicion = posicion;
        } catch (Exception e){

        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getSprite() {
        return sprite;
    }

    public void setSprite(byte[] sprite) {
        this.sprite = sprite;
    }
}
