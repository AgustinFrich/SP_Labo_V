package com.friadenrich.pokemon.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.model.Pokedex;


import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexViewHolder> {
    private List<Pokedex> pokedex;
    private IOnPokedexClick listener;

    public PokedexAdapter(List<Pokedex> pokedex, IOnPokedexClick listener){
        this.pokedex = pokedex;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokedex_entry, parent, false);
        return new PokedexViewHolder(v, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokedex p = pokedex.get(position);
        holder.tv_nombre.setText(p.getName());

        holder.setPosicion(position);
    }

    @Override
    public int getItemCount() {
        return this.pokedex.size();
    }
}