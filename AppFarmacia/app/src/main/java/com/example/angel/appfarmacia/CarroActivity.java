package com.example.angel.appfarmacia;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CarroActivity extends AppCompatActivity {
    private Button btnComprar, btnVaciar;
    private Usuario usuario;
    private ListView lstCarro;
    private AdaptadorCarro adaptador;
    private TextView lblPrecio;
    private String nombreFarmacia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        Bundle b = this.getIntent().getExtras();
        usuario = (Usuario)b.getSerializable("USUARIO");
        nombreFarmacia = b.getString("NOMBRE");

        btnComprar = (Button)findViewById(R.id.btnConfirmarCompra);
        btnVaciar = (Button)findViewById(R.id.btnVaciarCarro);
        lblPrecio = (TextView)findViewById(R.id.lblPrecioCarro);

        lblPrecio.setText(usuario.getSubtotalCarro() + " €");

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //realizarCompra(usuario.getProductosCarro());
                FragmentManager fragmentManager = getFragmentManager();
                final DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.setMensajeAlerta("Su compra se ha realizado con éxito, muchas gracias");
                dialogo.setTituloAlerta("Gracias");
                dialogo.show(fragmentManager, "CompraRealizada");

                final Intent intent = new Intent(CarroActivity.this, FarmaciaActivity.class);

                new CountDownTimer(1500, 1000) {

                    public void onTick(long millisUntilFinished) {  }

                    public void onFinish() {
                        dialogo.dismiss();
                        usuario.vaciarCarro();
                        lblPrecio.setText(usuario.getSubtotalCarro() + " €");
                        adaptador.actualizarCarro(usuario.getProductosCarro());
                        Bundle b = new Bundle();
                        b.putSerializable("USUARIO", usuario);
                        b.putString("NOMBRE", nombreFarmacia);
                        intent.putExtras(b);
                        startActivity(intent);
                    }

                }.start();
            }
        });

        btnVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario.vaciarCarro();
                lblPrecio.setText(usuario.getSubtotalCarro() + " €");
                adaptador.actualizarCarro(usuario.getProductosCarro());

                final Intent intent = new Intent(CarroActivity.this, FarmaciaActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("USUARIO", usuario);
                b.putString("NOMBRE", nombreFarmacia);
                intent.putExtras(b);
                new CountDownTimer(1500, 1000) {

                    public void onTick(long millisUntilFinished) {  }

                    public void onFinish() {
                        startActivity(intent);
                    }

                }.start();
            }
        });

        adaptador = new AdaptadorCarro(this, usuario.getProductosCarro());
        lstCarro = (ListView)findViewById(R.id.lstCarro);
        lstCarro.setAdapter(adaptador);

    }

    private void realizarCompra(ArrayList<Producto> productos){
        //PK, Precio, FK, UK, idORDER
        HashMap<String, Integer> order = new HashMap();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip_servidor) + getString(R.string.url_orders);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(order), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Recibida respuesta order");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error order");
            }
        });
        url = getString(R.string.ip_servidor) + getString(R.string.url_productos);
        JsonObjectRequest jsonobj2 = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(order), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Recibida respuesta order");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error order");
            }
        });
        queue.add(jsonobj);
        queue.add(jsonobj);
    }
}
