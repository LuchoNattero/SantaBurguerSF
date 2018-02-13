package com.example.lucia.santaburguersf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.santaburguersf.FireBase.Reference;
import com.example.lucia.santaburguersf.Fragment.Mi_Cuenta;
import com.example.lucia.santaburguersf.Fragment.UnPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucia on 7/2/2018.
 */

public class AdaptadorPedidoRealizado extends BaseAdapter {

    private ArrayList<HistorialPedido> lista_pedido;
    Context context;
    private Map<String,Integer> l_pedidos = new HashMap<>();




    public AdaptadorPedidoRealizado(Context c, ArrayList<HistorialPedido> lp){

        this.context = c;
        this.lista_pedido = lp;

    }
    @Override
    public int getCount() {
        return this.lista_pedido.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista_pedido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pedido_realizado, parent, false);
        }

        HistorialPedido h = (HistorialPedido) getItem(position);

        TextView estado = convertView.findViewById(R.id.tv_estado);
        TextView precio = convertView.findViewById(R.id.tv_precio_realizado);
        TextView lista_pedido = convertView.findViewById(R.id.lv_pedido_realizado);
        TextView telefono = convertView.findViewById(R.id.lv_telefono_realizado);
        TextView direccion = convertView.findViewById(R.id.lv_direccion_realizado);


        lista_pedido.setText("");

        for(UnPedido item : h.getLista_pedido()){

            lista_pedido.setText(lista_pedido.getText()+ item.getHamburguesa()+" ");

//            l_pedidos.put(item.getHamburguesa(),item.getCantidad());

        }

//        Mi_Cuenta.setPedidos(l_pedidos);



        estado.setText("Estado: "+h.getEstado());
        precio.setText(String.valueOf("Monto Total: $" + totalAPagar(h)));
        telefono.setText("Telefono: "+h.getTelefono());
        direccion.setText("Dirección: "+h.getDireccion());



        return convertView;
    }
    int totalAPagar(HistorialPedido h){

        int total = 0;

        for (UnPedido item : h.getLista_pedido()) {

            total += item.getPrecio();

        }

        return total;
    }
}
