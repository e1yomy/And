package com.my.elyo.bdalumnos;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    Context c =this;
    ListView l;
    TextView t;
    Button b1,b2;
    boolean modif=false, elim=false;
    String nc="", no="", ap="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        l=(ListView)findViewById(R.id.listView);
        t=(TextView)findViewById(R.id.textView4);
        b1=(Button)findViewById(R.id.button5);
        b2=(Button)findViewById(R.id.button6);
        CargarDatos();
        SetItemClic();
    }

    private void SetItemClic() {
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemC, int position, long id) {
                TextView t=(TextView) itemC;
                nc=t.getText().toString().substring(0,8);
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
        Cursor cr = da.vertodo(da.getReadableDatabase());

        try {
            if (cr.moveToFirst()) {
                do {
                    ar.add(cr.getString(0) + " - " + cr.getString(2) + ", " + cr.getString(1));
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {
            t.setText(ex.getMessage());
        }

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
