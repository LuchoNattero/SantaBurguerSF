package com.example.lucia.santaburguersf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lucia.santaburguersf.Fragment.Lista_Menu;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucia on 9/1/2018.
 */

public class AdaptadorHamburguesas extends BaseAdapter{

    private Context context;
    private List<Hamburguesas> Lista_hamburguesas = new ArrayList<>();

    public AdaptadorHamburguesas(Context context, List<Hamburguesas> lista_hamburguesas) {
        this.context = context;
        Lista_hamburguesas = lista_hamburguesas;
    }



    public AdaptadorHamburguesas(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Lista_hamburguesas.size();
    }

    @Override
    public Hamburguesas getItem(int position) {
        return Lista_hamburguesas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }
        final Hamburguesas item = getItem(position);
        ImageView imagenHamburguesa = (ImageView) view.findViewById(R.id.imagen_coche);
        TextView nombreHamburguesa = (TextView) view.findViewById(R.id.nombre_coche);


        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReference().child(item.getRefImagen());

        item.setStorageReferenceHamburguesa(storageRef);

//        Toast.makeText(view.getContext(),item.getRefImagen(),Toast.LENGTH_SHORT).show();
        Glide.with(imagenHamburguesa.getContext())
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .fitCenter()
                .centerCrop()
                .into(imagenHamburguesa);

        nombreHamburguesa.setText(item.getNombre());

        return view;
    }
}
