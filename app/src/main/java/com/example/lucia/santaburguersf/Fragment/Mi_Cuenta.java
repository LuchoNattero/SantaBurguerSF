package com.example.lucia.santaburguersf.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mi_Cuenta extends Fragment {

    private View MiInflater;

    private ListView lista_pedidos;
    private AdaptadorPedidoRealizado adpPedido;
    private static ArrayList<HistorialPedido> lista_historialPedido = new ArrayList();
    private FirebaseDatabase database;
    private DatabaseReference pedidoRef;
    private ProgressDialog progressDialog_principal;
    public Mi_Cuenta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         // Inflate the layout for this fragment
        MiInflater = inflater.inflate(R.layout.fragment_mi__cuenta, container, false);

        database = FirebaseDatabase.getInstance();

        progressDialog_principal = new ProgressDialog(getContext());

        pedidoRef = database.getReference(Reference.USUARIO_REFERENCE+"/"+Reference.RODRIGO_REFERENCE+"/pedidos");


        lista_pedidos = MiInflater.findViewById(R.id.lv_informacion);

        adpPedido = new AdaptadorPedidoRealizado(getContext(),lista_historialPedido);
        lista_pedidos.setAdapter(adpPedido);


        TextView nombreUs = MiInflater.findViewById(R.id.tv_nombre_usuario);

        nombreUs.setText("Nombre: "+ Reference.RODRIGO_REFERENCE);

        progressDialog_principal.setTitle("Cargando");
        progressDialog_principal.setMessage("Se esta cargando los pedidos");
        progressDialog_principal.setCancelable(false);
        progressDialog_principal.show();

        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {


                pedidoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        lista_historialPedido.removeAll(lista_historialPedido);

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            HistorialPedido historialPedido = snapshot.getValue(HistorialPedido.class);
                            lista_historialPedido.add(historialPedido);
                        }
                        adpPedido.notifyDataSetChanged();
                        progressDialog_principal.dismiss();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });



        registerForContextMenu(lista_pedidos);
        lista_pedidos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        return MiInflater;
    }


}
