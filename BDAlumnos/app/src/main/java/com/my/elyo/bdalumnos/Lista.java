package com.my.elyo.bdalumnos;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Lista extends AppCompatActivity {
    int accion;
    //1 consulta
    //2 actualizacion
    //3 eliminacion
    Context c =this;
    ListView l;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        l=(ListView)findViewById(R.id.listView);
        t=(TextView)findViewById(R.id.textView4);
        BD da= new BD(c);
        Cursor cr = da.getInfo(da);
        //Cursor cr = da.vertodo(da);
        String nc="";
        String no="";
        String ap="";
        String it0="";
        String it1="";
        //t.setText(cr.toString());

        try {
            if (cr.moveToFirst()) {
                do {
                    nc = cr.getString(0);
                    no = cr.getString(1);
                    ap = cr.getString(2);

                    it0 = nc;
                    it1 = no + " " + ap;
                    t.setText(t.getText().toString() + "   " + it0 + " - " + it1);

                } while (cr.moveToNext());
            }
        }
        catch(Exception ex){
            t.setText(ex.getMessage());
        }
    }





}
