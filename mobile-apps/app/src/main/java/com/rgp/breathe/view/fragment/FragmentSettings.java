package com.rgp.breathe.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.rgp.breathe.R;


/**
 * Created by mdansari on 3/30/2016.
 */
@SuppressLint("ValidFragment")
public class FragmentSettings extends PreferenceFragment {

    Context context;

    public FragmentSettings(Context context) {
        this.context = context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preferences);
    }

}
