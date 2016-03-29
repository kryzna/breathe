package com.rgp.breathe.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.model.Questionary;

import java.util.List;

/**
 * Created by mdansari on 3/28/2016.
 */
public class QuestionaryAdapter extends BaseAdapter implements View.OnClickListener {

    List<Questionary> questionaryList;
    Context context;

    public QuestionaryAdapter(Context context, List<Questionary> data) {
        super();
        this.context = context;
        this.questionaryList = data;
    }

    private static class QuestionaryViewHolder {
        public TextView questionaryTitle;
        public TextView questionaryStatus;
    }

    @Override
    public int getCount() {
        return questionaryList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        QuestionaryViewHolder holder = new QuestionaryViewHolder();
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_row, null);
            // Now we can fill the layout with the right values
            TextView titleView = (TextView) view.findViewById(R.id.questionary_title);
            TextView statusView = (TextView) view.findViewById(R.id.questionary_status);

            holder.questionaryTitle = titleView;
            holder.questionaryStatus = statusView;
            view.setTag(holder);
        } else {
            holder = (QuestionaryViewHolder) view.getTag();
        }
        Questionary questionary = questionaryList.get(position);
        holder.questionaryTitle.setText(questionary.getTitle());
        holder.questionaryStatus.setText(questionary.getStatus());
        return view;
    }

    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.list_row);
        dialog.setTitle("Title Questionarie");
        dialog.show();
    }
}
