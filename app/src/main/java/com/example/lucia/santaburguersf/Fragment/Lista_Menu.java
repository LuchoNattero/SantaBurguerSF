package com.example.lucia.santaburguersf.Fragment;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.AdaptadorHamburguesas;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.Pedido;
import com.example.lucia.santaburguersf.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Menu extends Fragment{

    private GridView gridView;
    private AdaptadorHamburguesas adaptador;
    private View MiInflater;
    private ArrayList<Hamburguesas> listaPedidos = new ArrayList();


    public Lista_Menu() {
        // Required empty public constructor
    }

//MODIFICAR SEGUN LA PAGINA DE INTERNET ENCONTRADA DE EJEMPLO.....http://www.hermosaprogramacion.com/2015/07/tutorial-para-crear-un-gridview-en-android/
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MiInflater = inflater.inflate(R.layout.fragment_lista__menu, container, false);
//
        FloatingActionButton fab =  MiInflater.findViewById(R.id.fab_button_pedidos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Pedido.class);
                intent.putExtra("listaPedidos", listaPedidos);
                startActivityForResult(intent,1);
            }
        });




        gridView = (GridView) MiInflater.findViewById(R.id.grid);
        adaptador = new AdaptadorHamburguesas(this.getContext());
        gridView.setAdapter(adaptador);
        registerForContextMenu(gridView);

        gridView.setClickable(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hamburguesas item = (Hamburguesas) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), ActividadDetalle.class);
                intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());
                startActivityForResult(intent,0);
            }
        });
        return MiInflater;
    }
//
//    private void usarToolbar(View v) {
//        Toolbar toolbar = (Toolbar) v.findViewById(R.id.tb_inicio);
//        setSupportActionBar(toolbar);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".


        if(requestCode == 0){
            switch (resultCode) {
                case RESULT_CANCELED:
                    Snackbar.make(MiInflater, "Se cancelo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    break;
                // Si es así mostramos mensaje de cancelado por pantalla.
                case RESULT_OK:


                    String aux1 = (data.getExtras().getString("nombre"));
                    String aux2 = (data.getExtras().getString("detalle"));
                    int aux3 = (data.getExtras().getInt("drawable"));

                    Hamburguesas ham = new Hamburguesas(aux1,aux3,aux2);

//                    ham.setNombre(aux1);
//                    ham.setDetalle(aux2);
//                    ham.setIdDrawable(aux3);
                    listaPedidos.add(ham);


                    Snackbar.make(MiInflater, "Se agrego a pedido", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    break;
            }
        }
    }

}
