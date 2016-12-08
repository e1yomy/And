package com.my.elyo.asteroides_moviles;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AcercaDe extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);

    }
    public void cerrarAcercaDe(View vi)
    {
        finish();
    }

}
