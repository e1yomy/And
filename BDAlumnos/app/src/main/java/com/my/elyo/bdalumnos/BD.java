package com.my.elyo.bdalumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * Created by elyo_ on 07/11/2016.
 */

public class BD extends SQLiteOpenHelper {

    public static final int version_basededatos=1;
    private String sqlCreate = "CREATE TABLE alumnos (ncontrol TEXT , nombre TEXT , apellido TEXT, PRIMARY KEY (ncontrol));";


    public BD(Context context) {
        super(context, "kardex", null, version_basededatos);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        Log.d("Tabla creada", "Tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertarDatos(BD bd, String num, String nom, String ape){
        SQLiteDatabase SQ=bd.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ncontrol",num);
        cv.put("nombre",nom);
        cv.put("apellido",ape);
        //cv.put("ncontrol","'"+num+"'");
        //cv.put("nombre","'"+nom+"'");
        //cv.put("apellido", "'"+ape+"'");
        long k = SQ.insert("alumnos",null,cv);
        Log.d("Insertar","Insertar datos");
    }
    public boolean insert(SQLiteDatabase db, String Nc, String nombre, String apellido){
        String params = "('" + Nc+ "','" + nombre + "','" + apellido  + "')";
        //String query = "INSERT INTO alumnos [(ncontrol, nombre, apellido)] VALUES " + params;
        String query = "INSERT INTO alumnos  VALUES " + params;
        try {
            db.execSQL(query);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Cursor getInfo(BD bd)
    {
        SQLiteDatabase SQ= bd.getReadableDatabase();
        String[] col= {"ncontrol","nombre","apellido"};
        //Cursor c =SQ.query("alumnos",col,null,null,null,null,null,null);
        //Cursor c=SQ.query(true,"alumnos",col,null,null,null,null,null,null,null);
        Cursor c= SQ.rawQuery("select * from alumnos",null);


        return c;
    }

}
