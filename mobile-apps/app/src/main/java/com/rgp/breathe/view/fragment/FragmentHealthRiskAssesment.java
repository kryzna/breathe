package com.rgp.breathe.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rgp.breathe.R;
import com.rgp.breathe.dao.QuestionnaireDataGenerator;
import com.rgp.breathe.model.Questionnaire;
import com.rgp.breathe.view.activity.MainActivity;
import com.rgp.breathe.view.adapter.QuetionnaireAdapter;

import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
public class FragmentHealthRiskAssesment extends Fragment {

    private List<Questionnaire> questionnaireList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentHealthRiskAssesment() {
        QuestionnaireDataGenerator.setQuestionnairesData();
        questionnaireList = QuestionnaireDataGenerator.getQuestionnairesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.health_risk_assesment));
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_health_risk_assesment, null);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.questionnaire_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new QuetionnaireAdapter(getContext(), questionnaireList);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }
}
