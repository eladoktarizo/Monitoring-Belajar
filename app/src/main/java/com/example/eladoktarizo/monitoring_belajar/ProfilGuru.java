package com.example.eladoktarizo.monitoring_belajar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Elad Oktarizo on 27/10/2017.
 */

public class ProfilGuru extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataguru);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dataguru);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profil Guru");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //lv = (ListView)root.findViewById(R.id.lv_dataanak) ;
        }
    }
}
