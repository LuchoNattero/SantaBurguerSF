package com.example.lucia.santaburguersf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends AppCompatActivity {

    private Intent intent;
    private AdaptadorPedido adpPedido ;
    private ListView listaElementos;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        intent = getIntent();

        ArrayList<Hamburguesas> lista  = (ArrayList<Hamburguesas>) intent.getSerializableExtra("listaPedidos");

        listaElementos = findViewById(R.id.lv_pedido);
        adpPedido = new AdaptadorPedido(getBaseContext(),lista);
        listaElementos.setAdapter(adpPedido); //  Falla acá!
        registerForContextMenu(listaElementos);
        listaElementos.setClickable(true);
        listaElementos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


    }
}
