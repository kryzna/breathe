package com.rgp.breathe.dao;

import com.rgp.breathe.model.PeakFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phantom on 3/30/2016.
 */
public class PeakFlowDao {

    static List<PeakFlow> peakFlowList;

    public static void generateDummyRecords(){
        peakFlowList = new ArrayList<>();
        addPeakFlow(new PeakFlow("30.3.2016 10:10 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "600 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 10:15 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "580 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 10:18 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "590 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 10:24 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "580 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 10:52 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "570 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 10:55 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "580 ml/s"));
        addPeakFlow(new PeakFlow("30.3.2016 11:10 AM", new ArrayList<String>(), new ArrayList<String>(), "Mohali, Punjab", "580 ml/s"));
    }

    public static void addPeakFlow(PeakFlow peakFlow) {
        peakFlowList.add(0,peakFlow);
    }

    public static void setPeakFlowList(List<PeakFlow> list) {
        peakFlowList = list;
    }

    public static List<PeakFlow> getPeakFlowList() {
        return peakFlowList;
    }

}
