package com.friadenrich.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.friadenrich.pokemon.editMVC.EditController;
import com.friadenrich.pokemon.editMVC.EditModel;
import com.friadenrich.pokemon.editMVC.EditView;
import com.friadenrich.pokemon.entryMVC.EntryController;
import com.friadenrich.pokemon.model.Pokemon;

public class EditActivity extends AppCompatActivity {
    int posicion;
    EditView view;
    EditModel model;
    EditController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        posicion = extras.getInt("posicion");
        view = new EditView(this);
        model = new EditModel(posicion, this);
        controller = new EditController(view, this, model);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            EditModel.posicion = -1;
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}