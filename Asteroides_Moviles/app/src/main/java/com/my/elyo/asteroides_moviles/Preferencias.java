package com.my.elyo.asteroides_moviles;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by elyo_ on 21/09/2016.
 */

public class Preferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);

    }
}
