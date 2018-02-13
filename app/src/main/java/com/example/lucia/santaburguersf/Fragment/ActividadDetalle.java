package com.example.lucia.santaburguersf.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;



public class ActividadDetalle extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private Hamburguesas itemDetallado;
    private ImageView imagenExtendida;
    private Intent intent;
    private TextView detalles;
    private TextView nombre;
    private String aclaracion_dialogo;
    private int cantidad_dialogo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        intent = getIntent();
        Toolbar toolbar =  findViewById(R.id.tb_detalle);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String h;
                    UnPedido nuevo_pedido = new UnPedido();

                    nuevo_pedido.setHamburguesa(itemDetallado.getNombre());
                    nuevo_pedido.setAclaracion(aclaracion_dialogo);
                    nuevo_pedido.setCantidad(cantidad_dialogo);
                    nuevo_pedido.setPrecio(itemDetallado.getPrecio()*cantidad_dialogo);
                    nuevo_pedido.setRefImagen(itemDetallado.getRefImagen());

                    intent.putExtra("pedido",nuevo_pedido);
                    intent.putExtra("RESULTADO",1);
                    setResult(RESULT_OK,intent);

                    finish();
            }
        });

        detalles = findViewById(R.id.tv_detalles);
        nombre = findViewById(R.id.tv_nombre);

        findViewById(R.id.bt_cantidad).setOnClickListener(this);
        findViewById(R.id.bt_aclaracion).setOnClickListener(this);
        findViewById(R.id.bt_hacer_pedido).setOnClickListener(this);


        itemDetallado = Lista_Menu.getItem(intent.getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida =  findViewById(R.id.imagen_extendida);

        detalles.setText(itemDetallado.getDetalle());
        nombre.setText(itemDetallado.getNombre());



        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {


        Glide.with(imagenExtendida.getContext())
                .using(new FirebaseImageLoader())
                .load(itemDetallado.getStorageReferenceHamburguesa())
                .fitCenter()
                .centerCrop()
                .into(imagenExtendida);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.bt_cantidad: createLoginDialogo();
                break;
            case R.id.bt_aclaracion: createLoginDialogoAclaracion();
                break;
            case R.id.bt_hacer_pedido: Toast.makeText(v.getContext(),"Su pedido fue realizado",Toast.LENGTH_LONG).show();


                String h;
                UnPedido nuevo_pedido = new UnPedido();

                h = itemDetallado.getNombre();
                nuevo_pedido.setHamburguesa(h);
                nuevo_pedido.setAclaracion(aclaracion_dialogo);
                nuevo_pedido.setCantidad(cantidad_dialogo);
                nuevo_pedido.setPrecio(itemDetallado.getPrecio()*cantidad_dialogo);
                nuevo_pedido.setRefImagen(itemDetallado.getRefImagen());

                intent.putExtra("pedido",nuevo_pedido);
                intent.putExtra("RESULTADO",2);

                setResult(RESULT_OK, intent);
                finish();
            break;
        }

    }
    public void createLoginDialogo() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_cantidad, null);


        final String[] arreglo = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        final ListView lista = v.findViewById(R.id.sv_cantidad);

        ArrayAdapter adapterList = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arreglo);

        builder.setTitle("Cantidad");
        builder.setAdapter(adapterList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cantidad_dialogo = Integer.valueOf(arreglo[which]);

                dialog.cancel();
            }
        });
        builder.setView(v);


        builder.show();
    }

    public void createLoginDialogoAclaracion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_aclaracion, null);

        final EditText aclaracion = v.findViewById(R.id.ed_aclaracion_dialog);

        builder.setTitle("Aclaracion");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                aclaracion_dialogo = aclaracion.getText().toString();


            }
        });


        builder.setView(v);


        builder.show();
    }


}
