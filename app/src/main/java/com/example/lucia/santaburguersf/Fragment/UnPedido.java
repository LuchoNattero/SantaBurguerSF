package com.example.lucia.santaburguersf.Fragment;

import android.media.Image;

import com.example.lucia.santaburguersf.Hamburguesas;

import java.io.Serializable;

/**
 * Created by lucia on 21/1/2018.
 */

public class UnPedido implements Serializable{

    private Hamburguesas hamburguesa = null;
    private int cantidad;
    private String aclaracion = null;
    private int precio = 0;

    public UnPedido(){}

    public UnPedido(Hamburguesas hamb, int cant,String acla,int p) {

        this.hamburguesa = hamb;
        this.cantidad = cant;
        this.aclaracion = acla;
        this.precio = p;

    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Hamburguesas getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(Hamburguesas hamburguesa) {
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
