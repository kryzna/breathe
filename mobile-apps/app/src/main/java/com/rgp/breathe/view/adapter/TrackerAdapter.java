package com.rgp.breathe.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.model.PeakFlow;

import java.util.List;

/**
 * Created by Phantom on 3/30/2016.
 */
public class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.TrackViewHolder> {

    private List<PeakFlow> peakFlowList;
    Context context;

    public TrackerAdapter(Context context, List<PeakFlow> peakFlowList) {
        this.context = context;
        this.peakFlowList = peakFlowList;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_row_view, parent, false);
        TrackViewHolder trackViewHolder = new TrackViewHolder(view);
        return trackViewHolder;
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        if (peakFlowList.size() == 0) {
            holder.readingUnit.setText("No reading added yet");
        } else {
            final String readingUnit = peakFlowList.get(position).getPeakFlowReading();
            final String readingTime = peakFlowList.get(position).getDateTime();
            holder.readingUnit.setText(readingUnit);
            holder.readingTime.setText(readingTime);
        }
    }

    @Override
    public int getItemCount() {
        if(peakFlowList != null) {
            return peakFlowList.size();
        }
        return -1;
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        public TextView readingUnit;
        public TextView readingTime;

        public TrackViewHolder(View itemView) {
            super(itemView);
            readingUnit = (TextView) itemView.findViewById(R.id.reading_unit);
            readingTime = (TextView) itemView.findViewById(R.id.reading_time);
        }
    }
}
