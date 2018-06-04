package com.example.angel.appfarmacia;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{
    private String nombreUsuario;
    private Carro carro;

    public Usuario(String username){
        nombreUsuario = username;
        carro = new Carro();
    }

    public void addProductoCarro(Producto p){
        carro.addProducto(nombreUsuario, p);
    }

    public void eliminarProductoCarro(Producto p){
        carro.eliminarProducto(nombreUsuario, p);
    }

    public void vaciarCarro(){
        carro.limpiarLista(nombreUsuario);
    }

    public ArrayList<Producto> getProductosCarro(){
        return carro.getProductos();
    }

    public double getSubtotalCarro(){
        return carro.getSubtotal();
    }
}
