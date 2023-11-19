package com.friadenrich.pokemon.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.friadenrich.pokemon.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SPHelper {
    public static SharedPreferences sp;

    public SPHelper(Activity ac) {
        sp = ac.getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
    }

    public static void actualizarListaGuardada(List<Pokemon> pokemones) {
        try {
            JSONArray arr = new JSONArray();
            for (Pokemon p : pokemones) {
                arr.put(p.pokemonAJson());
            }
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("Pokemones", arr.toString());
            editor.apply();
        } catch (Exception e) {

        }
    }

    public static List<Pokemon> getPokemones() {
        String dexStr = sp.getString("Pokemones", "[]");
        List<Pokemon> pokemones = new ArrayList<Pokemon>();

        try {
            JSONArray dexObj = new JSONArray(dexStr);
            int size = dexObj.length();
            for (int i = 0; i < size; i++) {
                Pokemon p = Pokemon.jsonAPokemon(dexObj.getJSONObject(i));
                pokemones.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemones;
    }

    public static JSONArray getJsonArrayPokemones() throws JSONException {
        String dexStr = sp.getString("Pokemones", "[]");
        return new JSONArray(dexStr);
    }

    public  static Pokemon getUnPokemon(int index) throws JSONException {
        JSONArray dexObj = getJsonArrayPokemones();
        JSONObject pkmnObj = dexObj.getJSONObject(index);

        return Pokemon.jsonAPokemon(pkmnObj);
    }

    public  static boolean aniadirPokemon(Pokemon modelo) {
        SharedPreferences.Editor editor = sp.edit();
        String dexStr = sp.getString("Pokemones", "[]");

        boolean retorno = false;

        try {
            JSONArray dexObj = new JSONArray(dexStr);

            if(dexObj.length() < 6) {
                JSONObject obj = modelo.pokemonAJson();
                dexObj.put(obj);
                retorno = true;
            }

            editor.putString("Pokemones", dexObj.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return retorno;
    }
}