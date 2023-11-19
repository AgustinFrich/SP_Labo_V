package com.friadenrich.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.friadenrich.pokemon.model.Pokedex;
import com.friadenrich.pokemon.pokedex.IOnPokedexClick;
import com.friadenrich.pokemon.pokedex.PokedexAdapter;
import com.friadenrich.pokemon.pokedex.PokedexHiloConexion;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, IOnPokedexClick {
    static List<Pokedex> pokedex;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://stackoverflow.com/questions/61023968/what-do-i-use-now-that-handler-is-deprecated
        Handler handler = new Handler(Looper.getMainLooper(), this);

        rv = super.findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        PokedexHiloConexion hilo = new PokedexHiloConexion(handler);
        hilo.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        pokedex = (List<Pokedex>) message.obj;

        PokedexAdapter adapter = new PokedexAdapter(pokedex, this);
        rv.setAdapter(adapter);

        return true;
    }

    @Override
    public void onPokedexClick(int buttonId, int posicion) {
        if(buttonId == R.id.rv_btn_info) {
            Intent i = new Intent(this, EntryActivity.class);
            i.putExtra("url", pokedex.get(posicion).getUrl());
            this.startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_equipo) {
            Log.d("ASDADAD", "ASD");
            Intent i = new Intent(this, TeamActivity.class);
            this.startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}