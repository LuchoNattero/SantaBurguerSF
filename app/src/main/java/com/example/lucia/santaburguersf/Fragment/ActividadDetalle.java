package com.example.lucia.santaburguersf.Fragment;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.R;

import java.io.Serializable;


public class ActividadDetalle extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Hamburguesas itemDetallado;
    private ImageView imagenExtendida;
    private Intent intent;
    private TextView detalles;
    private TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        intent = getIntent();
        FloatingActionButton fab =  findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                    Hamburguesas h = null;
                    intent.putExtra("nombre", itemDetallado.getNombre());
                    intent.putExtra("drawable",itemDetallado.getIdDrawable());
                    intent.putExtra("detalle",itemDetallado.getDetalle());
//
//                    h.setIdDrawable(itemDetallado.getIdDrawable());
//                    h.setDetalle(itemDetallado.getDetalle());
//                    h.setNombre(itemDetallado.getNombre());
//                    intent.putExtra("pedido",h);
//                    intent.putExtra("RESULTADO",0);
                    setResult(RESULT_OK,intent);
                    finish();
            }
        });

        detalles = findViewById(R.id.tv_detalles);
        nombre = findViewById(R.id.tv_nombre);

//        usarToolbar();

        // Obtener el coche con el identificador establecido en la actividad principal
        itemDetallado = Hamburguesas.getItem(intent.getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);
        detalles.setText(itemDetallado.getDetalle());
        nombre.setText(itemDetallado.getNombre());
        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }

    private void usarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
