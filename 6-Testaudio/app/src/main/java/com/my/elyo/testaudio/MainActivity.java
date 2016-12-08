package com.my.elyo.testaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer cancion;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageButton stopButton;
    private Button findsongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageButton) findViewById(R.id.playButton);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        stopButton = (ImageButton) findViewById(R.id.stopButton);
        cancion = MediaPlayer.create(this, R.raw.audio);
    }
    public void play(View view)
    {
        if (cancion != null)
            cancion.start();
    }
    public void pause(View view)
    {
        if (cancion != null)
            cancion.pause();
    }
    public void stop(View view)
    {
        if (cancion != null) {
            cancion.stop();

        }
    }
    private void initializeMediaPlayer() {
        try {
            cancion = new MediaPlayer();
            cancion.setDataSource("http://server3.pianosociety.com/protected/bach-bwv772-stahlbrand.mp3");
            cancion.prepare();
        } catch (IllegalArgumentException e) {
            // Mostramos mensaje en caso de error.
            Toast.makeText(getApplicationContext(), "URL no encontrada", Toast.LENGTH_SHORT);
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
