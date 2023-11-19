package com.friadenrich.pokemon.model;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class Pokemon implements Serializable {
    public int id;
    public String name;
    // types[0]
    public String type;
    // The weight of this Pokémon in hectograms.
    public float weight;
    // The height of this Pokémon in decimetres. (Qué medidas raras)
    public float height;
    // sprites.front_default
    public String spriteURL;
    public byte[] sprite;

    public Pokemon() {}

    public Pokemon(JSONObject obj) throws JSONException {
        this.id = obj.getInt("id");
        this.name = obj.getString("name");
        JSONObject typeContainerObj = (JSONObject) obj.getJSONArray("types").get(0);
        JSONObject typeObj = typeContainerObj.getJSONObject("type");
        this.type = typeObj.getString("name");
        this.weight = obj.getInt("weight");
        this.height = obj.getInt("height");
        JSONObject spritesContainerObj = obj.getJSONObject("sprites");
        this.spriteURL = spritesContainerObj.getString("front_default");
    }

    public JSONObject pokemonAJson() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("id", this.id);
        obj.put("type", this.type);
        obj.put("weight", this.weight);
        obj.put("height", this.height);
        obj.put("spriteURL", this.spriteURL);
        String encodedSprite = Base64.encodeToString(this.getSprite(), Base64.DEFAULT);
        obj.put("sprite", encodedSprite);
        return obj;
    }

    public static Pokemon jsonAPokemon(JSONObject obj) throws JSONException {
        Pokemon p = new Pokemon();
        p.setName(obj.getString("name"));
        p.setId(obj.getInt("id"));
        p.setType(obj.getString("type"));
        p.setWeight(obj.getInt("weight"));
        p.setHeight(obj.getInt("height"));
        p.setSpriteURL(obj.getString("spriteURL"));

        String encodedSprite = obj.getString("sprite");
        p.setSprite(Base64.decode(encodedSprite, Base64.DEFAULT));
        return p;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(String spriteURL) {
        this.spriteURL = spriteURL;
    }

    public void setSprite(byte[] sprite) {
        this.sprite = sprite;
    }

    public byte[] getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", spriteURL='" + spriteURL +
                ", sprite=" + sprite + '\'' +
                '}';
    }
}
