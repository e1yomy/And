package com.my.elyo.communicacionactividades_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public EditText txtNombre;
    public TextView lblResultado;
    public String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtNombre=(EditText)findViewById(R.id.txtNombre);
        lblResultado=(TextView)findViewById(R.id.lblResultado);
    }

    public void Verificar(View view)
    {
        nom= txtNombre.getText().toString();
        if(!nom.equals("")) {
            Intent i = new Intent(this, VerificarActivity.class);
            i.putExtra("nombre", nom);
            startActivityForResult(i, 1234);
            lblResultado.setText("Resultado");
        }
        else
        {
            lblResultado.setText("Introducir un nombre");
        }
    }

    public void Limpiar(View view)
    {
        txtNombre.setText("");
        lblResultado.setText("");
    }

    @Override
    protected void onActivityResult(int codigo,int resultado,Intent i)
    {
        if(codigo==1234 && resultado==RESULT_OK)
        {
            String res=i.getExtras().getString("resultado");
            if(res.equals("1"))
            {
                lblResultado.setText("Resultado: Aceptado");
            }
            else{
                lblResultado.setText("Resultado: Rechazado");
            }
        }
    }



}
