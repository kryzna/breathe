package com.rgp.breathe.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.rgp.breathe.R;
import com.rgp.breathe.dao.Choice;
import com.rgp.breathe.dao.Question;
import com.rgp.breathe.dao.QuestionType;
import com.rgp.breathe.dao.Questionnaire;
import com.rgp.breathe.helper.Helper;

import java.util.List;

/**
 * Created by mdansari on 3/29/2016.
 */
public class QuetionnaireAdapter extends RecyclerView.Adapter<QuetionnaireAdapter.QuetionnaireViewHolder> {

    private Context context;
    private List<Questionnaire> questionnaireList;
    private ViewGroup parent;

    public static class QuetionnaireViewHolder extends RecyclerView.ViewHolder {
        public TextView questionnaireTitle;
        public Button questionnaireStatus;

        public QuetionnaireViewHolder(View view) {
            super(view);
            questionnaireTitle = (TextView) view.findViewById(R.id.questionnaire_title);
            questionnaireStatus = (Button) view.findViewById(R.id.questionary_status_button);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionnaire_row_view, parent, false);
        QuetionnaireViewHolder quetionnaireViewHolder = new QuetionnaireViewHolder(view);
        return quetionnaireViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final QuetionnaireViewHolder holder, final int position) {
        final String title = questionnaireList.get(position).getTitle();

        //TODO: set and get status of questionnaire as per the number of questions addressed
        final String status = "not completed";

        holder.questionnaireTitle.setText(title);
        holder.questionnaireStatus.setText(status);

        if (status.equalsIgnoreCase("completed")) {
            holder.questionnaireStatus.setTextColor(ContextCompat.getColor(context, R.color.cyan));
            holder.questionnaireStatus.setText("completed on " + Helper.getFormattedDate("MM.dd.yyyy"));
        } else {
            holder.questionnaireStatus.setTextColor(ContextCompat.getColor(context, R.color.orange_red));
        }
        holder.questionnaireStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.contains("not completed")) {
                    showQuestionsDialog(0, title, questionnaireList.get(position).getQuestions());
                } else {
                    showSubmitQuestionsDialog(title, questionnaireList.get(position).getQuestions());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionnaireList.size();
    }

    private void showSubmitQuestionsDialog(String title, List<Question> questionList) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage("This Questionnaire set is already submitted.\n\n Do you want to edit it again?");
        alertDialog.setPositiveButton("YES", new YesButtonClickListener(title, questionList));
        alertDialog.setNegativeButton("NO", new NoButtonClickListener());
        alertDialog.create();
        alertDialog.show();
    }

    class YesButtonClickListener implements DialogInterface.OnClickListener {
        String title;
        List<Question> questionList;

        YesButtonClickListener(String title, List<Question> questionList) {
            this.title = title;
            this.questionList = questionList;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            showQuestionsDialog(0, title, questionList);
        }
    }

    class NoButtonClickListener implements DialogInterface.OnClickListener {

        NoButtonClickListener() {

        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }


    private void showQuestionsDialog(final int questionNo, final String title, final List<Question> questionList) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.question_content, parent, false);
        TextView questionnaireTitleView = (TextView) linearLayout.findViewById(R.id.questionnaire_title);
        questionnaireTitleView.setText(title);

        TextView questionContentView = (TextView) linearLayout.findViewById(R.id.question_content);
        questionContentView.setText(questionList.get(questionNo).getContent());

        List<Choice> possibleAnswers = questionList.get(questionNo).getOptions();
        String questionType = questionList.get(questionNo).getType().name();
        if (QuestionType.RADIO.name().equalsIgnoreCase(questionType)) {
            linearLayout.addView(addAnswersInRadioFormat(possibleAnswers));
        } else {
            addAnswersInCheckBoxFormat(possibleAnswers, linearLayout);
        }
        if (questionNo == 0) {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            showQuestionsDialog(questionNo + 1, title, questionList);
                        }
                    });


        } else if (questionNo == (questionList.size() - 1)) {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //dialog.cancel();
                        }
                    })
                    .setNegativeButton("PREVIOUS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo - 1, title, questionList);
                        }
                    });

        } else {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo + 1, title, questionList);
                        }
                    })
                    .setNegativeButton("PREVIOUS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            showQuestionsDialog(questionNo - 1, title, questionList);
                        }
                    });
        }

        AlertDialog helpDialog = builder.create();
        helpDialog.show();
    }

    private void addAnswersInCheckBoxFormat(List<Choice> possibleAnswers, LinearLayout linearLayout) {
        // when answer is of type checkbox
        for (Choice pa : possibleAnswers) {
            AppCompatCheckBox compatCheckBox = new AppCompatCheckBox(context);
            compatCheckBox.setText(pa.getText());
            compatCheckBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(compatCheckBox);
        }
    }

    private RadioGroup addAnswersInRadioFormat(List<Choice> possibleAnswers) {
        final RadioButton[] radioButtons = new RadioButton[possibleAnswers.size()];
        RadioGroup rg = new RadioGroup(context); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        int i = 0;
        for (Choice pa : possibleAnswers) {
            radioButtons[i] = new AppCompatRadioButton(context);
            radioButtons[i].setText(pa.getText());
            rg.addView(radioButtons[i]);
            i++;
        }
        return rg;
    }

}