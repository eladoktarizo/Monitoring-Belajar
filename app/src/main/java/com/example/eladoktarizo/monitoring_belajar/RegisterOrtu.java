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

public class RegisterOrtu extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameortu;
    private EditText emailortu;
    private EditText passwordortu;
    private EditText namalengkaportu;
    private EditText alamatortu;
    private EditText notelportu;
    AQuery aq;
    private Button buttondaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ortu_activity);
        aq = new AQuery(getApplicationContext());
        usernameortu = (EditText) findViewById(R.id.usernameortu);
        passwordortu = (EditText) findViewById(R.id.passwordortu);
        emailortu = (EditText) findViewById(R.id.emailortu);
        namalengkaportu = (EditText) findViewById(R.id.namalengkaportu);
        alamatortu = (EditText) findViewById(R.id.alamatortu);
        notelportu = (EditText) findViewById(R.id.notelportu);

        buttondaftar = (Button) findViewById(R.id.buttondaftar);

        buttondaftar.setOnClickListener(this);
    }

    private void registerUser() {
        final String username = usernameortu.getText().toString().trim();
        final String password = passwordortu.getText().toString().trim();
        final String email = emailortu.getText().toString().trim();
        final String namalengkap = namalengkaportu.getText().toString().trim();
        final String alamat = alamatortu.getText().toString().trim();
        final String nomortelepon = notelportu.getText().toString().trim();
        final ProgressDialog loading;
        loading = ProgressDialog.show(RegisterOrtu.this, "Proses Kirim Data...", "Wait...", false, false);
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
                            Toast.makeText(RegisterOrtu.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginOrtu.class);
                            startActivity(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(RegisterOrtu.this, status.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v == buttondaftar){
            registerUser();
        }
    }
}