package com.my.elyo.gmaps;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements Runnable, OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
    private static final long DISTANCIA_MIN = 5; // 5 metros
    LatLng tec = new LatLng(24.119048, -110.309053); //Tec
    LatLng ubi; //ubicacion
    Criteria criterio;

    private LocationManager manejador;
    Location localizacion;
    private String proveedor;
    public View vi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        vi=new View(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
        criterio = new Criteria();
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);

        proveedor = manejador.getBestProvider(criterio, true);
        localizacion = manejador.getLastKnownLocation(proveedor);
        ubi = new LatLng(localizacion.getLatitude(), localizacion.getLongitude());
        Thread hilo = new Thread(this);
        hilo.start();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(ubi).title("Aqui"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubi));
    }
/*
* Circle circle = mMap.addCircle(new CircleOptions()
                                .center(mMap.getCameraPosition().target)
                                .radius(10000)
                                .strokeColor(Color.RED)
                                .fillColor(Color.BLUE));
* */
    public void moveCamera(View view) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tec, 10));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tec, 15));
        mMap.addMarker(new MarkerOptions().position(tec).title("Tec").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

    }

    public void animateCamera(View view) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubi, 10));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubi, 18));

    }

    public void addMarker(View view) {
        mMap.addMarker(new MarkerOptions().position(
                mMap.getCameraPosition().target).anchor(0.3f, 0.3f).icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            proveedor = manejador.getBestProvider(criterio, true);
            localizacion = manejador.getLastKnownLocation(proveedor);
            ubi = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(ubi).icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        }
        catch (Exception ex){

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    int pos=1;
    public void run() {

        while (true) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        proveedor = manejador.getBestProvider(criterio, true);
                        localizacion = manejador.getLastKnownLocation(proveedor);
                        if(ubi!=new LatLng(localizacion.getLatitude(),localizacion.getLongitude())) {
                            ubi = new LatLng(localizacion.getLatitude(), localizacion.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(ubi).title(""+pos+"").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                            animateCamera(vi);
                        }

                    }
                    catch (Exception ex){}
                }
            });

            try {
                Thread.sleep(2*1000);

            } catch (Exception e) {

            }
        }
    }
}
