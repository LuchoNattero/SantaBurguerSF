package com.example.lucia.santaburguersf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.lucia.santaburguersf.AdaptadorHamburguesas;
import com.example.lucia.santaburguersf.AdaptadorPedido;
import com.example.lucia.santaburguersf.AdaptadorPedidoRealizado;
import com.example.lucia.santaburguersf.FireBase.Reference;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.HistorialPedido;
import com.example.lucia.santaburguersf.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mi_Cuenta extends Fragment {

    private View MiInflater;
    private Intent intent;
    private ArrayList<UnPedido> lista;
    private ListView lista_pedidos;
    private AdaptadorPedidoRealizado adpPedido;
    private static ArrayList<HistorialPedido> lista_historialPedido = new ArrayList();
    private FirebaseDatabase database;


    public Mi_Cuenta() {
        // Required empty public constructor
    }
//
//    public ArrayList<HistorialPedido> getLista_historialPedido() {
//        return lista_historialPedido;
//    }
//
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         // Inflate the layout for this fragment
        MiInflater = inflater.inflate(R.layout.fragment_mi__cuenta, container, false);
//
////
////        Snackbar.make(MiInflater, "paso el intent", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//
//
        database = FirebaseDatabase.getInstance();
//
//
        DatabaseReference pedidoRef = database.getReference(Reference.USUARIO_REFERENCE+"/"+Reference.RODRIGO_REFERENCE+"/pedidos");

        pedidoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lista_historialPedido.removeAll(lista_historialPedido);

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    HistorialPedido historialPedido = snapshot.getValue(HistorialPedido.class);
                   lista_historialPedido.add(historialPedido);
                }
                adpPedido.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//
        lista_pedidos = MiInflater.findViewById(R.id.lv_informacion);
//
        adpPedido = new AdaptadorPedidoRealizado(getContext(),lista_historialPedido);
        lista_pedidos.setAdapter(adpPedido);
        registerForContextMenu(lista_pedidos);
        lista_pedidos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        return MiInflater;
    }

}
