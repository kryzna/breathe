package com.rgp.breathe.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.model.Questionary;

import java.util.List;

/**
 * Created by Phantom on 3/29/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Questionary> questionaryList;
    ViewGroup parent;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView questionaryTitle;
        public TextView questionaryStatus;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            questionaryTitle = (TextView) view.findViewById(R.id.questionary_title);
            questionaryStatus = (TextView) view.findViewById(R.id.questionary_status);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final String title = questionaryList.get(position).getTitle();
        final String status = questionaryList.get(position).getStatus();
        holder.questionaryTitle.setText(title);
        holder.questionaryStatus.setText(status);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.question_content);
                dialog.setTitle(title);
                dialog.show();*/
                showPopUp2(title, "Do you find difficulty in taking breathe?");
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionaryList.size();
    }

    private void showPopUp2(String title, String question) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.question_content, parent, false);
        TextView textView = (TextView) linearLayout.findViewById(R.id.questionary_title);
        textView.setText(title);

        String[] answers = {"YES", "NO"};

        /*// when answer is of type checkbox
        for (int i=0; i<answers.length; i++){
            AppCompatCheckBox  compatCheckBox = new AppCompatCheckBox(context);
            compatCheckBox.setText(answers[i]);
            compatCheckBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            ((LinearLayout) linearLayout).addView(compatCheckBox);
        }*/

        // when answer is of type radio
        final RadioButton[] rb = new RadioButton[2];
        RadioGroup rg = new RadioGroup(context); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (int i = 0; i < answers.length; i++) {
            rb[i] = new AppCompatRadioButton(context);
            rb[i].setText(answers[i]);
            rg.addView(rb[i]);
        }
        ((LinearLayout) linearLayout).addView(rg);

        builder.setView(linearLayout)
                // Add action buttons
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog helpDialog = builder.create();
        helpDialog.show();
    }

}