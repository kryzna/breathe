package com.rgp.breathe.model;


import java.util.List;

public class TreatmentEvent {

    private String eventName;
    private boolean detailsAvailable;
    List<String[]> contentList;

    public TreatmentEvent(String eventName, boolean detailsAvailable, List<String[]> contentList) {
        this.eventName = eventName;
        this.detailsAvailable = detailsAvailable;
        this.contentList = contentList;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isDetailsAvailable() {
        return detailsAvailable;
    }

    public void setDetailsAvailable(boolean detailsAvailable) {
        this.detailsAvailable = detailsAvailable;
    }

    public List<String[]> getContentList() {
        return contentList;
    }

    public void setContentMap(List<String[]> contentList) {
        this.contentList = contentList;
    }
}
