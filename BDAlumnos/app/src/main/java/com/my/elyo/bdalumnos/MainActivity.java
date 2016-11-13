package com.my.elyo.bdalumnos;

import android.app.Activity;
import android.content.Intent;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }


    public void insertar(View view){
        Intent i = new Intent(this,Registro.class);
        startActivity(i);
    }
    public void ver(View view){
        Intent i = new Intent(this,Lista.class);
        startActivity(i);
    }





    public static abstract class TableInfo implements BaseColumns {
        public static final String numerodecontrol="ncontrol";
        public static final String nombre="nombre";
        public static final String apellido="apellido";
        public static final String databasename="kardex";
        public static final String tablanombre="alumnos";
    }
}
