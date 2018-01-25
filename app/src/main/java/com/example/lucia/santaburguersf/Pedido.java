package com.example.lucia.santaburguersf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.Fragment.UnPedido;

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

        ArrayList<UnPedido> lista  = (ArrayList<UnPedido>) intent.getSerializableExtra("listaPedidos");

        listaElementos = findViewById(R.id.lv_pedido);
        adpPedido = new AdaptadorPedido(getBaseContext(),lista);
        listaElementos.setAdapter(adpPedido);
        registerForContextMenu(listaElementos);
        listaElementos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listaElementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                createLoginDialogoEditar();

            }
        });



    }


    public void createLoginDialogoEditar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_pedido, null);


        builder.setView(v);


        builder.show();
    }
}
