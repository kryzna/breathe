package com.rgp.breathe.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgp.breathe.R;
import com.rgp.breathe.dao.QuestionnaryDataGenerator;
import com.rgp.breathe.model.Questionary;
import com.rgp.breathe.view.activity.MainActivity;
import com.rgp.breathe.view.adapter.MyAdapter;

import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
@SuppressLint("ValidFragment")
public class FragmentHealthRiskAssesment extends Fragment {

    private List<Questionary> questionaryList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Context context;

    public FragmentHealthRiskAssesment(Context context) {
        this.context = context;
        QuestionnaryDataGenerator.setQuestionaryData();
        questionaryList = QuestionnaryDataGenerator.getQuestionaryData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.health_risk_assesment));
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_health_risk_assesment, null);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.questionary_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getContext(), questionaryList);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }
}
