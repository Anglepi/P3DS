package com.example.angel.appfarmacia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorProductos extends ArrayAdapter<Producto> {
    private final Context context;
    private ArrayList<Producto> productos;

    public AdaptadorProductos(Context context, ArrayList<Producto> productos){
        super(context, -1, productos);
        this.context = context;
        this.productos = productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listitem_producto, parent, false);

        TextView nombreProducto = (TextView) rowView.findViewById(R.id.lblNombreProducto);
        nombreProducto.setText(productos.get(position).getNombre());

        TextView cantidadProducto = (TextView) rowView.findViewById(R.id.lblCantidadProducto);
        cantidadProducto.setText(productos.get(position).getCantidad());

        return rowView;
    }

    public void actualizarProductos(ArrayList<Producto> productos){
        this.productos.clear();
        this.productos.addAll(productos);
        this.notifyDataSetChanged();
    }
}