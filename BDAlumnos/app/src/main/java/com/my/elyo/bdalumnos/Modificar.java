package com.my.elyo.bdalumnos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    EditText e1;
    EditText e2;
    EditText e3;
    TextView t;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        e1 = (EditText)findViewById(R.id.editText4);
        e2 = (EditText)findViewById(R.id.editText5);
        e3 = (EditText)findViewById(R.id.editText6);
        t = (TextView)findViewById(R.id.textView6);
        t.setText(BD.sel);
    }


    public void Limpiar1(View view){
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e1.requestFocus();
    }

    public void Actualizacion(View view) {
        String[] s=new String[3];
        s[0]= e1.getText().toString();
        s[1]= e2.getText().toString();
        s[2]= e3.getText().toString();
        if(s[0].length()!=8)
        {
            Toast.makeText(getBaseContext(), "Numero de control no valido", Toast.LENGTH_SHORT).show();
            return;
        }
        if(s[1].length()<3 )
        {
            Toast.makeText(getBaseContext(), "Verificar nombres ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(s[2].length()<3 )
        {
            Toast.makeText(getBaseContext(), "Verificar apellidos ", Toast.LENGTH_SHORT).show();
            return;
        }
        for(byte i=0;i<s.length;i++) {
            if (s[i].equals("")) {
                Toast.makeText(getBaseContext(), "Verificar datos ingresdos", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        BD b = new BD(context);
        //b.InsertarDatos(b, s[0], s[1], s[2]);
        b.actualizar(b.getWritableDatabase(),s);

        //b.insert(b.getWritableDatabase(),s[0], s[1], s[2]);
        Toast.makeText(getBaseContext(),"Alumno actualizado" , Toast.LENGTH_SHORT).show();


        //finish();
    }
}
