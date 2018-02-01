package com.example.lucia.santaburguersf.Fragment;

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


        FloatingActionButton fab =  findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    Hamburguesas h = new Hamburguesas();
                    UnPedido nuevo_pedido = new UnPedido();

                    String aux1 = (itemDetallado.getNombre());
                    String aux2 = (itemDetallado.getDetalle());
                    String aux4 = (itemDetallado.getRefImagen());
                    int aux5 = (itemDetallado.getPrecio());
//                    StorageReference strf = itemDetallado.getStorageReferenceHamburguesa();


                    h.setDetalle(aux2);
                    h.setNombre(aux1);
                    h.setRefImagen(aux4);
                    h.setPrecio(aux5);
//                    h.setStorageReferenceHamburguesa(strf);

                    nuevo_pedido.setHamburguesa(h);
                    nuevo_pedido.setAclaracion(aclaracion_dialogo);
                    nuevo_pedido.setCantidad(cantidad_dialogo);
                    nuevo_pedido.setPrecio(h.getPrecio()*cantidad_dialogo);

                    intent.putExtra("pedido",nuevo_pedido);
                    intent.putExtra("RESULTADO",0);
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


        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);

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
            case R.id.bt_hacer_pedido: Toast.makeText(v.getContext(),"Su pedido fue realizado",Toast.LENGTH_LONG).show();

                Hamburguesas h = new Hamburguesas();
                UnPedido nuevo_pedido = new UnPedido();

                String aux1 = (itemDetallado.getNombre());
                String aux2 = (itemDetallado.getDetalle());
                String aux4 = (itemDetallado.getRefImagen());
                int aux5 = (itemDetallado.getPrecio());
//                    StorageReference strf = itemDetallado.getStorageReferenceHamburguesa();


                h.setDetalle(aux2);
                h.setNombre(aux1);
                h.setRefImagen(aux4);
                h.setPrecio(aux5);
//                    h.setStorageReferenceHamburguesa(strf);

                nuevo_pedido.setHamburguesa(h);
                nuevo_pedido.setAclaracion(aclaracion_dialogo);
                nuevo_pedido.setCantidad(cantidad_dialogo);
                nuevo_pedido.setPrecio(h.getPrecio()*cantidad_dialogo);

                intent.putExtra("pedido",nuevo_pedido);
                intent.putExtra("RESULTADO",2);
                setResult(RESULT_OK,intent);

                finish();
            break;
        }

    }
    public void createLoginDialogo() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_cantidad, null);


        final Integer[] arreglo = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        final ListView lista = v.findViewById(R.id.sv_cantidad);
        ArrayAdapter adapterList = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arreglo);
        lista.setAdapter(adapterList);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cantidad_dialogo = arreglo[position];
                Toast.makeText(view.getContext(),"cantidad " + cantidad_dialogo,Toast.LENGTH_LONG).show();
                //aca termine, seguir con pasar los pedidos y q se acepte el pedido, usar el "Resultado" : 0  ó 1 para identificar el "RESULT_OK"
            }
        });


        builder.setView(v);


        builder.show();
    }

    public void createLoginDialogoAclaracion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_aclaracion, null);

        EditText aclaracion = v.findViewById(R.id.ed_aclaracion_dialog);


        aclaracion_dialogo = aclaracion.getText().toString();

        builder.setView(v);


        builder.show();
    }


}
