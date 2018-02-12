package com.example.lucia.santaburguersf.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lucia.santaburguersf.Hamburguesas;
import com.example.lucia.santaburguersf.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by lucia on 11/2/2018.
 */

public class AdaptadorPromocion extends BaseAdapter {

    private Context context;
    private ArrayList<Hamburguesas> lista_promocion = new ArrayList<>();


    public AdaptadorPromocion(Context context, ArrayList<Hamburguesas> lista_promocion) {
        this.context = context;
        this.lista_promocion = lista_promocion;
    }

    @Override
    public int getCount() {
        return lista_promocion.size();
    }

    @Override
    public Hamburguesas getItem(int position) {
        return lista_promocion.get(position);
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
            convertView = inflater.inflate(R.layout.promocion, parent, false);
        }
        Hamburguesas item = getItem(position);

        ImageView imagenHamburguesa = (ImageView) convertView.findViewById(R.id.im_promocion);
        TextView nombreHamburguesa = (TextView) convertView.findViewById(R.id.nombre_promocion_hamburguesa);


        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReference().child(item.getRefImagen());

        item.setStorageReferenceHamburguesa(storageRef);

        Glide.with(imagenHamburguesa.getContext())
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .fitCenter()
                .centerCrop()
                .into(imagenHamburguesa);

        nombreHamburguesa.setText(item.getNombre());




        return convertView;
    }
}
