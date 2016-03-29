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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.model.Question;
import com.rgp.breathe.model.Questionary;

import java.util.List;

/**
 * Created by Phantom on 3/29/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Questionary> questionaryList;
    private ViewGroup parent;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView questionaryTitle;
        public Button questionaryStatus;

        public MyViewHolder(View view) {
            super(view);
            questionaryTitle = (TextView) view.findViewById(R.id.questionary_title);
            questionaryStatus = (Button) view.findViewById(R.id.questionary_status_button);
        }
    }

    public MyAdapter(Context parent, List<Questionary> questionaryList) {
        this.context = parent;
        this.questionaryList = questionaryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String title = questionaryList.get(position).getTitle();
        final String status = questionaryList.get(position).getStatus();

        holder.questionaryTitle.setText(title);
        holder.questionaryStatus.setText(status);

        if (status.equalsIgnoreCase("completed")) {
            holder.questionaryStatus.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.questionaryStatus.setTextColor(ContextCompat.getColor(context, R.color.cyan));
        } else {
            holder.questionaryStatus.setTextColor(ContextCompat.getColor(context, R.color.orange_red));
        }
        holder.questionaryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equalsIgnoreCase("completed")) {
                    showSubmitQuestionsDialog(title, questionaryList.get(position).getQuestionList());
                } else {
                    showQuestionsDialog(0, title, questionaryList.get(position).getQuestionList());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionaryList.size();
    }

    private void showSubmitQuestionsDialog(String title, List<Question> questionList) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage("This Questionnary set is already submitted.\n\n Do you want to edit it again?");
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
        TextView questionnaryTitleView = (TextView) linearLayout.findViewById(R.id.questionary_title);
        questionnaryTitleView.setText(title);

        TextView quesContentView = (TextView) linearLayout.findViewById(R.id.question_content);
        quesContentView.setText(questionList.get(questionNo).getQuestionContent());

        List<String> possibleAnswers = questionList.get(questionNo).getPossibleAnswers();
        String questionType = questionList.get(questionNo).getQuestionType();
        if ("RADIO".equalsIgnoreCase(questionType)) {
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

    private void setQuestionContent(final int questionNo, final String title, final List<Question> questionList, final LinearLayout linearLayout, final AlertDialog.Builder builder) {

        TextView questionnaryTitleView = (TextView) linearLayout.findViewById(R.id.questionary_title);
        questionnaryTitleView.setText(title);

        TextView quesContentView = (TextView) linearLayout.findViewById(R.id.question_content);
        quesContentView.setText(questionList.get(questionNo).getQuestionContent());

        List<String> possibleAnswers = questionList.get(questionNo).getPossibleAnswers();
        String questionType = questionList.get(questionNo).getQuestionType();
        if ("RADIO".equalsIgnoreCase(questionType)) {
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
                            setQuestionContent(questionNo + 1, title, questionList, linearLayout, builder);
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
                            setQuestionContent(questionNo - 1, title, questionList, linearLayout, builder);
                        }
                    });

        } else {
            builder.setView(linearLayout)
                    // Add action buttons
                    .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            setQuestionContent(questionNo + 1, title, questionList, linearLayout, builder);
                        }
                    })
                    .setNegativeButton("PREVIOUS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            setQuestionContent(questionNo - 1, title, questionList, linearLayout, builder);
                        }
                    });
        }

        AlertDialog helpDialog = builder.create();
        helpDialog.show();
    }

    private void addAnswersInCheckBoxFormat(List<String> possibleAnswers, LinearLayout linearLayout) {
        // when answer is of type checkbox
        for (String pa : possibleAnswers) {
            AppCompatCheckBox compatCheckBox = new AppCompatCheckBox(context);
            compatCheckBox.setText(pa);
            compatCheckBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(compatCheckBox);
        }
    }

    private RadioGroup addAnswersInRadioFormat(List<String> possibleAnswers) {
        final RadioButton[] radioButtons = new RadioButton[possibleAnswers.size()];
        RadioGroup rg = new RadioGroup(context); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        int i = 0;
        for (String pa : possibleAnswers) {
            radioButtons[i] = new AppCompatRadioButton(context);
            radioButtons[i].setText(pa);
            rg.addView(radioButtons[i]);
            i++;
        }
        return rg;
    }

}