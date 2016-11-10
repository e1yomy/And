package com.my.elyo.bdalumnos;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

/**
 * Created by elyo_ on 08/11/2016.
 */

public class AlumnosBBDD extends SQLiteOpenHelper {
    public static String numerodecontrol="";
    public static String nombre="";
    public static String apellido="";
    private String sqlCreate = "CREATE TABLE Alumnos (ncontrol TEXT, nombre TEXT, apellidos TEXT)";

    public AlumnosBBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insert(SQLiteDatabase db, String Nc, String nombre, String apellido){
        String params = "('" + nombre + "','" + apellido + "','" + Nc + "')";
        String query = "INSERT INTO Alumnos (ncontrol, nombre, apellido) VALUES " + params;
        try {
            db.execSQL(query);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean delete(SQLiteDatabase db, String nc){
        try{
            //db.execSQL("delete from alumnos where ncontrol='"+nc+"';");
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

    public String[] getNc(){
        return new String[]{numerodecontrol,nombre,apellido};
    }
    public void setNc(String s, String a, String d) {
        numerodecontrol = s;
        nombre = a;
        apellido = d;
    }
}
