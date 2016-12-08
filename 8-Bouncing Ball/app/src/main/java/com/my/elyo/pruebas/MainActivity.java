package com.my.elyo.pruebas;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener, View.OnTouchListener{


    private SensorManager sensorManager;
    private Sensor accelerometer;

    private ImageView imageview;
    private float equis;
    private float ye;
    private float delta;
    Display display;
    int width ;
    int height ;
    boolean activado=true;

    ImageView iv;
    Matrix mat;
    Bitmap bMap,bMapRotate ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //Remove title bar
            //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //Remove notification bar
            //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        catch (Exception ex){}
        setContentView(R.layout.activity_main);

        Drawable myDrawable = getResources().getDrawable(R.drawable.pelota);
        Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        delta = 40.0f;
        equis = 30.0f;
        ye = 30.0f;
        imageview = (ImageView)findViewById(R.id.pelotita);
        imageview.setImageBitmap(myLogo);
        imageview.setScaleType(ImageView.ScaleType.CENTER);

        RelativeLayout lay = (RelativeLayout)findViewById(R.id.relative);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        lay.setOnTouchListener(this);

        display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        iv = (ImageView)findViewById(R.id.pelotita);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                imageview.setX((int)(event.getX()-imageview.getWidth()/2));
                imageview.setY((int)(event.getY()-imageview.getHeight()/2));
                break;
            case MotionEvent.ACTION_MOVE:
                imageview.setX((int)(event.getX()-imageview.getWidth()/2));
                imageview.setY((int)(event.getY()-imageview.getHeight()/2));
                activado =false;
                break;
            case MotionEvent.ACTION_UP:
                equis = (event.getX()-imageview.getWidth()/2);
                ye = (event.getY()-imageview.getHeight()/2);
                activado=true;
                break;
            default:
                break;
        }
        return true;
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (accelerometer != null && activado) {

            iv.setRotation(3);




            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float deltax = 3.0f;
            float deltay = 3.0f;


            if (x < 0.0) {
                if (equis < width-imageview.getWidth()-30) {
                    equis += deltax;
                }
            }
            else if (x > 0) {
                if (equis > 30.0) {
                    equis -= deltax;
                }
            }

            if (y < 0) {
                if (ye > 30.0) {
                    ye -= deltay;
                }

            }
            else if (y > 0) {
                if (ye < height-imageview.getHeight()-30) {
                    ye += deltay;
                }

            }
            imageview.setX((int)(equis));
            imageview.setY((int)(ye));
        }
    }
}
