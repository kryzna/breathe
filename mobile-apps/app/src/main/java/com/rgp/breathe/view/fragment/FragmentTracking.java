package com.rgp.breathe.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.helper.Helper;
import com.rgp.breathe.helper.SQLiteHandler;
import com.rgp.breathe.model.PeakFlow;
import com.rgp.breathe.view.activity.MainActivity;
import com.rgp.breathe.view.activity.PeakFlowActivity;
import com.rgp.breathe.view.adapter.TrackerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
@SuppressLint("ValidFragment")
public class FragmentTracking extends Fragment {

    private CoordinatorLayout coordinatorLayout;
    private AppCompatSpinner spinner;
    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private List<PeakFlow> peakFlowList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Context context;

    public FragmentTracking(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.tracking));
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tracking, null);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.tracking_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        coordinatorLayout = (CoordinatorLayout) root;
        textView = (TextView) root.findViewById(R.id.readingSince);
        textView.setText("10 reading Since " + Helper.getFormattedDate("dd.MM.yyyy"));
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab);
        spinner = (AppCompatSpinner) root.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long id) {
                //String s = adapter.getItemAtPosition(position).toString();
                if (position == 0) {
                    mAdapter = new TrackerAdapter(getContext(), peakFlowList);
                    mRecyclerView.setAdapter(mAdapter);
                    floatingActionButton.show();
                } else if (position == 1) {
                    mAdapter = new TrackerAdapter(getContext(), new ArrayList<PeakFlow>());
                    mRecyclerView.setAdapter(mAdapter);
                    floatingActionButton.hide();
                } else if (position == 2) {
                    mAdapter = new TrackerAdapter(getContext(), new ArrayList<PeakFlow>());
                    mRecyclerView.setAdapter(mAdapter);
                    floatingActionButton.hide();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Add new entry", Snackbar.LENGTH_LONG)
                        .setAction("Add", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //call peak flow activity
                                Intent intent = new Intent(context, PeakFlowActivity.class);
                                startActivity(intent);
                            }
                        });

                snackbar.show();
            }
        });
        initializeData();
        return root;
    }

    private void initializeAdapter() {
        mAdapter = new TrackerAdapter(context, peakFlowList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /*private void initializeData(){
        List<PeakFlow> list = PeakFlowDao.getPeakFlowList();
        if(list != null) {
            if(list.size() > 10)
                peakFlowList = PeakFlowDao.getPeakFlowList().subList(0, 10);
            else
                peakFlowList = list;
        }
        initializeAdapter();
    }*/

    private void initializeData() {
        SQLiteHandler sqLiteHandler = new SQLiteHandler(context);
        List<PeakFlow> list = sqLiteHandler.getPeakFlowList();
        if (list != null) {
            peakFlowList = list;
        }
        initializeAdapter();
    }


    @Override
    public void onResume() {
        super.onResume();
        initializeData();
    }

}
