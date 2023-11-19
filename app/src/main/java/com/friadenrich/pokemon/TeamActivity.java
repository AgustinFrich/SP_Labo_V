package com.friadenrich.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.friadenrich.pokemon.editMVC.EditModel;
import com.friadenrich.pokemon.model.Pokemon;
import com.friadenrich.pokemon.pokedex.PokedexAdapter;
import com.friadenrich.pokemon.pokedex.PokedexHiloConexion;
import com.friadenrich.pokemon.pokemon.IOnPokemonClick;
import com.friadenrich.pokemon.pokemon.PokemonAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity implements IOnPokemonClick {

    List<Pokemon> pokemones;
    RecyclerView rv;
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
        String dexStr = sp.getString("Pokemones", "[]");
        pokemones = new ArrayList<Pokemon>();

        try {
            JSONArray dexObj = new JSONArray(dexStr);
            int size = dexObj.length();
            for(int i = 0; i < size; i ++ ){
                Pokemon p = Pokemon.jsonAPokemon(dexObj.getJSONObject(i));
                pokemones.add(p);
            }

            rv = super.findViewById(R.id.team_rv);

            LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(llm);

            adapter = new PokemonAdapter(pokemones, this );
            rv.setAdapter(adapter);
        } catch (Exception e) {

        }
    }


    @Override
    public void onPokemonClick(int buttonId, int index) {
        if(buttonId == R.id.team_rv_btn_del) {
            removePokemon(index);
        } else if (buttonId == R.id.team_rv_btn_mod) {
            Intent i = new Intent(this, EditActivity.class);
            i.putExtra("posicion", index);
            this.startActivity(i);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        int pos = EditModel.posicion;
        Log.d("pos", pos + "");
        if(pos >= 0){
            SharedPreferences sp = getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
            String dexStr = sp.getString("Pokemones", "[]");
            try {
                JSONArray dexObj = new JSONArray(dexStr);
                Pokemon p = Pokemon.jsonAPokemon(dexObj.getJSONObject(pos));
                pokemones.set(pos, p);
                adapter.notifyItemChanged(pos);
            }catch (Exception e){

            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO Mover metodo
    private void removePokemon(int index) {

        SharedPreferences sp = getSharedPreferences("Pokedex", Context.MODE_PRIVATE);
        try {
            adapter.removeItem(index);
            JSONArray arr = new JSONArray();
            for(Pokemon p: pokemones) {
                arr.put(p.pokemonAJson());
            }
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("Pokemones", arr.toString());
            editor.apply();

        } catch (Exception e){

        }
    }
}