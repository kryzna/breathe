package com.rgp.breathe.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.rgp.breathe.R;
import com.rgp.breathe.model.TreatmentEvent;
import com.rgp.breathe.view.activity.MainActivity;
import com.rgp.breathe.view.adapter.TreatmentEventAdapter2;

import java.util.ArrayList;
import java.util.List;

public class TreatmentPlanFragment extends Fragment {

    TreatmentEventAdapter2 listAdapter;
    ExpandableListView expandableListView;
    private List<TreatmentEvent> mDataList;


    public TreatmentPlanFragment(){
        mDataList = new ArrayList<TreatmentEvent>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.treatment_plan));
        View rootView = inflater.inflate(R.layout.fragment_treatment_plan, container, false);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.nav_treatment_plan_2_expandable_list);

        prepareListData();

        listAdapter = new TreatmentEventAdapter2(getContext(), mDataList);

        expandableListView.setAdapter(listAdapter);
        return rootView;
    }


    private void prepareListData(){
        String[] data1 = new String[]{"Name","Xopenex HFA"};
        String[] data2 = new String[]{"Form", "Inhaler"};
        String[] data3 = new String[]{"Dose","4.0"};
        String[] data4 = new String[]{"Frequency", "2 times per day"};
        List<String[]> ldata2 = new ArrayList<String[]>(4); ldata2.add(data1);ldata2.add(data2);ldata2.add(data3);ldata2.add(data4);

        String[] data5 = new String[]{"Frequency","2 times/month"};
        String[] data6 = new String[]{"Next Doctor visit", "25.03.2016"};
        List<String[]> ldata3 = new ArrayList<String[]>(2); ldata3.add(data5);ldata3.add(data6);

        String[] data7 = new String[]{"Next Screening","27.03.2016"};
        String[] data8 = new String[]{"Location", "AIIMS, Delhi"};
        List<String[]> ldata4 = new ArrayList<String[]>(2); ldata4.add(data7);ldata4.add(data8);

        String[] data9 = new String[]{"Name","Peak Flow"};
        String[] data10 = new String[]{"When", "7:00 PM"};
        List<String[]> ldata5 = new ArrayList<String[]>(2); ldata5.add(data9);ldata5.add(data10);

        mDataList.add(new TreatmentEvent("Plan goals", true, null));
        mDataList.add(new TreatmentEvent("Medications", true, ldata2));
        mDataList.add(new TreatmentEvent("Visits", true, ldata3));
        mDataList.add(new TreatmentEvent("Screening", false, ldata4));
        mDataList.add(new TreatmentEvent("Vitals to track", true, ldata5));
    }

}
