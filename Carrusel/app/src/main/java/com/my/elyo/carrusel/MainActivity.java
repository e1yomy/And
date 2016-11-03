package com.my.elyo.carrusel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String ruta;
    public byte estado;
    String[] ms= {"Tarjeta SD montada y lista","Tarjeta SD montada con permisos de solo lectura","Sin tarjeta SD"};
    File f;


    /////////Camara////////////
    static final int REQUEST_IMAGE_CAPTURE = 1;


    /////////Camara////////////



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msj();
        dirSD();

    }
    String fol="F";
    public void dirSD(){
        if(estado==0) {
            Toast toast;
            f = new File(Environment.getExternalStorageDirectory(),fol);
            if (!f.exists()) {
                if(f.mkdirs())
                     toast = Toast.makeText(getApplicationContext(), "Ruta creada", Toast.LENGTH_SHORT);
                else
                    toast = Toast.makeText(getApplicationContext(), "Ruta no creada", Toast.LENGTH_SHORT);
            }
            else
            {
                toast = Toast.makeText(getApplicationContext(), "Directorio ya existente", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }
    public byte haySD() {
        String stadoSD = Environment.getExternalStorageState();
        if (stadoSD.equals(Environment.MEDIA_MOUNTED)) {
            return 0;
        } else if (stadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return 1;
        } else {
            return 2;
        }
    }
    public void msj(){
        estado=haySD();
        Toast toast = Toast.makeText(getApplicationContext(), ms[estado], Toast.LENGTH_SHORT);
        toast.show();
    }


    /////////Camara////////////
   public void boton(View view){
       Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
           startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

       }
   }

    String mCurrentPhotoPath= Environment.getExternalStorageDirectory()+File.separator+"F";

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }



    /////////Camara////////////
}
