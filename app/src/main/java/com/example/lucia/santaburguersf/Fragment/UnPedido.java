package com.example.lucia.santaburguersf.Fragment;

import android.media.Image;

/**
 * Created by lucia on 21/1/2018.
 */

public class UnPedido {

    private int img;
    private String nombre;
    private String cantidad;

    public UnPedido(String nombre, int idDrawable,String de) {
        this.nombre = nombre;
        this.img = idDrawable;
        this.cantidad = de;
    }
}
