package com.friadenrich.pokemon.pokemon;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.friadenrich.pokemon.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
    public TextView tv_nombre;
    public TextView tv_tipo;
    public ImageView iv_img;

    public Button btnEdit;
    public Button btnDelete;

    private IOnPokemonClick listener;
    private int posicion;

    public PokemonViewHolder(@NonNull View itemView, IOnPokemonClick listener) {
        super(itemView);

        this.tv_nombre = itemView.findViewById(R.id.team_rv_name);
        this.iv_img = itemView.findViewById(R.id.team_rv_img);
        this.tv_tipo = itemView.findViewById(R.id.team_rv_type);
        this.btnDelete = itemView.findViewById(R.id.team_rv_btn_del);
        this.btnEdit = itemView.findViewById(R.id.team_rv_btn_mod);

        this.listener = listener;

        itemView.setClickable(true);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    public void setPosicion(int p) {
        this.posicion = p;
    }

    @Override
    public void onClick(View view) {
        listener.onPokemonClick(view.getId(), posicion);
    }
}
