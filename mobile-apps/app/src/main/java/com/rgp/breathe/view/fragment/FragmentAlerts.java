package com.rgp.breathe.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgp.breathe.R;
import com.rgp.breathe.view.activity.MainActivity;

/**
 * Created by mdansari on 3/28/2016.
 */

public class FragmentAlerts extends Fragment {

    public FragmentAlerts() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.alerts));
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_alerts, null);
        return root;
    }
}
