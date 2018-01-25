package com.example.lucia.santaburguersf.Fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.R;


public class ActividadDetalle extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Hamburguesas itemDetallado;
    private ImageView imagenExtendida;
    private Intent intent;
    private TextView detalles;
    private TextView nombre;
    private Button aclaracion;

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
                    Hamburguesas h = new Hamburguesas();
                    UnPedido nuevo_pedido = new UnPedido();

//                    intent.putExtra("nombre", itemDetallado.getNombre());
//                    intent.putExtra("drawable",itemDetallado.getIdDrawable());
//                    intent.putExtra("detalle",itemDetallado.getDetalle());

                    String aux1 = (itemDetallado.getNombre());
                    String aux2 = (itemDetallado.getDetalle());
                    int aux3 = (itemDetallado.getIdDrawable());


                    h.setIdDrawable(aux3);
                    h.setDetalle(aux2);
                    h.setNombre(aux1);

                    nuevo_pedido.setHamburguesa(h);

                    intent.putExtra("pedido",nuevo_pedido);
//                    intent.putExtra("RESULTADO",0);
                    setResult(RESULT_OK,intent);
                    finish();
            }
        });

        detalles = findViewById(R.id.tv_detalles);
        nombre = findViewById(R.id.tv_nombre);

        findViewById(R.id.bt_cantidad).setOnClickListener(this);
        findViewById(R.id.bt_aclaracion).setOnClickListener(this);


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


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_cantidad: createLoginDialogo();
                break;
            case R.id.bt_aclaracion: createLoginDialogoAclaracion();
                break;
        }

    }
    public void createLoginDialogo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_cantidad, null);


        Integer[] arreglo = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ListView lista = v.findViewById(R.id.sv_cantidad);
        ArrayAdapter adapterList = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arreglo);
        lista.setAdapter(adapterList);

        builder.setView(v);


        builder.show();
    }

    public void createLoginDialogoAclaracion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_aclaracion, null);


        builder.setView(v);


        builder.show();
    }


}
