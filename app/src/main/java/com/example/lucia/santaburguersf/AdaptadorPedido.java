package com.example.lucia.santaburguersf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucia.santaburguersf.Fragment.UnPedido;

import java.util.List;

/**
 * Created by lucia on 21/1/2018.
 */

public class AdaptadorPedido extends BaseAdapter {

    private Context context;
    private List<UnPedido> lista_pedidos;

    public AdaptadorPedido(Context c){this.context = c;}
    public AdaptadorPedido(Context c, List<UnPedido> listPe){
        this.context = c;
        this.lista_pedidos = listPe;
    }

    @Override
    public int getCount() {
        return lista_pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_pedidos.get(position);
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
            convertView = inflater.inflate(R.layout.un_pedido, parent, false);
        }

        ImageView imagen = (ImageView) convertView.findViewById(R.id.im_un_pedido);
        TextView nombre = (TextView) convertView.findViewById(R.id.tv_nombre_pedido);
        TextView cantidad = convertView.findViewById(R.id.tv_aclaracion_pedido);

        UnPedido pedido = (UnPedido) getItem(position);


        Drawable drawable  = imagen.getResources().getDrawable(pedido.getHamburguesa().getIdDrawable());
        imagen.setImageDrawable(drawable);

        nombre.setText(pedido.getHamburguesa().getNombre());
        cantidad.setText(pedido.getHamburguesa().getDetalle());
        return convertView;

    }
}
