package com.rgp.breathe.view.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgp.breathe.R;
import com.rgp.breathe.view.activity.MainActivity;

/**
 * Created by Phantom on 3/29/2016.
 */
@SuppressLint("ValidFragment")
public class FragmentQuestion extends Fragment {

    public FragmentQuestion(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_alerts, null);
        return root;
    }
}
