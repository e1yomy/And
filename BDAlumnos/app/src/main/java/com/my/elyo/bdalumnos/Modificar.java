package com.my.elyo.bdalumnos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar extends Activity {

    EditText e1;
    EditText e2;
    EditText e3;
    TextView t;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_modificar);


        e1 = (EditText)findViewById(R.id.editText4);
        e2 = (EditText)findViewById(R.id.editText5);
        e3 = (EditText)findViewById(R.id.editText6);
        t = (TextView)findViewById(R.id.textView6);
        t.setText(BD.sel);
        BD b=new BD(this);
        String[] c = b.verClicado(b.getReadableDatabase());
        e1.setText(c[0]);
        e2.setText(c[1]);
        e3.setText(c[2]);
        Toast.makeText(getBaseContext(), "Para cancelar, presionar el boton de regresar.", Toast.LENGTH_SHORT).show();

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
        BD b = new BD(context);
        if(b.actualizar(b.getWritableDatabase(),s)) {
            finish();
        }
        else
            Toast.makeText(getBaseContext(),"Algo ha ido mal, contacte al administrador o verifique los datos." , Toast.LENGTH_SHORT).show();
    }
}
