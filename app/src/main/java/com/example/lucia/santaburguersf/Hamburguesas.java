package com.example.lucia.santaburguersf;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by lucia on 9/1/2018.
 */

public class Hamburguesas implements Serializable {

    private String nombre;
    private int idDrawable;

    private String detalle;

    public Hamburguesas(){}

    public Hamburguesas(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public Hamburguesas(String nombre, int idDrawable,String de) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.detalle = de;
    }

    public Hamburguesas(String nombre) {
        this.nombre = nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static Hamburguesas[] ITEMS_2 = {
            new Hamburguesas("Santa Catalina",R.drawable.santa_catalina),
            new Hamburguesas("Santa Burguer",R.drawable.santa_burgue),
            new Hamburguesas("Santa Monica",R.drawable.santa_monica_2),
            new Hamburguesas("Sweet Burguer",R.drawable.sweet_burger),
            new Hamburguesas("Veggie Buerguer",R.drawable.veggie_burguer_2),
            new Hamburguesas("Doble Clasica",R.drawable.doble_clasica_2),


    };
    public static Hamburguesas[] ITEMS = {
            new Hamburguesas("Santa Catalina",R.drawable.santa_catalina,"detalle del producto"),
            new Hamburguesas("Santa Burguer",R.drawable.santa_burgue,"detalle del producto"),
            new Hamburguesas("Santa Monica",R.drawable.santa_monica_2,"detalle del producto"),
            new Hamburguesas("Sweet Burguer",R.drawable.sweet_burger,"detalle del producto"),
            new Hamburguesas("Veggie Buerguer",R.drawable.veggie_burguer_2,"detalle del producto"),
            new Hamburguesas("Doble Clasica",R.drawable.doble_clasica_2,"detalle del producto"),


    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static Hamburguesas getItem(int id) {
        for (Hamburguesas item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
