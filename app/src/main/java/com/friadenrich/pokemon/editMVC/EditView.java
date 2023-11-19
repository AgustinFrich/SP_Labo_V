package com.friadenrich.pokemon.editMVC;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.friadenrich.pokemon.R;
import com.friadenrich.pokemon.model.Pokemon;

public class EditView {
    ImageView iv_img;
    EditText ed_nombre;
    TextView tv_id;

    Button btn_save;

    Activity ac;

    public EditView(Activity ac) {
        this.ac = ac;
    }

    public void asignarElementos(EditModel model){
        iv_img = this.ac.findViewById(R.id.edit_image);
        ed_nombre = this.ac.findViewById(R.id.edit_name);
        tv_id = this.ac.findViewById(R.id.edit_id);

        ed_nombre.setText(model.getName());
        tv_id.setText("No. " + model.getId());

        iv_img.setImageBitmap(BitmapFactory.decodeByteArray(model.getSprite(), 0, model.getSprite().length));

        btn_save = this.ac.findViewById(R.id.edit_btn_save);
    }
}
