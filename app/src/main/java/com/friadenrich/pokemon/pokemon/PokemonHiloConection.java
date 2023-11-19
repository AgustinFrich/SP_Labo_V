package com.friadenrich.pokemon.pokemon;

import android.os.Handler;
import android.os.Message;

import com.friadenrich.pokemon.api.PokemonApiConection;
import com.friadenrich.pokemon.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonHiloConection extends Thread {
    String ruta;
    Handler handler;

    public PokemonHiloConection(Handler handler, String ruta) {
        this.handler = handler;
        this.ruta = ruta;
    }

    public void run() {
        try {
            PokemonApiConection con = new PokemonApiConection();
            String respuesta = con.obtenerString(ruta);

            JSONObject obj = new JSONObject(respuesta);

            Pokemon pkmn = new Pokemon(obj);

            byte[] imagen = con.obtenerImagen(pkmn.spriteURL);

            pkmn.setSprite(imagen);

            Message message = new Message();
            message.obj = pkmn;

            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}