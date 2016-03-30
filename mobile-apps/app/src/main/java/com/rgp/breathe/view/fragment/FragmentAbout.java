package com.rgp.breathe.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgp.breathe.R;
import com.rgp.breathe.view.activity.MainActivity;

/**
 * Created by mdansari on 3/30/2016.
 */
@SuppressLint("ValidFragment")
public class FragmentAbout extends Fragment {
    Context context;

    public FragmentAbout(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.app_name));
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_about, null);
        return root;
    }
}
