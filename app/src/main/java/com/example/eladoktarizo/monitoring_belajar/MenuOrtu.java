package com.example.eladoktarizo.monitoring_belajar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuOrtu extends AppCompatActivity {

    Button dataanakortu, monitoring, profilguru, tentang, logout;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ortu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menuortu);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Menu Orang Tua");

            dataanakortu = (Button) findViewById(R.id.dataanakortu);
            monitoring = (Button) findViewById(R.id.monitoring);
            profilguru = (Button) findViewById(R.id.profilguru);
            tentang = (Button) findViewById(R.id.tentang);
            logout = (Button) findViewById(R.id.logout);

            logout.setOnClickListener((View.OnClickListener) this) ;

            dataanakortu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), DataAnak.class);
                    startActivity(i);
                }
            });
            monitoring.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), Monitoring.class);
                    startActivity(i);
                }
            });
            profilguru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), ProfilGuru.class);
                    startActivity(i);
                }
            });
            tentang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), TentangAplikasi.class);
                    startActivity(i);
                }
            });
        }
    }
        public void logout (View view){
            SharedPreferences sharedPreferences = getSharedPreferences(LoginOrtu.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }


}


