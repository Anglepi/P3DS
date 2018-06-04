package com.example.angel.appfarmacia;

import java.io.Serializable;

public class Producto implements Serializable{
    private String nombre;
    private Double precio;
    private int cantidad;
    private int pk;

    public Producto(String nom, int cant, Double precio, int pk){
        nombre = nom;
        cantidad = cant;
        this.precio = precio;
        this.pk = pk;
    }

    public Boolean getDisponibilidad(){
        return cantidad>0;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public String getCantidad(){
        if(getDisponibilidad())
            return "Quedan " + cantidad;
        else
            return "NO DISPONIBLE";
    }

    public int getID(){
        return pk;
    }
}
