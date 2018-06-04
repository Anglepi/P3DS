package com.example.angel.appfarmacia;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText txtLoginUser, txtLoginPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Iniciar sesión");

        txtLoginUser = (EditText)findViewById(R.id.txtLoginUser);
        txtLoginPassword = (EditText)findViewById(R.id.txtLoginPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtLoginUser.getText().toString().isEmpty() || txtLoginPassword.getText().toString().isEmpty()){
                    FragmentManager fragmentManager = getFragmentManager();
                    DialogoAlerta dialogo = new DialogoAlerta();
                    dialogo.setMensajeAlerta("Por favor, rellena todos los campos para continuar");
                    dialogo.setTituloAlerta("Campos vacíos");
                    dialogo.show(fragmentManager, "CamposVacios");
                } else {
                    iniciarSesion(txtLoginUser.getText().toString(), txtLoginPassword.getText().toString());
                }
            }
        });
    }

    private void iniciarSesion(String usuario, String contrasena){

        HashMap jsonCredenciales = new HashMap();
        jsonCredenciales.put("email", usuario);
        jsonCredenciales.put("password", contrasena);
        postUsuario(jsonCredenciales);

    }

    private void postUsuario(HashMap credenciales){
        final String usuario = (String)credenciales.get("email");
        System.out.println("El usuario es " + usuario);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip_servidor) + getString(R.string.url_usuarios);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(credenciales), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                Bundle b = new Bundle();
                Usuario user = new Usuario(usuario);
                b.putSerializable("USUARIO",user);
                intent.putExtras(b);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error login");
                Log.e("Volley", error.toString());
                FragmentManager fragmentManager = getFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.setMensajeAlerta("Usuario y/o contraseña incorrectos");
                dialogo.setTituloAlerta("Error");
                dialogo.show(fragmentManager, "CamposLoginVacios");
            }
        });
        queue.add(jsonobj);
    }
}
