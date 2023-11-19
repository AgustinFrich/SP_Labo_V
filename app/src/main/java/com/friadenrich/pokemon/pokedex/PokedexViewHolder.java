package com.friadenrich.pokemon.pokedex;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.friadenrich.pokemon.R;

public class PokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tv_nombre;
    public Button btnInfo;

    private IOnPokedexClick listener;
    private int posicion;

    public PokedexViewHolder(@NonNull View itemView, IOnPokedexClick listener) {
        super(itemView);

        this.tv_nombre = itemView.findViewById(R.id.rv_nombre);

        this.btnInfo = itemView.findViewById(R.id.rv_btn_info);
        this.listener = listener;

        itemView.setClickable(true);
        btnInfo.setOnClickListener(this);
    }

    public void setPosicion(int p) {
        this.posicion = p;
    }

    @Override
    public void onClick(View view) {
        listener.onPokedexClick(view.getId(), posicion);
    }

}