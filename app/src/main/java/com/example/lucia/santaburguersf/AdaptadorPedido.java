package com.example.lucia.santaburguersf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lucia on 21/1/2018.
 */

public class AdaptadorPedido extends BaseAdapter {

    private Context context;
    private List<Hamburguesas> lista_hamburguesas;

    public AdaptadorPedido(Context c){this.context = c;}
    public AdaptadorPedido(Context c, List<Hamburguesas> listHa){
        this.context = c;
        this.lista_hamburguesas = listHa;
    }

    @Override
    public int getCount() {
        return lista_hamburguesas.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_hamburguesas.get(position);
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

        Hamburguesas hamburguesa = (Hamburguesas) getItem(position);

//        imagen = hamburguesa.getIdDrawable();

        //ImageView imgView=(ImageView) findViewById(R.id.imgView);

        Drawable drawable  =imagen.getResources().getDrawable(hamburguesa.getIdDrawable());
        imagen.setImageDrawable(drawable);

        nombre.setText(hamburguesa.getNombre());
        cantidad.setText(hamburguesa.getDetalle());

//        final Hamburguesas item = getItem(position);
//        Glide.with(imagenCoche.getContext())
//                .load(item.getIdDrawable())
//                .into(imagenCoche);

//        nombreCoche.setText(item.getNombre());

        return convertView;

    }
}
