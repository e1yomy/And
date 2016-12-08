package com.my.elyo.asteroides_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView titulo;
    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo=(TextView)findViewById(R.id.Titulo);
        Animation an1= AnimationUtils.loadAnimation(this,R.anim.giro_con_zoom);
        titulo.startAnimation(an1);
        Animation an2= AnimationUtils.loadAnimation(this,R.anim.aparecer);
        button1=(Button)findViewById(R.id.Button01);
        button1.startAnimation(an2);
        button2=(Button)findViewById(R.id.Button02);
        button2.startAnimation(an2);
        button3=(Button)findViewById(R.id.Button03);
        button3.startAnimation(an2);
        button4=(Button)findViewById(R.id.Button04);
        button4.startAnimation(an2);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //Eventos del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.acercaDe:
                lanzarAcercaDe(null);
                break;
            case R.id.action_settings:
                lanzarPreferencias(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
*/


    public void lanzarAcercaDe(View view) {
        Intent i =new Intent(this,AcercaDe.class);
        startActivity(i);
    }
    public void lanzarPreferencias(View view) {
        Intent i =new Intent(this,Preferencias.class);
        startActivity(i);
    }
    public void salir(View view)
    {
        finish();
    }
    public void lanzarJuego(View view) {
        Intent i =new Intent(this,Juego.class);
        startActivity(i);
    }
}
