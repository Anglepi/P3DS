package com.example.angel.appfarmacia;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.CountDownTimer;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class RegistroActivity extends AppCompatActivity {
    private EditText txtUser, txtPassword, txtRepeatPassword;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.setTitle("Regístrate");

        txtUser = (EditText)findViewById(R.id.txtRegistroUser);
        txtPassword = (EditText)findViewById(R.id.txtRegistroPassword);
        txtRepeatPassword = (EditText)findViewById(R.id.txtRegistroRepitePassword);

        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario(txtUser.getText().toString(), txtPassword.getText().toString(), txtRepeatPassword.getText().toString());
            }
        });
    }

    private void registrarUsuario(String user, String password, String repeatedPassword){
        final DialogoAlerta dialogo = new DialogoAlerta();


        if(user.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()){
            dialogo.setMensajeAlerta("Por favor, rellena todos los campos para continuar");
            dialogo.setTituloAlerta("Campos vacíos");
        } else if(!password.equals(repeatedPassword)){
            dialogo.setMensajeAlerta("Ambas contraseñas deben coincidir");
            dialogo.setTituloAlerta("Contraseñas diferentes");
        } else{
            HashMap nuevoUsuario = new HashMap();
            nuevoUsuario.put("nombre", "");
            nuevoUsuario.put("nick", "");
            nuevoUsuario.put("password", password);
            nuevoUsuario.put("rol", "Usuario");
            nuevoUsuario.put("email", user);
            postUsuario(nuevoUsuario);
            dialogo.setMensajeAlerta("¡Bienvenido! Ahora ya puedes iniciar sesión. Serás redirigido en 5 segundos");
            dialogo.setTituloAlerta("Registro completado");
            final Intent intent = new Intent(RegistroActivity.this, InicioActivity.class);

            new CountDownTimer(2000, 1000) {

                public void onTick(long millisUntilFinished) {  }

                public void onFinish() {
                    startActivity(intent);
                    dialogo.dismiss();
                }

            }.start();
        }

        FragmentManager fragmentManager = getFragmentManager();
        dialogo.show(fragmentManager, "FalloRegistro");
    }

    private void postUsuario(HashMap credenciales){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip_servidor) + getString(R.string.url_usuarios);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(credenciales), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Recibida respuesta registro");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error registro");
                Log.e("Volley", error.toString());
                final DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.setMensajeAlerta("El nombre de usuario establecido ya está siendo usado por otra persona");
                dialogo.setTituloAlerta("Usuario no disponible");
                FragmentManager fragmentManager = getFragmentManager();
                dialogo.show(fragmentManager, "FalloRegistro");
            }
        });
        queue.add(jsonobj);
    }
}
