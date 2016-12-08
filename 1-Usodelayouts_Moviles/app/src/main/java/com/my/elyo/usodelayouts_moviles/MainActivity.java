package com.my.elyo.usodelayouts_moviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
    }
    public void lay1(View view) {
        // Do something in response to button
        //button1.setText("a");
        Intent intent = new Intent(this,Activity1.class);
        startActivity(intent);
    }
    public void lay2(View view) {
        // Do something in response to button
        //button1.setText("s");
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }
    public void lay3(View view) {
        // Do something in response to button
        //button1.setText("d");
        Intent intent = new Intent(this,Activity3.class);
        startActivity(intent);
    }
}
