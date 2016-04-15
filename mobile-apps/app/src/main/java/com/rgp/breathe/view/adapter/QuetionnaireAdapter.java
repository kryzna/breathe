package com.rgp.breathe.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.rgp.breathe.R;
import com.rgp.breathe.dao.Choice;
import com.rgp.breathe.dao.Question;
import com.rgp.breathe.dao.QuestionResponse;
import com.rgp.breathe.dao.QuestionType;
import com.rgp.breathe.dao.Questionnaire;
import com.rgp.breathe.dao.QuestionnaireResponse;
import com.rgp.breathe.dao.RiskScore;
import com.rgp.breathe.helper.Helper;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/29/2016.
 */
public class QuetionnaireAdapter
        extends RecyclerView.Adapter<QuetionnaireAdapter.QuetionnaireViewHolder> {

    private Context context;
    private List<Questionnaire> questionnaireList;
    private ViewGroup parent;

    public static class QuetionnaireViewHolder extends RecyclerView.ViewHolder {
        public TextView questionnaireTitle;
        public TextView questionnaireScore;
        public Button questionnaireStatus;

        public QuetionnaireViewHolder(View view) {
            super(view);
            questionnaireTitle = (TextView) view.findViewById(R.id.questionnaire_title);
            questionnaireScore = (TextView) view.findViewById(R.id.questionnaire_score);
            questionnaireStatus = (Button) view.findViewById(R.id.questionnaire_status_button);
        }
    }

    public QuetionnaireAdapter(Context parent, List<Questionnaire> questionnaireList) {
        this.context = parent;
        this.questionnaireList = questionnaireList;
    }

    @Override
    public QuetionnaireViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.questionnaire_row_view, parent, false);
        QuetionnaireViewHolder quetionnaireViewHolder = new QuetionnaireViewHolder(view);
        return quetionnaireViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final QuetionnaireViewHolder holder, final int position) {
        final String title = questionnaireList.get(position).getTitle();

        final String status = questionnaireList.get(position).getStatus();
        final Double score = questionnaireList.get(position).getWeight();
        holder.questionnaireTitle.setText(title);
        if (score != null)


            holder.questionnaireStatus.setText(status);


        holder.questionnaireStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.contains(Questionnaire.NOT_COMPLETED)) {
                    showQuestionsDialog(0, title, questionnaireList.get(position), holder);

                } else {
                    showSubmitQuestionsDialog(title, questionnaireList.get(position), holder);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionnaireList.size();
    }

    private void showSubmitQuestionsDialog(String title, Questionnaire questionnaire,
            QuetionnaireViewHolder holder) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(
                "This Questionnaire set is already submitted.\n\n Do you want to edit it again?");
        alertDialog.setPositiveButton("YES",
                new YesButtonClickListener(title, questionnaire, holder));
        alertDialog.setNegativeButton("NO", new NoButtonClickListener());
        alertDialog.create();
        alertDialog.show();
    }

    class YesButtonClickListener implements DialogInterface.OnClickListener {
        String title;
        Questionnaire questionnaire;
        private QuetionnaireViewHolder holder;

        YesButtonClickListener(String title, Questionnaire questionnaire,
                QuetionnaireViewHolder holder) {
            this.title = title;
            this.questionnaire = questionnaire;
            this.holder = holder;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            showQuestionsDialog(0, title, questionnaire, holder);
        }
    }

    class NoButtonClickListener implements DialogInterface.OnClickListener {

        NoButtonClickListener() {

        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }


    private void showQuestionsDialog(final int questionNo, final String title,
            final Questionnaire questionnaire, final QuetionnaireViewHolder holder) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_content, parent, false);
        TextView questionnaireTitleView =
                (TextView) linearLayout.findViewById(R.id.questionnaire_title);
        questionnaireTitleView.setText(title);

        TextView questionContentView = (TextView) linearLayout.findViewById(R.id.question_content);
        Question questionInView = questionnaire.getQuestions().get(questionNo);
        questionContentView.setText(questionInView.getContent());

        String questionType = questionInView.getType().name();
        if (QuestionType.RADIO.name().equalsIgnoreCase(questionType)) {
            linearLayout.addView(addAnswersInRadioFormat(questionInView));
        } else {
            addAnswersInCheckBoxFormat(questionInView, linearLayout);
        }
        if (questionNo == 0) {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                            showQuestionsDialog(questionNo + 1, title, questionnaire, holder);
                        }
                    });


        } else if (questionNo == (questionnaire.getQuestions().size() - 1)) {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            new ServerRequest(questionnaire, holder).execute();
                        }
                    }).setNegativeButton("PREVIOUS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo - 1, title, questionnaire, holder);
                        }
                    });

        } else {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo + 1, title, questionnaire, holder);
                        }
                    }).setNegativeButton("PREVIOUS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo - 1, title, questionnaire, holder);
                        }
                    });
        }

        AlertDialog helpDialog = builder.create();
        helpDialog.show();
    }

    private void addAnswersInCheckBoxFormat(final Question question, LinearLayout linearLayout) {
        // when answer is of type checkbox
        List<Choice> possibleAnswers = question.getOptions();
        for (final Choice pa : possibleAnswers) {
            AppCompatCheckBox compatCheckBox = new AppCompatCheckBox(context);
            compatCheckBox.setText(pa.getText());
            if (pa.isChosen()) {
                compatCheckBox.setChecked(true);
            }
            compatCheckBox.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            compatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    pa.setChoice(isChecked);
                    if (!question.isAttempted()) {
                        question.markAttempted();
                    }
                }
            });
            linearLayout.addView(compatCheckBox);
        }
    }

    private RadioGroup addAnswersInRadioFormat(final Question question) {
        List<Choice> possibleAnswers = question.getOptions();
        final RadioButton[] radioButtons = new RadioButton[possibleAnswers.size()];
        RadioGroup rg = (RadioGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.radio_group_ra, null, false);
        int i = 0;
        for (Choice pa : possibleAnswers) {
            radioButtons[i] = new AppCompatRadioButton(context);
            radioButtons[i].setText(pa.getText());
            radioButtons[i].setId(pa.getId());
            if (pa.isChosen()) {
                radioButtons[i].setChecked(true);
            }
            rg.addView(radioButtons[i]);
            i++;
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                question.switchChoice(checkedId);
                if (!question.isAttempted()) {
                    question.markAttempted();
                }
            }
        });
        return rg;
    }

    public QuestionnaireResponse prepareQuestionnaireResponse(Questionnaire questionnaire) {
        QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
        int numberOfAttemptedQuestions = 0;
        List<QuestionResponse> questionResponseList = new ArrayList<QuestionResponse>(1);
        questionnaireResponse.setQuestionnaire(new Questionnaire().withId(questionnaire.getId()));
        for (Question question : questionnaire.getQuestions()) {
            QuestionResponse questionResponse =
                    new QuestionResponse().withQuestion(new Question().withId(question.getId()));
            List<Choice> selectedChoices = new ArrayList<Choice>();
            for (Choice choice : question.getSelectedChoices()) {
                selectedChoices.add(new Choice().withId(choice.getId()));
            }
            questionResponse.setAnswers(selectedChoices);
            questionResponseList.add(questionResponse);
            if (question.isAttempted())
                numberOfAttemptedQuestions++;
        }
        questionnaireResponse.setQuestionResponseList(questionResponseList);
        if (numberOfAttemptedQuestions == questionnaire.getQuestions().size()) {
            questionnaire.setStatus(Questionnaire.COMPLETED);
        }
        return questionnaireResponse;
    }


    private class ServerRequest extends AsyncTask<Void, Void, Double> {

        Questionnaire questionnaire;
        private final QuetionnaireViewHolder holder;

        protected ServerRequest(Questionnaire questionnaire, QuetionnaireViewHolder holder) {
            this.questionnaire = questionnaire;
            this.holder = holder;
        }

        @Override
        protected Double doInBackground(Void... params) {
            try {
                //TODO: move URL to App Settings
                final String url = "http://10.64.34.36:8080/questionnaire/response";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(getMessageConverters());
                QuestionnaireResponse questionnaireResponse =
                        prepareQuestionnaireResponse(questionnaire);
                if (questionnaire.getStatus().equalsIgnoreCase(Questionnaire.COMPLETED)) {
                    RiskScore riskScore = restTemplate.postForObject(url, questionnaireResponse,
                            RiskScore.class, "");
                    return riskScore.getScore();
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return -1.0;
        }

        @Override
        protected void onPostExecute(Double result) {
            if (result > 0.0) {
                double riskScoreRounded = Helper.round(result, 2);
                questionnaire.setWeight(riskScoreRounded);
                holder.questionnaireStatus
                        .setTextColor(ContextCompat.getColor(context, R.color.cyan));
                //holder.questionnaireStatus.setText("completed on " + Helper.getFormattedDate("MM.dd.yyyy"));
                holder.questionnaireStatus
                        .setText(context.getResources().getString(R.string.questionnaire_completed)
                                + " " + Helper.getFormattedDate("MM.dd.yyyy"));
                holder.questionnaireScore.setText("" + riskScoreRounded);
            }
        }

        private List<HttpMessageConverter<?>> getMessageConverters() {
            List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
            converters.add(new GsonHttpMessageConverter());
            return converters;
        }
    }
}
