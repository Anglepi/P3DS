package com.example.angel.appfarmacia;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProductoActivity extends AppCompatActivity {
    private TextView nombreProducto, precio;
    private Button btnAnadir;
    private Usuario usuario;
    private String nombreFarmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        Bundle b = this.getIntent().getExtras();

        nombreFarmacia = b.getString("NOMBREFARMACIA");
        this.setTitle(nombreFarmacia);
        final Producto producto = (Producto) b.getSerializable("PRODUCTO");
        usuario = (Usuario) b.getSerializable("USUARIO");

        nombreProducto = (TextView)findViewById(R.id.lblNombreProductoExtendido);
        precio = (TextView)findViewById(R.id.lblPrecioProducto);


        nombreProducto.setText(producto.getNombre());
        precio.setText(producto.getPrecio() + "€");

        btnAnadir = (Button)findViewById(R.id.btnAnadir);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                final DialogoAlerta dialogo = new DialogoAlerta();
                if(producto.getDisponibilidad()) {
                    usuario.addProductoCarro(producto);

                    dialogo.setMensajeAlerta("El producto ha sido añadido al carro. Volveras a la lista de productos en unos instantes");
                    dialogo.setTituloAlerta("Éxito");
                    dialogo.show(fragmentManager, "ProductoAñadido");

                    final Intent intent = new Intent(ProductoActivity.this, FarmaciaActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("USUARIO", usuario);
                    b.putString("NOMBRE", nombreFarmacia);
                    intent.putExtras(b);

                    new CountDownTimer(1500, 1000) {

                        public void onTick(long millisUntilFinished) {  }

                        public void onFinish() {
                            startActivity(intent);
                            dialogo.dismiss();
                        }

                    }.start();
                } else {
                    dialogo.setMensajeAlerta("El producto que desea añadir está agotado");
                    dialogo.setTituloAlerta("Agotado");
                    dialogo.show(fragmentManager, "ProductoAgotado");
                }
            }
        });





    }
}
