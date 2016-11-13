package com.my.elyo.misrutas;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    LatLng ubi; //ubicacion
    Criteria criterio;
    private GoogleMap m;
    private LocationManager manejador;
    Location localizacion;
    private String proveedor;
    Context contextR = this;
    public View vi;
    ArrayList<LatLng> lista=new ArrayList<>();
    B b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        b= new B(contextR,"gps");
        hacerlista();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        m=googleMap;
        m.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            m.getUiSettings().setZoomControlsEnabled(true);
            m.getUiSettings().setCompassEnabled(true);
        }
        if(lista.size()>0)
            mostrarlista();


    }
    public void hacerlista(){
        Cursor cr = b.selectAll(b.getReadableDatabase());
        try {
            if (cr.moveToFirst()) {
                do {
                    lista.add(new LatLng(cr.getDouble(1),cr.getDouble(2)));
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {

        }
    }
    public void mostrarlista(){
        for(int i=0;i< lista.size();i++)
        {
            m.addMarker(new MarkerOptions().position(lista.get(i)));
        }
    }

}
