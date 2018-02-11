package com.example.lucia.santaburguersf.Fragment;

import android.media.Image;

import com.example.lucia.santaburguersf.Hamburguesas;

import java.io.Serializable;

/**
 * Created by lucia on 21/1/2018.
 */

public class UnPedido implements Serializable{

    private String hamburguesa = null;
    private int cantidad;
    private String aclaracion = null;
    private int precio = 0;
    private String refImagen;

    public UnPedido(){}

    public UnPedido(String hamb, int cant,String acla,int p) {

        this.hamburguesa = hamb;
        this.cantidad = cant;
        this.aclaracion = acla;
        this.precio = p;

    }

    @Override
    public String toString() {
        return "UnPedido{" +
                "hamburguesa=" + hamburguesa +
                ", cantidad=" + cantidad +
                ", aclaracion='" + aclaracion + '\'' +
                ", precio=" + precio +
                '}';
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getHamburguesa() {
        return hamburguesa;
    }

    public String getRefImagen() {
        return refImagen;
    }

    public void setRefImagen(String refImagen) {
        this.refImagen = refImagen;
    }

    public void setHamburguesa(String hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }






}
