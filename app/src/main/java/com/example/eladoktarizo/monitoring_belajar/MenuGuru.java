package com.example.eladoktarizo.monitoring_belajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuGuru extends AppCompatActivity {

    Button dataanak, laporanharian, profil, tentang, logout;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_guru_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_menuguru);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Menu Guru");

            dataanak = (Button) findViewById(R.id.dataanak);
            laporanharian = (Button) findViewById(R.id.laporanharian);
            profil = (Button) findViewById(R.id.profil);
            tentang = (Button) findViewById(R.id.tentang);
            logout = (Button) findViewById(R.id.logout);

            dataanak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), DataAnak.class);
                    startActivity(i);

                }
            });
            laporanharian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), LaporanHarian.class);
                    startActivity(i);

                }
            });
            profil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), Profil.class);
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
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(getApplicationContext(), TentangAplikasi.class);
                    startActivity(i);

                }
            });
        }
    }


}
