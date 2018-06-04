package com.example.angel.appfarmacia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {
    Button btnInicioSesion, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        this.setTitle("Farmacia Online");

        btnInicioSesion = (Button)findViewById(R.id.btnInicioSesion);
        btnRegistro = (Button)findViewById(R.id.btnRegistro);

        btnInicioSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(InicioActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(InicioActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
