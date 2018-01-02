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
import com.example.eladoktarizo.monitoring_belajar.config.ConfigLoginOrtu;

import java.util.HashMap;
import java.util.Map;

public class LoginOrtu extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;
    private AppCompatButton buttonsignUp;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ortu_activity);

        //Initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        buttonsignUp = (AppCompatButton) findViewById(R.id.buttonsignUp);

        //Adding click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                Log.d("URL", ConfigLoginOrtu.LOGIN_URL);

                SharedPreferences.Editor editor = sharedpreferences.edit();
            }
        });
        buttonsignUp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick (View view) {
//               view.startAnimation(buttonsignUp);
                startActivity(new Intent(getApplicationContext(), RegisterOrtu.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(ConfigLoginOrtu.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(ConfigLoginOrtu.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginOrtu.this, MenuOrtu.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigLoginOrtu.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(ConfigLoginOrtu.LOGIN_SUCCESS)){
                            System.out.print("Toing"+response);
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginOrtu.this.getSharedPreferences(ConfigLoginOrtu.SHARED_PREF_NAME,
                                    Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(ConfigLoginOrtu.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(ConfigLoginOrtu.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(LoginOrtu.this, MenuOrtu.class);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginOrtu.this, "Invalid username or password", Toast.LENGTH_LONG).show();
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
                params.put(ConfigLoginOrtu.KEY_EMAIL, email);
                params.put(ConfigLoginOrtu.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
