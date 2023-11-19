package com.friadenrich.pokemon.editMVC;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.friadenrich.pokemon.entryMVC.EntryView;
import com.friadenrich.pokemon.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditController implements View.OnClickListener {
    private EditView vista;
    private Activity ac;

    private EditModel modelo;

    public  EditController(EditView vista, Activity activity, EditModel modelo) {
        this.vista = vista;
        this.ac = activity;
        this.modelo = modelo;

        this.vista.asignarElementos(modelo);
        this.vista.btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sp = ac.getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String dexStr = sp.getString("Pokemones", "[]");

        try {
            JSONArray dexObj = new JSONArray(dexStr);

            JSONObject obj = dexObj.getJSONObject(EditModel.posicion);
            obj.put("name", vista.ed_nombre.getText().toString());

            dexObj.put(EditModel.posicion, obj);

            editor.putString("Pokemones", dexObj.toString());
            editor.apply();
            this.ac.finish();

        } catch (Exception e){
            Log.d("pok", "error");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this.ac.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
