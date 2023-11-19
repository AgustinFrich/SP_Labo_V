package com.friadenrich.pokemon.pokedex;

import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;

import com.friadenrich.pokemon.api.PokemonApiConection;
import com.friadenrich.pokemon.model.Pokedex;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class PokedexHiloConexion extends Thread {
    String ruta = "https://pokeapi.co/api/v2/pokemon?limit=151";
    Handler handler;

    public PokedexHiloConexion(Handler handler){
        this.handler = handler;
    }
    public void run() {
        try {
            PokemonApiConection con = new PokemonApiConection();
            String respuesta = con.obtenerString(ruta);

            JSONObject obj = new JSONObject(respuesta);
            JSONArray arr =  obj.getJSONArray("results");

            List<Pokedex> pokedex = Pokedex.fromJsonArray(arr);

            Message message = new Message();
            message.obj = pokedex;

            handler.sendMessage(message);

        } catch (Exception e){

        }
    }
}
