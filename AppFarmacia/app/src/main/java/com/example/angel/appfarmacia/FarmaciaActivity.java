package com.example.angel.appfarmacia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmaciaActivity extends AppCompatActivity {
    private ArrayList<Producto> productos = new ArrayList<>();
    private ListView lstProductos;
    private String nombreFarmacia;
    private Usuario usuario;
    private Button btnCarro;
    private AdaptadorProductos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);

        Bundle b = this.getIntent().getExtras();

        usuario = (Usuario) b.getSerializable("USUARIO");

        nombreFarmacia = b.getString("NOMBRE");
        this.setTitle(nombreFarmacia);



        inicializarInventario();

        adaptador = new AdaptadorProductos(this, productos);
        lstProductos = (ListView)findViewById(R.id.lstProductos);
        lstProductos.setAdapter(adaptador);



        btnCarro = (Button)findViewById(R.id.btnAccederCarro);
        btnCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmaciaActivity.this, CarroActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("USUARIO", usuario);
                b.putString("NOMBRE", nombreFarmacia);
                intent.putExtras(b);
                startActivity(intent);
            }
        });



        lstProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Producto producto = (Producto) parent.getItemAtPosition(position);

                Intent intent = new Intent(FarmaciaActivity.this, ProductoActivity.class);
                Bundle b = new Bundle();
                b.putString("NOMBREFARMACIA", nombreFarmacia);
                b.putSerializable("PRODUCTO", producto);
                b.putSerializable("USUARIO", usuario);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void inicializarInventario(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip_servidor) + getString(R.string.url_productos);


        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("OBTENIDO DE LOS PRODUCTOS: ");
                System.out.println(response.toString());
                try {
                    JSONArray prods = response.getJSONArray("productos");
                    actualizarInventario(prods);
                } catch(Exception e){
                    System.out.println("Excepcion de obtencion de productos");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("HA FALLADO LA OBTENCION DE PRODUCTOS");
            }
        });
        queue.add(jsonobj);

    }

    private void actualizarInventario(JSONArray prods){
        productos = new ArrayList<>();
        for(int i=0 ; i<prods.length() ; i++){
            try {
                JSONObject p = prods.getJSONObject(i);
                productos.add(new Producto(p.getString("nombre"), p.getInt("cantidad"), p.getDouble("precio"), p.getInt("ID")));
            } catch (Exception e){
                System.out.println("EXCEPCION ACTUALIZAR INVENTARIO");
            }

        }
        adaptador.actualizarProductos(productos);
    }

}
