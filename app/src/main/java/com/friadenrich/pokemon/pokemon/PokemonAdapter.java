package com.friadenrich.pokemon.pokemon;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.model.Pokedex;
import com.friadenrich.pokemon.model.Pokemon;
import com.friadenrich.pokemon.pokedex.IOnPokedexClick;
import com.friadenrich.pokemon.pokedex.PokedexViewHolder;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    private List<Pokemon> pokemones;
    private IOnPokemonClick listener;

    public PokemonAdapter(List<Pokemon> pokemones, IOnPokemonClick listener){
        this.pokemones = pokemones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_entry, parent, false);
        return new PokemonViewHolder(v, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon p = pokemones.get(position);
        holder.tv_nombre.setText(p.getName());
        holder.tv_tipo.setText(p.getType());
        holder.iv_img.setImageBitmap(BitmapFactory.decodeByteArray(p.getSprite(), 0, p.getSprite().length));
        holder.setPosicion(position);
    }

    @Override
    public int getItemCount() {
        return this.pokemones.size();
    }

    public void removeItem(int position) {
        pokemones.remove(position);
        notifyItemRemoved(position);
    }
}
