package com.friadenrich.pokemon.entryMVC;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.friadenrich.pokemon.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntryController implements View.OnClickListener {
    private EntryView vista;
    private Activity ac;
    // private EntryModel modelo;
    Pokemon modelo;

    public  EntryController(EntryView vista, Activity activity, Pokemon modelo) {
        this.vista = vista;
        this.ac = activity;
        this.modelo = modelo;

        this.vista.asignarElementos(modelo);
        this.vista.btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Guarda el pokemon en las shared preferences
        SharedPreferences sp = ac.getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String dexStr = sp.getString("Pokemones", "[]");

        try {
            JSONArray dexObj = new JSONArray(dexStr);

            if(dexObj.length() < 6) {
                JSONObject obj = modelo.pokemonAJson();
                dexObj.put(obj);
                // TODO STRING
                showToast("Pokemon añadido al equipo");
            } else {
                // TODO STRING
                showToast("Ha alcanzado el tamaño máximo de equipo");
            }

            editor.putString("Pokemones", dexObj.toString());
            editor.apply();
            Log.d("ASD", dexObj.length() + "");
        } catch (Exception e){
            Log.d("pok", "error");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this.ac.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
