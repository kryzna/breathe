package com.rgp.breathe.view.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rgp.breathe.R;
import com.rgp.breathe.dao.Questionnaire;
import com.rgp.breathe.view.activity.MainActivity;
import com.rgp.breathe.view.adapter.QuetionnaireAdapter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
public class FragmentHealthRiskAssesment extends Fragment {

    private List<Questionnaire> questionnaireList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.health_risk_assesment));
        ViewGroup root =
                (ViewGroup) inflater.inflate(R.layout.fragment_health_risk_assesment, null);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.questionnaire_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        new QuestionnaireService().execute();
        //TODO: Handle the delay in App, move the questionnaire fetching in Assessment Fragment.
        /*try {
        Thread.sleep(5000L);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }*/
        /* mAdapter = new QuetionnaireAdapter(getContext(), questionnaireList);
         mRecyclerView.setAdapter(mAdapter);*/
        return root;
    }


    private class QuestionnaireService extends AsyncTask<Void, Void, List<Questionnaire>> {

        @Override
        protected List<Questionnaire> doInBackground(Void... params) {
            try {
                final String url = "http://10.64.34.44:8080/questionnaire/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
                //TODO: Send Questionnaire query
                Questionnaire questionnaire =
                        restTemplate.getForObject(url, Questionnaire.class, "1");
                List<Questionnaire> list = new ArrayList<Questionnaire>(1);
                list.add(questionnaire);
                questionnaireList = list;
                return list;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Questionnaire> questionnaires) {
            progressDialog.dismiss();
            questionnaireList = questionnaires;
            mAdapter = new QuetionnaireAdapter(getContext(), questionnaireList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
