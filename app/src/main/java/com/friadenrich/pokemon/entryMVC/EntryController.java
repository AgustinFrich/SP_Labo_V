package com.friadenrich.pokemon.entryMVC;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.helper.SPHelper;
import com.friadenrich.pokemon.model.Pokemon;

public class EntryController implements View.OnClickListener {
    private EntryView vista;
    private Activity ac;
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
        if(SPHelper.aniadirPokemon(modelo)){
                showToast(ac.getString(R.string.aniadido));
        } else {
                showToast(ac.getString(R.string.max_limit));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this.ac.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
