package com.my.elyo.bdalumnos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLDataException;


/**
 * Created by elyo_ on 07/11/2016.
 */

public class BD extends SQLiteOpenHelper {

    public static String numero="";
    public static String error="";
    public static String sel="";

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

    public boolean insert(SQLiteDatabase db, String Nc, String nombre, String apellido){
        String params = "('" + Nc+ "','" + nombre + "', '" + apellido  + "')";
        String query = "INSERT INTO alumnos  VALUES " + params;
        try {
            db.execSQL(query);
            return true;
        }
        catch (Exception e){
            String es=e.getMessage();
            return false;
        }
    }

    public boolean actualizar(SQLiteDatabase d,String[] s)
    {
        try{
            d.execSQL("UPDATE alumnos SET ncontrol='"+s[0]+"' WHERE ncontrol='"+numero+"'");
            d.execSQL("UPDATE alumnos SET nombre='"+s[1]+"' WHERE ncontrol='"+s[0]+"'");
            d.execSQL("UPDATE alumnos SET apellido='"+s[2]+"' WHERE ncontrol='"+s[0]+"'");
                    return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public Cursor vertodo(SQLiteDatabase db){
        String sqlQuery = "SELECT * FROM Alumnos;";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        return cursor;
    }

    public boolean borrar(SQLiteDatabase db,String nc){
        String qu="delete from alumnos where ncontrol = '"+nc+"'";
        try {
            db.execSQL(qu);
            return true;
            }
        catch (Exception e)
        {
            error=e.getMessage();
            return false;
        }

    }
}
