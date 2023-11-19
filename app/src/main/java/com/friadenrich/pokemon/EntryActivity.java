package com.friadenrich.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.entryMVC.EntryController;
import com.friadenrich.pokemon.entryMVC.EntryView;
import com.friadenrich.pokemon.model.Pokemon;
import com.friadenrich.pokemon.pokedex.PokedexHiloConexion;
import com.friadenrich.pokemon.pokemon.PokemonHiloConection;

import java.nio.charset.StandardCharsets;

public class EntryActivity extends AppCompatActivity implements Handler.Callback {
    String url;
    EntryView view;
    EntryController controller;
    Pokemon model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // TODO R.string
        actionBar.setTitle("View POKEMON");

        Bundle extras = getIntent().getExtras();
        url = extras.getString("url");

        Handler handler = new Handler(Looper.getMainLooper(), this);

        view = new EntryView(this);

        PokemonHiloConection hilo = new PokemonHiloConection(handler, url);
        hilo.start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        model = (Pokemon) message.obj;
        controller = new EntryController(view, this, model);

        return true;
    }
}