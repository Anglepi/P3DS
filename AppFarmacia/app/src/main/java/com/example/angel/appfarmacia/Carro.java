package com.example.angel.appfarmacia;

import java.io.Serializable;
import java.util.ArrayList;

public class Carro implements Serializable {

    private ArrayList<Producto> productos = new ArrayList<>();
    private double total;


    public Carro(){
        total = 0;
    }

    public void addProducto(String user, Producto p){
        productos.add(p);
        total+=p.getPrecio();
        //updateCarroBD();
    }

    public void eliminarProducto(String user, Producto p){
        productos.remove(p);
        total-=p.getPrecio();
        //updateCarroBD();
    }

    public void limpiarLista(String user){
        productos = new ArrayList<>();
        total = 0.0;
        //updateCarroBD();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getSubtotal(){
        return total;
    }

    private void updateCarroBD(){
        //Actualiza el carro en la BD
    }
}
