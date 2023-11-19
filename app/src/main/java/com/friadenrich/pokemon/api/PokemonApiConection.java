package com.friadenrich.pokemon.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PokemonApiConection {
    public String obtenerString(String pokeapiUrl) {
        try {
            ByteArrayOutputStream baos = obtenerBaos(pokeapiUrl);
                String s = new String(baos.toByteArray());
                return s;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public byte[] obtenerImagen(String pokeapiUrl){
        try {
            ByteArrayOutputStream baos = obtenerBaos(pokeapiUrl);

            return baos.toByteArray();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private ByteArrayOutputStream obtenerBaos(String pokeapiUrl) throws IOException {
        URL url = new URL(pokeapiUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        if(urlConnection.getResponseCode() == 200) {
            InputStream is = urlConnection.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }

            is.close();

            return baos;
        }
        return null;
    }
}
