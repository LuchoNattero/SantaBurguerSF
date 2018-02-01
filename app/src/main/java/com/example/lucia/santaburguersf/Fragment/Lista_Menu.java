package com.example.lucia.santaburguersf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lucia.santaburguersf.AdaptadorHamburguesas;
import com.example.lucia.santaburguersf.FireBase.Reference;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.Pedido;
import com.example.lucia.santaburguersf.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Menu extends Fragment{

    private static List<Hamburguesas> lista_hamburguesa;
    private GridView gridView;
    private AdaptadorHamburguesas adaptador;
    private View MiInflater;
    private ArrayList<UnPedido> listaPedidos = new ArrayList();


    public Lista_Menu() {
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

        lista_hamburguesa = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        gridView = (GridView) MiInflater.findViewById(R.id.grid);


        adaptador = new AdaptadorHamburguesas(this.getContext(),lista_hamburguesa);

        gridView.setAdapter(adaptador);

        database.getReference(Reference.HAMBURGUESA_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_hamburguesa.removeAll(lista_hamburguesa);

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Hamburguesas hamburguesa = snapshot.getValue(Hamburguesas.class);

                    lista_hamburguesa.add(hamburguesa);
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        registerForContextMenu(gridView);

        gridView.setClickable(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Hamburguesas item = (Hamburguesas) parent.getItemAtPosition(position);

//                Toast.makeText(view.getContext(),item.getRefImagen(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), ActividadDetalle.class);
                intent.putExtra(ActividadDetalle.EXTRA_PARAM_ID, item.getId());
                startActivityForResult(intent,0);
            }
        });
        return MiInflater;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


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

                    UnPedido ham = (UnPedido) data.getSerializableExtra("pedido");

                    listaPedidos.add(ham);


                    Snackbar.make(MiInflater, "Se agrego a pedido", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    break;
            }
        }

        if(requestCode == 2){

            switch (resultCode) {

                case RESULT_CANCELED:
                    Snackbar.make(MiInflater, "Se cancelo", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    break;

                case RESULT_OK:

            }
        }

    }

    public static Hamburguesas getItem(int id) {
        for (Hamburguesas item : lista_hamburguesa) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
