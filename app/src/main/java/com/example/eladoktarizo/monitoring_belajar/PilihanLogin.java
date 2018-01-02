package com.example.eladoktarizo.monitoring_belajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PilihanLogin extends AppCompatActivity {

    Button btnLoginOrtu,btnLoginGuru;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_login_activity);


        btnLoginGuru = (Button)findViewById(R.id.buttonlogin2);
        btnLoginOrtu=(Button)findViewById(R.id.buttonlogin1);


        btnLoginOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(),LoginOrtu.class);
                startActivity(i);
            }
        });
        btnLoginGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(),LoginGuru.class);
                startActivity(i);
            }
        });
    }
}
