package com.friadenrich.pokemon.entryMVC;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.model.Pokemon;

public class EntryView {
    ImageView iv_img;
    TextView tv_nombre;
    TextView tv_id;
    TextView tv_weight;
    TextView tv_height;
    TextView tv_type;
    Button btn_add;

    Activity ac;

    public EntryView(Activity ac) {
        this.ac = ac;
    }

    public void asignarElementos(Pokemon pkmn){
        iv_img = this.ac.findViewById(R.id.entry_image);
        tv_nombre = this.ac.findViewById(R.id.entry_name);
        tv_id = this.ac.findViewById(R.id.entry_id);
        tv_weight = this.ac.findViewById(R.id.entry_weight);
        tv_height = this.ac.findViewById(R.id.entry_height);
        tv_type = this.ac.findViewById(R.id.entry_type);

        tv_nombre.setText(pkmn.name.toUpperCase());
        tv_id.setText("No. " + pkmn.id);
        tv_weight.setText("WT " + pkmn.weight / 10 + "kg");
        tv_height.setText("HT " + pkmn.height / 10 + "m");
        tv_type.setText("Type: " + pkmn.type.toUpperCase());

        iv_img.setImageBitmap(BitmapFactory.decodeByteArray(pkmn.getSprite(), 0, pkmn.getSprite().length));

        btn_add = this.ac.findViewById(R.id.entry_btn);
    }
}
