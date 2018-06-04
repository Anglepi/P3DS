package com.example.angel.appfarmacia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogoAlerta extends DialogFragment {
    private String mensajeAlerta, tituloAlerta, textoBoton;

    public DialogoAlerta(){
        mensajeAlerta = "Esto es un mensaje de alerta";
        tituloAlerta = "Información";
        textoBoton = "OK";
    }

    public void setMensajeAlerta(String m){
        mensajeAlerta = m;
    }

    public void setTituloAlerta(String t){
        tituloAlerta = t;
    }

    public void setTextoBoton(String b){
        textoBoton = b;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(mensajeAlerta).setTitle(tituloAlerta);
        builder.setPositiveButton(textoBoton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
