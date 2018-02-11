package com.example.lucia.santaburguersf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.Fragment.UnPedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;
    private AdaptadorPedido adpPedido ;
    private ListView listaElementos;
    private ArrayList<UnPedido> lista;
    private EditText telefono;
    private EditText direccion;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        intent = getIntent();

        lista  = (ArrayList<UnPedido>) intent.getSerializableExtra("listaPedidos");

        findViewById(R.id.bt_Confirmar).setOnClickListener(this);

        telefono = findViewById(R.id.ed_telefono);
        direccion = findViewById(R.id.ed_direccion);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb_pedido);
        setSupportActionBar(myToolbar);


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

//        boton_realizar_pedido.setText("REALIZAR PEDIDO TOTAL: " );

    }
//    @Override
//    public void onClick(View view){

//    }
    int totalAPagar(){

        int total = 0;

        for (UnPedido item : lista) {

            total += item.getPrecio();

        }

        return total;
    }
    public void createLoginDialogoEditar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_pedido, null);


        builder.setView(v);


        builder.show();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.bt_Confirmar:

                boolean error = false;

                Toast.makeText(getApplicationContext(),"Realizo el pedido",Toast.LENGTH_LONG).show();

                if(telefono.getText().toString().isEmpty())
                {
                    telefono.setError("Debe completar el campo");
                    error = true;
                }
                if(direccion.getText().toString().isEmpty())
                {
                    direccion.setError("Debe completar el campo");
                    error = true;
                }

                if(!error)
                {
                    intent.putExtra("telefono",telefono.getText().toString());
                    intent.putExtra("direccion",direccion.getText().toString());
                    setResult(RESULT_OK,intent);

                    finish();
                }
                break;
        }


    }
}
