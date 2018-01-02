package com.example.eladoktarizo.monitoring_belajar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Elad Oktarizo on 27/10/2017.
 */

public class LaporanHarian extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_anak_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_laporanharian);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Laporan Harian");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //lv = (ListView)root.findViewById(R.id.lv_dataanak) ;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }
}
