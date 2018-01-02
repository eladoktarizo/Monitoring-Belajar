package com.example.eladoktarizo.monitoring_belajar.config;

/**
 * Created by Elad Oktarizo on 03/08/2017.
 */

public class ConfigLoginOrtu {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://192.168.1.12/monitoring/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String NAMA_SHARED_PREF = "username";


    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}