package com.rgp.breathe.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mdansari on 4/1/2016.
 */
public class SharedPreferencesHelper {

    static final String KEY_USERNAME = "prefUsername";
    static final String KEY_USEREMAIL = "prefUseremail";
    static final String KEY_USERDOB = "prefUserdob";
    static final String KEY_USERPASSWORD = "prefUserpassword";
    static final String KEY_AUTOENABLE = "prefAutoenable";
    static final String NULL = "NULL";

    private final SharedPreferences preferences;

    public SharedPreferencesHelper(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void saveUserInfo(String mUserName, String mEmail, String mDOB, String mPassword) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, mUserName);
        editor.putString(KEY_USEREMAIL, mEmail);
        editor.putString(KEY_USERDOB, mDOB);
        editor.putString(KEY_USERPASSWORD, mPassword);
        editor.apply();
    }

    public void setAutoEnable(boolean autoenable) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_AUTOENABLE, autoenable);
        editor.apply();
    }

    public String getmUserName() {
        return preferences.getString(KEY_USERNAME, NULL);
    }

    public String getmEmail() {
        return preferences.getString(KEY_USEREMAIL, NULL);
    }

    public String getmDOB() {
        return preferences.getString(KEY_USERDOB, NULL);
    }


    public String getmPassword() {
        return preferences.getString(KEY_USERPASSWORD, NULL);
    }

    public boolean ismAutoLogin() {
        return preferences.getBoolean(KEY_AUTOENABLE, false);
    }

}
