package com.example.lucia.santaburguersf;

import com.example.lucia.santaburguersf.Fragment.UnPedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lucia on 7/2/2018.
 */

public class HistorialPedido implements Serializable {


    private ArrayList<UnPedido>lista_pedido;
    private int precio;
    private String estado;
    private String direccion;
    private String telefono;

    public HistorialPedido() {
    }

    public HistorialPedido(ArrayList<UnPedido> lista_pedido, int precio, String estado, String direccion, String telefono) {
        this.lista_pedido = lista_pedido;
        this.precio = precio;
        this.estado = estado;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public HistorialPedido(ArrayList<UnPedido> lista_pedido, int precio, String estado) {
        this.lista_pedido = lista_pedido;
        this.precio = precio;
        this.estado = estado;
    }
/*
    public HistorialPedido(ArrayList<UnPedido> lista_pedido) {
        this.lista_pedido = lista_pedido;
    }*/

    public ArrayList<UnPedido> getLista_pedido() {
        return lista_pedido;
    }

    public void setLista_pedido(ArrayList<UnPedido> lista_pedido) {
        this.lista_pedido = lista_pedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }
    public String getId(){

        return String.valueOf((estado+precio+telefono+direccion+(Math.random() * 100) + 1).hashCode());

    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
