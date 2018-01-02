package com.example.eladoktarizo.monitoring_belajar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.eladoktarizo.monitoring_belajar.config.ConfigSignupOrtu;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elad Oktarizo on 27/10/2017.
 */

public class RegisterGuru extends AppCompatActivity implements View.OnClickListener {

    private EditText noiduk;
    private EditText emailguru;
    private EditText passwordguru;
    private EditText namalengkapguru;
    private EditText alamatguru;
    private EditText notelpguru;
    AQuery aq;
    private Button buttondaftarguru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_guru_activity);
        aq = new AQuery(getApplicationContext());
        noiduk = (EditText) findViewById(R.id.nomorinduk);
        passwordguru = (EditText) findViewById(R.id.passwordguru);
        emailguru = (EditText) findViewById(R.id.emailguru);
        namalengkapguru = (EditText) findViewById(R.id.namalengkapguru);
        alamatguru = (EditText) findViewById(R.id.alamatguru);
        notelpguru = (EditText) findViewById(R.id.notelpguru);

        buttondaftarguru = (Button) findViewById(R.id.buttondaftarguru);

        buttondaftarguru.setOnClickListener(this);
    }

    private void registerUser() {
        final String username = noiduk.getText().toString().trim();
        final String password = passwordguru.getText().toString().trim();
        final String email = emailguru.getText().toString().trim();
        final String namalengkap = namalengkapguru.getText().toString().trim();
        final String alamat = alamatguru.getText().toString().trim();
        final String nomortelepon = notelpguru.getText().toString().trim();
        final ProgressDialog loading;
        loading = ProgressDialog.show(RegisterGuru.this, "Proses Kirim Data...", "Wait...", false, false);
        HashMap<String, String> params = new HashMap<>();
        // Sesuaikan bagian ini dengan field di tabel Mahasiswa
        params.put(ConfigSignupOrtu.KEY_EMP_USERNAME, username);
        params.put(ConfigSignupOrtu.KEY_EMP_PASSWORD, password);
        params.put(ConfigSignupOrtu.KEY_EMP_EMAIL, email);
        params.put(ConfigSignupOrtu.KEY_EMP_NAMALENGKAP, namalengkap);
        params.put(ConfigSignupOrtu.KEY_EMP_ALAMAT, alamat);
        params.put(ConfigSignupOrtu.KEY_EMP_NOMORTELEPON, nomortelepon);
        aq.ajax(ConfigSignupOrtu.URL_ADD, params, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        if (object.getString("success").equals("1")) {
                            loading.dismiss();
                            Toast.makeText(RegisterGuru.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginGuru.class);
                            startActivity(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(RegisterGuru.this, status.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        if(v == buttondaftarguru){
            registerUser();
        }
    }
}
