package com.my.elyo.misrutas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, Runnable{


    private GoogleMap m;
    Context contextR = this;
    public View vi;
    ArrayList<LatLng> lista=new ArrayList<>();
    B b;
    int can;
    Thread h1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        b= new B(contextR,"gps");
        hacerlista();
        h1=new Thread(this);
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
        h1.start();
    }
    public void hacerlista(){
        Cursor cr = b.selectAll(b.getReadableDatabase());

        try {
            if (cr.moveToFirst()) {
                lista.clear();
                do {
                    lista.add(new LatLng(cr.getDouble(1),cr.getDouble(2)));
                } while (cr.moveToNext());
            }
        } catch (Exception ex) {

        }
    }
    public void mostrarlista(){
        //hacer variable para el primer elemento
        //hacer variable para el siguiente elemento
        //si la lista es diferente de 0
        m.clear();
        can=lista.size();
        if(lista.size()>0) {
            for (int i = 0; i < lista.size() - 1; i++) {
                //if(i==0)
                //primerpunto = lista.get(i);
                //segundo = lista.get(i+1);
                //else
                //
                //
                //
                //
                m.addMarker(new MarkerOptions().position(lista.get(i)));
                m.animateCamera(CameraUpdateFactory.newLatLngZoom(lista.get(i),18));

            }
        }
    }

    @Override
    public void run() {
        while(true){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        hacerlista();
                        if(can<lista.size()) {
                            mostrarlista();
                        }
                    }
                    catch (Exception ex){}
                }
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
