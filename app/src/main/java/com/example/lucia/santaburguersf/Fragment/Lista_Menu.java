package com.example.lucia.santaburguersf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.AdaptadorHamburguesas;
import com.example.lucia.santaburguersf.FireBase.Reference;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.HistorialPedido;
import com.example.lucia.santaburguersf.Pedido;
import com.example.lucia.santaburguersf.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.lucia.santaburguersf.MainActivity.mi_cuenta;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Menu extends Fragment {

    private static List<Hamburguesas> lista_hamburguesa;
    private GridView gridView;
    private AdaptadorHamburguesas adaptador;
    private View MiInflater;
    private ArrayList<UnPedido> listaPedidos = new ArrayList();
    private ArrayList<HistorialPedido> lista_historial = new ArrayList();
    private FirebaseDatabase database;


    public Lista_Menu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MiInflater = inflater.inflate(R.layout.fragment_lista__menu, container, false);

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

        database = FirebaseDatabase.getInstance();


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

            if (resultCode == RESULT_OK){


                switch ((int)data.getSerializableExtra("RESULTADO")) {
                    case 1:

                                UnPedido ham = (UnPedido) data.getSerializableExtra("pedido");

                                listaPedidos.add(ham);


                                Snackbar.make(MiInflater, "Se agrego a pedido", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;

                    case 2:

                                UnPedido ham_2 = (UnPedido) data.getSerializableExtra("pedido");

                                listaPedidos.add(ham_2);

                                Snackbar.make(MiInflater, "IR A PEDIDO", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                                Intent intent = new Intent(getContext(), Pedido.class);
                                intent.putExtra("listaPedidos", listaPedidos);
                                startActivityForResult(intent,1);

                                break;
                }

            }else if(resultCode == RESULT_CANCELED){

                Snackbar.make(MiInflater, "Se cancelo", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }

        }

        if(requestCode == 1){

            if (resultCode == RESULT_OK){

                    Snackbar.make(MiInflater, "Se realizo el pedido final", Snackbar.LENGTH_LONG).setAction("Action", null).show();


                    DatabaseReference pedidoUsuarioRef = database.getReference(Reference.USUARIO_REFERENCE+"/"+Reference.RODRIGO_REFERENCE+"/pedidos");

                    HistorialPedido historialPedido = new HistorialPedido();

                    historialPedido.setDireccion(data.getStringExtra("direccion"));
                    historialPedido.setTelefono(data.getStringExtra("telefono"));
                    historialPedido.setEstado("Pendiente");
                    historialPedido.setLista_pedido(listaPedidos);
                    historialPedido.setPrecio(totalAPagar());


                    Map<String, Object> childUpdate = new HashMap<>();

                    String id_pedido = historialPedido.getId();

                    childUpdate.put(id_pedido,historialPedido);
                    pedidoUsuarioRef.updateChildren(childUpdate);

                    DatabaseReference pedidoRef = database.getReference(Reference.PEDIDOS_REFERENCE);


                    Map<String, Object> pedidoUpdate = new HashMap<>();

                    pedidoUpdate.put("Rodrigo",historialPedido);
                    pedidoRef.child(id_pedido+"/").updateChildren(pedidoUpdate);


                    listaPedidos.removeAll(listaPedidos);

            }else if(resultCode == RESULT_CANCELED){

                Snackbar.make(MiInflater, "Se cancelo", Snackbar.LENGTH_LONG).setAction("Action", null).show();

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

    int totalAPagar(){

        int total = 0;

        for (UnPedido item : listaPedidos) {

            total += item.getPrecio();

        }

        return total;
    }
}
