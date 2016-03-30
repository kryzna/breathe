package com.rgp.breathe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/30/2016.
 */
public class PeakFlow {

    private String dateTime;
    private List<String> symptomsList;
    private List<String> triggersList;
    private String geoLocation;
    private String peakFlowReading;

    public PeakFlow(String dateTime, List<String> symptomsList,  List<String> triggersList, String geoLocation, String peakFlowReading) {
        this.dateTime = dateTime;
        this.symptomsList = symptomsList;
        this.triggersList = triggersList;
        this.geoLocation = geoLocation;
        this.peakFlowReading = peakFlowReading;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getSymptomsList() {
        return symptomsList;
    }

    public void setSymptomsList(List<String> symptomsList) {
        this.symptomsList = symptomsList;
    }

    public  List<String> getTriggersList() {
        return triggersList;
    }

    public void setTriggersList( List<String> triggersList) {
        this.triggersList = triggersList;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getPeakFlowReading() {
        return peakFlowReading;
    }

    public void setPeakFlowReading(String peakFlowReading) {
        this.peakFlowReading = peakFlowReading;
    }
}
