package com.my.elyo.communicacionactividades_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VerificarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        Bundle extras = getIntent().getExtras();
        String nombre= (String)extras.getString("nombre");

        TextView lblHola = (TextView)findViewById(R.id.lblHola);
        EditText txtNombre=(EditText)findViewById(R.id.txtNombre);
        nombre = lblHola.getText().toString()+", "+nombre;
        lblHola.setText(nombre);
    }


    public void Aceptar(View v){
        Intent in = new Intent();
        in.putExtra("resultado","1");
        setResult(RESULT_OK,in);
        finish();

    }
    public void Rechazar(View v){
        Intent in = new Intent();
        in.putExtra("resultado","0");
        setResult(RESULT_OK,in);
        finish();
    }
}
