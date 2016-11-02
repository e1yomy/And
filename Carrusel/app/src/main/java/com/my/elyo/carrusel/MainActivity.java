package com.my.elyo.carrusel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String ruta;
    ImageSwitcher switcher;
    public byte estado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switcher = (ImageSwitcher) findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });
        


    }

    public byte sD(){
        String stadoSD = Environment.getExternalStorageState();
        if (stadoSD.equals(Environment.MEDIA_MOUNTED)) {
            return 1;
        }
        else if (stadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return 2;
        }
        else{
            return 0;
        }
}
