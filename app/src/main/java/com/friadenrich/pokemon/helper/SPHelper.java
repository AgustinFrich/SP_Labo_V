package com.friadenrich.pokemon.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.friadenrich.pokemon.model.Pokemon;

import org.json.JSONArray;

import java.util.List;

public class SPHelper {
    public static SharedPreferences sp;

    public SPHelper(Activity ac){
        sp = ac.getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
    }

    public void removerPokemon(List<Pokemon> pokemones, int index){
        try {
            JSONArray arr = new JSONArray();
            for(Pokemon p: pokemones) {
                arr.put(p.pokemonAJson());
            }
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("Pokemones", arr.toString());
            editor.apply();
        }
        catch (Exception e){

        }
    }
}
