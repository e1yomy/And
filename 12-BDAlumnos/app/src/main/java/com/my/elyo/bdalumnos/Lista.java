package com.my.elyo.bdalumnos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lista extends Activity {
    int accion;
    //1 consulta
    //2 actualizacion
    //3 eliminacion
    Context c =this;
    ListView l;
    TextView t;
    boolean modif=false, elim=false;
    Button b1,b2;
    String nc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista);
        l=(ListView)findViewById(R.id.listView);
        t=(TextView)findViewById(R.id.textView4);
        b1=(Button)findViewById(R.id.button5);
        b2=(Button)findViewById(R.id.button6);
        cargarDatos();
        SetItemClic();
    }
    private void SetItemClic() {
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemC, int position, long id) {
                TextView t=(TextView) itemC;
                nc=t.getText().toString().substring(0,8);
                BD.sel=t.getText().toString();
                BD.numero=nc;
                //Toast.makeText(getBaseContext(), nc, Toast.LENGTH_SHORT).show();
                Accion();
            }
        });
    }
    private void Accion() {
        if(elim)
        {
            BD b = new BD(getBaseContext());
            if(b.borrar(b.getWritableDatabase(),nc))
                cargarDatos();
            else
                Toast.makeText(getBaseContext(),"Algo ha ido mal, contacte al administrador." , Toast.LENGTH_SHORT).show();
        }
        else if (modif){
            Intent i = new Intent(this,Modificar.class);
            startActivity(i);
        }
        modif=false;
        elim=false;
        setTextB();
    }
    public void cargarDatos(){
        ArrayList<String> ar = new ArrayList<>();
        BD da = new BD(c);
        Cursor cr = da.vertodo(da.getReadableDatabase());

        try {
            if(cr.getCount()<1)
                t.setText("Lista de alumnos vacia.");
            if (cr.moveToFirst()) {
                do {
                    ar.add(cr.getString(0) + " - " + cr.getString(2)+ ", " + cr.getString(1));
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {
            t.setText(ex.getMessage());
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar);
        l.setAdapter(adapter);
    }
    public void Modificar(View view){
        if(!modif)
        {
            Toast.makeText(getBaseContext(), "Seleccione el elemento que quiere modificar o presione de nuevo para cancelar.", Toast.LENGTH_SHORT).show();
            modif=true;
            elim=false;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Modificacion cancelada.", Toast.LENGTH_SHORT).show();
            modif=false;
        }
        setTextB();
    }
    public void Eliminar(View view){
        if(!elim)
        {
            Toast.makeText(getBaseContext(), "Seleccione el elemento que quiere eliminar o presione de nuevo para cancelar.", Toast.LENGTH_SHORT).show();
            elim=true;
            modif=false;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Eliminacion cancelada.", Toast.LENGTH_SHORT).show();
            elim=false;
        }
        setTextB();
    }
    public void setTextB(){
        if (!modif)
            b1.setText("Modificar");
        else
            b1.setText("Cancelar");
        if(!elim)
            b2.setText("Eliminar");
        else
            b2.setText("Cancelar");
    }

    @Override
    public void onResume(){
        super.onResume();
        cargarDatos();
    }



}
