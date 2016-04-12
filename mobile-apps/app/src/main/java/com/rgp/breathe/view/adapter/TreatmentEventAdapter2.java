package com.rgp.breathe.view.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.rgp.breathe.R;
import com.rgp.breathe.model.TreatmentEvent;

import java.util.*;


public class TreatmentEventAdapter2 extends BaseExpandableListAdapter {
    private Context context;
    private List<TreatmentEvent> listData;


    public TreatmentEventAdapter2(Context context, List<TreatmentEvent> listDataHeader) {
        this.context = context;
        this.listData = listDataHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listData.get(groupPosition).getContentList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        final String[] childText = (String[]) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.treatment_event_list_item, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText[0]);
        TextView txtListChild2 = (TextView) convertView.findViewById(R.id.lblListItem2);
        txtListChild2.setText(childText[1]);
        return convertView;
    }


    @Override
    public Object getGroup(int groupPosition) {
        return this.listData.get(groupPosition).getEventName();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        List<String[]> childList = this.listData.get(groupPosition).getContentList();
        if (childList != null) {
            return childList.size();
        }
        return 0;
    }

    @Override
    public int getGroupCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.treatment_event_list_group, null);
        }

        TextView listHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}


