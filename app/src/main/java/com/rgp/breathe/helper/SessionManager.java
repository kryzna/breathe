package com.rgp.breathe.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by mdansari on 3/23/2016.
 */
public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "com.rgp.breathe";
    // All Shared Preferences Keys
    private static final String AUTO_LOGIN_ENABLED = "isAutoLoginEnabled";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public boolean isAutoLoginEnabled() {
        return sharedPreferences.getBoolean(AUTO_LOGIN_ENABLED, false);
    }

    public void createLogin(String name, String email, boolean isLoginEnabled) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(AUTO_LOGIN_ENABLED, isLoginEnabled);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

}
