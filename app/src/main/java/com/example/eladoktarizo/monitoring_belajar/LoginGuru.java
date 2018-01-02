package com.example.eladoktarizo.monitoring_belajar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eladoktarizo.monitoring_belajar.config.ConfigLoginGuru;
import com.example.eladoktarizo.monitoring_belajar.config.ConfigSignupGuru;

import java.util.HashMap;
import java.util.Map;

public class LoginGuru extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    private EditText noindukguru;
    private EditText editTextPassword;
    private AppCompatButton buttonloginguru;
    private AppCompatButton buttonsignupguru;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_guru_activity);

        //Initializing views
        noindukguru = (EditText) findViewById(R.id.emailguru);
        editTextPassword = (EditText) findViewById(R.id.passwordguru);
        buttonsignupguru = (AppCompatButton) findViewById(R.id.buttonsignupguru);
        buttonloginguru = (AppCompatButton) findViewById(R.id.buttonloginguru);

        //Adding click listener
        buttonloginguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                Log.d("URL", ConfigLoginGuru.LOGIN_URL);
            }
        });
        buttonsignupguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterGuru.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(ConfigLoginGuru.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(ConfigLoginGuru.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginGuru.this, MenuGuru.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String email = noindukguru.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigLoginGuru.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(ConfigLoginGuru.LOGIN_SUCCESS)){
                            System.out.print("Toing"+response);
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginGuru.this.getSharedPreferences(ConfigLoginGuru.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(ConfigLoginGuru.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(ConfigLoginGuru.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(LoginGuru.this, MenuGuru.class);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginGuru.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(ConfigLoginGuru.KEY_EMAIL, email);
                params.put(ConfigLoginGuru.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
    }
}