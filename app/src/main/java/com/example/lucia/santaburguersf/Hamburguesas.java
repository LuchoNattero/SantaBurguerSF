package com.example.lucia.santaburguersf;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

/**
 * Created by lucia on 9/1/2018.
 */

public class Hamburguesas implements Serializable {

    private String nombre;
    private int precio;
    private String refImagen;
    private String detalle;
//    private int id = nombre.hashCode();
    StorageReference storageReferenceHamburguesa;

    public Hamburguesas(){}

    public Hamburguesas(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.precio = idDrawable;
    }

    public Hamburguesas(String nombre, int idDrawable,String de) {
        this.nombre = nombre;
        this.precio = idDrawable;
        this.refImagen = de;
    }

    @Override
    public String toString() {
        return "Hamburguesas{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", refImagen='" + refImagen + '\'' +
                ", detalle='" + detalle + '\'' +
                ", storageReferenceHamburguesa=" + storageReferenceHamburguesa +
                '}';
    }

    public Hamburguesas(String nombre) {
        this.nombre = nombre;
    }

    public StorageReference getStorageReferenceHamburguesa() {
        return storageReferenceHamburguesa;
    }

    public void setStorageReferenceHamburguesa(StorageReference storageReferenceHamburguesa) {
        this.storageReferenceHamburguesa = storageReferenceHamburguesa;
    }

    public String getRefImagen() {
        return refImagen;
    }

    public void setRefImagen(String refImagen) {
        this.refImagen = refImagen;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int idDrawable) {
        this.precio = idDrawable;
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

    public int getPrecio() {
        return precio;
    }

    public int getId() {
        return nombre.hashCode();
    }
//
//    public void setId(int i){this.id = i;}


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
