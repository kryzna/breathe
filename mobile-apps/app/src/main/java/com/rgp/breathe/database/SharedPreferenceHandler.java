package com.rgp.breathe.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mdansari on 4/1/2016.
 */
public class SharedPreferenceHandler {

    private static final String KEY_USERNAME = "prefUsername";
    private static final String KEY_USEREMAIL = "prefUseremail";
    private static final String KEY_USERDOB = "prefUserdob";
    private static final String KEY_USERPASSWORD = "prefUserpassword";
    private static final String KEY_AUTOENABLE = "prefAutoenable";

    private static Context context;

    public SharedPreferenceHandler(Context context) {
        this.context = context;
    }

    public static void saveUserInfo(String mUserName, String mEmail, String mDOB, String mPassword){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("prefUsername", mUserName);
        editor.putString("prefUseremail", mEmail);
        editor.putString("prefUserdob", mDOB);
        editor.putString("prefUserpassword", mPassword);
        editor.apply();
    }

    public static void setAutoEnable(boolean autoenable){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("prefAutoenable", autoenable);
        editor.apply();
    }


    public static String getmUser() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getString(KEY_USERNAME, "NULL");
    }

    public static String getmEmail() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getString(KEY_USEREMAIL, "NULL");
    }

    public static String getmDOB() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getString(KEY_USERDOB, "NULL");
    }


    public static String getmPass() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getString(KEY_USERPASSWORD, "NULL");
    }

    public static boolean ismAutoLogin() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getBoolean(KEY_AUTOENABLE, false);
    }


}
