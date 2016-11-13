package com.my.elyo.bdalumnos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
=======
import android.support.v7.app.AppCompatActivity;
import android.view.View;
>>>>>>> master
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD

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
=======

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    Context c =this;
    ListView l;
    TextView t;
    Button b1,b2;
    boolean modif=false, elim=false;
    String nc="", no="", ap="";

>>>>>>> master

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
<<<<<<< HEAD
        cargarDatos();
        SetItemClic();
    }
=======
        CargarDatos();
        SetItemClic();
    }

>>>>>>> master
    private void SetItemClic() {
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemC, int position, long id) {
                TextView t=(TextView) itemC;
                nc=t.getText().toString().substring(0,8);
<<<<<<< HEAD
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
=======
                no=t.getText().toString().substring(12, dividir(t.getText().toString())[0]);
                ap=t.getText().toString().substring(dividir(t.getText().toString())[1]);
                Toast.makeText(getBaseContext(), nc/*+"\n"+no+"\n"+ap*/, Toast.LENGTH_SHORT).show();
                ///////////

            }
        });
    }
    public void CargarDatos() {
        ArrayList<String> ar = new ArrayList<>();

        AlumnosBBDD da = new AlumnosBBDD(c, "kardex", null, 1);
>>>>>>> master
        Cursor cr = da.vertodo(da.getReadableDatabase());

        try {
            if(cr.getCount()<1)
                t.setText("Lista de alumnos vacia.");
            if (cr.moveToFirst()) {
                do {
<<<<<<< HEAD
                    ar.add(cr.getString(0) + " - " + cr.getString(2)+ ", " + cr.getString(1));
=======
                    ar.add(cr.getString(0) + " - " + cr.getString(2) + ", " + cr.getString(1));
>>>>>>> master
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {
            t.setText(ex.getMessage());
        }
<<<<<<< HEAD

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
=======

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar);
        l.setAdapter(adapter);

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
    public void Accion(){
        AlumnosBBDD da = new AlumnosBBDD(c, "kardex", null, 1);
        if(modif)
        {

        }
        else if(elim)
        {
            da.delete(da.getWritableDatabase(),nc);

        }
        modif=false;
        elim=false;
        setTextB();
    }
    public int[] dividir(String s){
>>>>>>> master

        for(byte i=12;i<s.length();i++)
        {
            if(",".equals(s.toCharArray()[i]))
            {
                return new int[]{i-1,i+2};
            }
        }
        return null;
    }


}
