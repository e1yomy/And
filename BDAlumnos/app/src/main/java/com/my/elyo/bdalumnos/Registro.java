package com.my.elyo.bdalumnos;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends Activity {

    EditText e1;
    EditText e2;
    EditText e3;
    public Context contextR=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro);

        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
    }
    public void Registro(View v) {
        String[] s = new String[3];
        s[0] = e1.getText().toString();
        s[1] = e2.getText().toString();
        s[2] = e3.getText().toString();
        if (s[0].length() != 8) {
            Toast.makeText(getBaseContext(), "Numero de control no valido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s[1].length() < 3) {
            Toast.makeText(getBaseContext(), "Verificar nombres ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (s[2].length() < 3) {
            Toast.makeText(getBaseContext(), "Verificar apellidos ", Toast.LENGTH_SHORT).show();
            return;
        }
        for (byte i = 0; i < s.length; i++) {
            if (s[i].equals("")) {
                Toast.makeText(getBaseContext(), "Verificar datos ingresdos", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        BD b = new BD(contextR);
        //b.InsertarDatos(b, s[0], s[1], s[2]);
<<<<<<< HEAD
        if (!b.insert(b.getWritableDatabase(), s[0], s[1], s[2]))
            Toast.makeText(getBaseContext(), "Alumno ya existente.", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getBaseContext(), "Alumno agregado.", Toast.LENGTH_SHORT).show();
            Limpiar(new View(contextR));
        }
=======
        b.insert(b.getWritableDatabase(),s[0], s[1], s[2]);
        Toast.makeText(getBaseContext(),"Alumno Agregado" , Toast.LENGTH_SHORT).show();
>>>>>>> master

    }
    public void Limpiar(View view){
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e1.requestFocus();
    }
}
