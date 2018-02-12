package com.example.lucia.santaburguersf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.AdaptadorHamburguesas;
import com.example.lucia.santaburguersf.FireBase.Reference;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Inicio extends Fragment {

    private ArrayList<Hamburguesas> lista_promocion;
    private FirebaseDatabase database;
    private ListView listView;
    private AdaptadorPromocion adaptador;


    public Inicio() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflatedView = inflater.inflate(R.layout.fragment_inicio, container, false);


        lista_promocion = new ArrayList<>();

        database = FirebaseDatabase.getInstance();


        listView = inflatedView.findViewById(R.id.lv_inicio);


        adaptador = new AdaptadorPromocion(this.getContext(), lista_promocion);

        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        database.getReference(Reference.PROMOCION_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_promocion.removeAll(lista_promocion);

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Hamburguesas hamburguesa = snapshot.getValue(Hamburguesas.class);


                    lista_promocion.add(hamburguesa);
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return inflatedView;

    }

}
